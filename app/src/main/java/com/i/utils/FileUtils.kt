package com.i.utils

import android.content.ContentUris
import android.content.Context
import android.content.CursorLoader
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import org.jetbrains.annotations.Nullable
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

object FileUtils {
    private const val TAG = "FUFileUtils"


    //region 文件管理
    /**
     * 获取外部存储目录
     * @return File 外部存储目录
     */
    @JvmStatic
    fun getExternalRootFileDir(context: Context): File {
        return context.getExternalFilesDir(null) ?: context.filesDir
    }

    /**
     * 获取外部存储缓存目录
     * @return File 外部存储缓存目录
     */
    @JvmStatic
    fun getExternalCacheFileDir(context: Context): File {
        return context.externalCacheDir ?: context.cacheDir
    }

    /**
     * 创建文件夹
     * @param dirPath String 文件夹路径
     * @return Boolean
     */
    @JvmStatic
    fun createFileDir(dirPath: String): Boolean {
        val dir = File(dirPath)
        return if (!dir.exists()) {
            dir.mkdirs()
        } else true
    }

    fun createFile(filePath: String): Boolean {
        val file = File(filePath);
        if (file.exists()) {
            return true;
        }

        val pareFile = file.parentFile
        if (pareFile != null && !pareFile.exists()) {
            Log.d(TAG, "creating parent directory...");
            if (!pareFile.mkdirs()) {
                Log.e(TAG, "created parent directory failed.");
                return false;
            }
        }
        try {
            if (file.createNewFile()) {
                Log.i(TAG, "create file [ $filePath ] success");
                return true;
            }
        } catch (e: IOException) {
            Log.e(TAG, "create file [ $filePath ] failed");
        }
        return false;
    }

    /**
     * 删除文件夹 - 递归删除
     * @param dirPath String 文件夹目录
     */
    @JvmStatic
    fun deleteFileDir(dirPath: String) {
        deleteFileDir(File(dirPath))
    }

    /**
     * 删除文件夹 - 递归删除
     * @param dirFiLe File 需要删除的文件夹
     */
    @JvmStatic
    fun deleteFileDir(dirFiLe: File) {
        if (!dirFiLe.exists() || !dirFiLe.isDirectory) return
        dirFiLe.listFiles()?.let {
            for (file in it) {
                if (file.isFile) {
                    file.delete()
                } else if (file.isDirectory) {
                    deleteFileDir(file)
                }
            }
        }
        dirFiLe.delete()
    }


    /**
     * 根据路径获取外部存储文件
     * @param filePath String 外部存储文件路径
     * @param needDeleteOriginal Boolean 文件存在是否需要删除原文件
     * @return File
     */
    @JvmStatic
    @Nullable
    @JvmOverloads
    fun getExternalFile(filePath: String, needDeleteOriginal: Boolean = false): File {
        val targetFile = File(filePath)
        targetFile.parent?.let {
            createFileDir(it)
        }
        if (needDeleteOriginal && targetFile.exists()) {
            targetFile.delete()
        }
        return targetFile
    }

    /**
     * 将 Assets 文件拷贝到应用作用域存储
     *
     * @param assetsFilePath String Assets-资源路径
     * @param targetFilePath String 本地存储路径
     */
    @JvmStatic
    fun copyAssetsFile(context: Context, assetsFilePath: String, targetFilePath: String): Boolean {
        val targetFile = getExternalFile(targetFilePath, true)
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open(assetsFilePath)
            return writeToFile(targetFile, inputStream)
        } catch (e: IOException) {
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
            }
        }
        return false
    }


    /**
     * 将 Assets 文件拷贝到应用作用域存储
     *
     * @param assetsDir String Assets-资源文件夹
     * @param targetDir String 本地存储目录
     */
    @JvmStatic
    fun copyAssetsDir(context: Context, assetsDir: String, targetDir: String): Int {
        val assetsFiles = context.assets.list(assetsDir) ?: return 0
        createFileDir(targetDir)
        var hasCoryFileCount = 0
        assetsFiles.forEach { fileName ->
            if (!fileName.contains(".")) {
                if (assetsDir.isEmpty()) {
                    copyAssetsDir(context, fileName, targetDir + fileName + File.separator)
                } else {
                    copyAssetsDir(context, assetsDir + File.separator + fileName, targetDir + File.separator + fileName)
                }
            } else {
                val res = copyAssetsFile(context, assetsDir + File.separator + fileName, targetDir + File.separator + fileName)
                if (res) {
                    hasCoryFileCount += 1
                }
            }
        }
        return hasCoryFileCount
    }

    /**
     * 存储空间文件拷贝
     *
     * @param sourceFilePath 原始文件路径
     * @param targetFilePath 目标文件路径
     */
    @JvmStatic
    fun copyExternalFile(sourceFilePath: String, targetFilePath: String): Boolean {
        val sourceFile = File(sourceFilePath)
        val targetFile = getExternalFile(targetFilePath, true)
        if (!sourceFile.exists() || sourceFile.isDirectory) {
            return false
        }
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(sourceFile)
            return writeToFile(targetFile, inputStream)
        } catch (e: FileNotFoundException) {
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
            }
        }
        return false
    }

    /**
     * 存储空间文件夹拷贝
     *
     * @param sourceDirPath 原始文件夹路径
     * @param targetDirPath 目标文件夹路径
     * @return
     */
    @JvmStatic
    fun copyExternalDir(sourceDirPath: String, targetDirPath: String): Int {
        val sourceDir = File(sourceDirPath)
        if (!sourceDir.exists() || !sourceDir.isDirectory) {
            return 0
        }
        val sourceFiles = sourceDir.listFiles() ?: return 0
        var hasCoryFileCount = 0
        createFileDir(targetDirPath)
        for (file in sourceFiles) {
            if (file.isDirectory) {
                hasCoryFileCount += copyExternalDir(file.path + File.separator, targetDirPath + File.separator + file.name + File.separator)
            } else {
                val res = copyExternalFile(file.path, targetDirPath + File.separator + file.name)
                if (res) {
                    hasCoryFileCount += 1
                }
            }
        }
        return hasCoryFileCount
    }


    /**
     * 根据 Uri 获取文件绝对路径
     *
     * @param uri  Uri
     * @return 绝对路径
     */
    @JvmStatic
    @Nullable
    fun getAbsolutePathByUri(context: Context, uri: Uri): String? {
        val sdkVersion = Build.VERSION.SDK_INT
        if (sdkVersion < 11) return getRealPathFromUriBelowApi11(context, uri)
        return if (sdkVersion < 19) getRealPathFromUriApi11To18(context, uri) else getRealPathFromUriAboveApi19(context, uri)
    }


    /**
     * 根据 Uri 获取 File 对象
     * @param uri Uri
     * @return File?
     */
    fun getFileByUri(context: Context, uri: Uri): File? {
        val path = getAbsolutePathByUri(context, uri) ?: return null
        val file = File(path)
        if (!file.exists()) return null
        return file
    }


    /**
     * 判断文件是否存在
     * @param filePath String 文件路径
     * @return Boolean true:存在 false:不存在
     */
    @JvmStatic
    fun isExists(context: Context, filePath: String): Boolean {
        if (TextUtils.isEmpty(filePath)) return false
        val file = File(filePath)
        if (file.exists()) return true
        try {
            context.assets.open(filePath)
        } catch (e: IOException) {
            return false
        }
        return true
    }

    /**
     * 判断文件是否存在本地 SD 卡目录
     * @param filePath String 文件路径
     * @return Boolean true:存在 false:不存在
     */
    @JvmStatic
    fun isStorageExists(filePath: String): Boolean {
        if (TextUtils.isEmpty(filePath)) return false
        val file = File(filePath)
        return file.exists()
    }

    /**
     * 判断文件是否存在 Assets 目录
     * @param filePath String 文件路径
     * @return Boolean true:存在 false:不存在
     */
    fun isAssetsExists(context: Context, filePath: String): Boolean {
        try {
            context.assets.open(filePath)
        } catch (e: IOException) {
            return false
        }
        return true
    }


    /**
     * 适配api19以上,根据uri获取图片的绝对路径
     */
    private fun getRealPathFromUriAboveApi19(context: Context, uri: Uri): String? {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val contentUri: Uri = when (split[0]) {
                    "image" -> {
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    }
                    "video" -> {
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    }
                    "audio" -> {
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    else -> {
                        MediaStore.Files.getContentUri("external")
                    }
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    /**
     * 适配api11-api18,根据uri获取图片的绝对路径
     */
    private fun getRealPathFromUriApi11To18(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        //这个有两个包不知道是哪个。。。。不过这个复杂版一般用不到
        val loader = CursorLoader(context, uri, projection, null, null, null)
        val cursor = loader.loadInBackground()
        if (cursor != null) {
            cursor.moveToFirst()
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]))
            cursor.close()
        }
        return filePath
    }

    /**
     * 适配api11以下(不包括api11),根据uri获取图片的绝对路径
     */
    private fun getRealPathFromUriBelowApi11(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]))
            cursor.close()
        }
        return filePath
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private fun getDataColumn(context: Context, uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = MediaStore.MediaColumns.DATA
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(columnIndex)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }


    //endregion 文件管理

    //region 文件读写
    /**
     * 根据路径获取 InputStream
     *
     * @param filePath String 文件绝对路径
     * @return InputStream? 数据流
     */
    @JvmStatic
    @Nullable
    fun readInputStreamByPath(context: Context,filePath: String): InputStream? {
        if (TextUtils.isEmpty(filePath)) return null
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open(filePath)
        } catch (e1: IOException) {
            try {
                inputStream = FileInputStream(filePath)
            } catch (e2: IOException) {
            }
        }
        return inputStream
    }

    /**
     * 根据路径获取 ByteArray
     *
     * @param filePath String 文件绝对路径
     * @return ByteArray?
     */
    @JvmStatic
    @Nullable
    fun readByteArrayByPath(context: Context, filePath: String): ByteArray? {
        if (TextUtils.isEmpty(filePath)) return null
        var inputStream: InputStream? = null
        try {
            inputStream = readInputStreamByPath(context, filePath)
            if (inputStream != null) {
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                return buffer
            }
        } catch (e: IOException) {
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
            }
        }
        return null
    }

    /**
     * 读取 json 类型文件，根据路径获取字段键值对
     *
     * @param filePath String 文件绝对路径
     * @return HashMap<String, Any?>? 键值对
     */
    @JvmStatic
    @Nullable
    fun readJSONObjectMapByPath(context: Context, filePath: String): HashMap<String, Any?>? {
        if (TextUtils.isEmpty(filePath)) return null
        val jsonContent = readContentByPath(context, filePath)
        if (TextUtils.isEmpty(jsonContent)) return null
        val paramMap = HashMap<String, Any?>()
        try {
            val jsonObject = JSONObject(jsonContent!!)
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val obj = jsonObject.opt(key)
                paramMap[key] = obj
            }
        } catch (e: JSONException) {
            return null
        }
        return paramMap
    }

    /**
     * 读取文本类型文件，根据文件绝对路径获取文本
     * @param filePath String 文件路径
     * @param charsetName String 编码
     * @return String? 文本
     */
    @JvmStatic
    @JvmOverloads
    @Nullable
    fun readContentByPath(context: Context, filePath: String, charsetName: String = "UTF-8"): String? {
        if (TextUtils.isEmpty(filePath)) return null
        val sb = StringBuilder()
        var inputStream: InputStream? = null
        try {
            inputStream = readInputStreamByPath(context, filePath)
            if (inputStream != null) {
                val bufferSize = 1024
                val buffer = CharArray(bufferSize)
                val `in`: Reader = InputStreamReader(inputStream, charsetName)
                while (true) {
                    val rsz = `in`.read(buffer, 0, buffer.size)
                    if (rsz < 0) break
                    sb.append(buffer, 0, rsz)
                }
            }
        } catch (e: IOException) {
            return null
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
            }
        }
        return sb.toString()
    }

    /**
     * 将数据流写入到本地文件
     * @param targetFile File 本地文件
     * @param inputStream InputStream 数据流
     * @return Boolean
     */
    @JvmStatic
    fun writeToFile(targetFile: File, inputStream: InputStream): Boolean {
        var fos: FileOutputStream? = null
        var bos: BufferedOutputStream? = null
        var bis: BufferedInputStream? = null
        try {
            fos = FileOutputStream(targetFile)
            bos = BufferedOutputStream(fos)
            bis = BufferedInputStream(inputStream)
            val byteArray = ByteArray(1024)
            var bytes = bis.read(byteArray)
            while (bytes > 0) {
                bos.write(byteArray, 0, bytes)
                bos.flush()
                bytes = bis.read(byteArray)
            }
            return true
        } catch (e: IOException) {
            return false
        } finally {
            try {
                bos?.close()
            } catch (e: IOException) {
            }
            try {
                fos?.close()
            } catch (e: IOException) {
            }
            try {
                bis?.close()
            } catch (e: IOException) {
            }
        }
    }


    /**
     * 将文本写入到本地文件
     * @param targetFile File 目标文件
     * @param content String 文本
     * @return Boolean 是否写入成功
     */
    @JvmStatic
    fun writeToFile(targetFile: File, content: String): Boolean {
        var outputStream: FileOutputStream? = null
        return try {
            outputStream = FileOutputStream(targetFile)
            outputStream.write(content.toByteArray(charset("utf-8")))
            true
        } catch (e: IOException) {
            false
        } finally {
            try {
                outputStream?.close()
            } catch (e: IOException) {
            }
        }
    }

    /**
     * 将 ByteArray 写入到本地文件
     * @param targetFile File 目标文件
     * @param byteArray ByteArray 数据
     * @return Boolean 是否写入成功
     */
    @JvmStatic
    fun writeToFile(targetFile: File, byteArray: ByteArray): Boolean {
        var outputStream: FileOutputStream? = null
        return try {
            outputStream = FileOutputStream(targetFile)
            outputStream.write(byteArray)
            true
        } catch (e: IOException) {
            false
        } finally {
            try {
                outputStream?.close()
            } catch (e: IOException) {
            }
        }
    }
    //endregion 文件读写
    //region 其他业务

    /**
     * 获取文件的上级目录
     * @param filePath 文件绝对路径
     * @return 文件的上级目录
     */
    @JvmStatic
    fun getFileParentPath(filePath: String): String {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val filePos = filePath.lastIndexOf(File.separator)
        return if (filePos == -1) "" else filePath.substring(0, filePos)
    }

    /**
     * 获取文件后缀
     * @param filePath 文件绝对路径
     * @return 文件后缀
     */
    @JvmStatic
    fun getFileExtension(filePath: String): String {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val extensionPos = filePath.lastIndexOf(".")
        val filePos = filePath.lastIndexOf(File.separator)
        if (extensionPos == -1) {
            return ""
        }
        return if (filePos >= extensionPos) "" else filePath.substring(extensionPos + 1)
    }

    /**
     * 获取文件名称（包含后缀）
     * @param filePath 文件绝对路径
     * @return 文件名（包含后缀）
     */
    @JvmStatic
    fun getFileName(filePath: String): String {
        if (TextUtils.isEmpty(filePath)) {
            return ""
        }
        val filenamePos = filePath.lastIndexOf(File.separatorChar)
        return if (filenamePos >= 0) filePath.substring(filenamePos + 1) else filePath
    }

    /**
     * 获取文件名称（不包含后缀）
     * @param filePath 文件绝对路径
     * @return 文件名（不包含后缀）
     */
    @JvmStatic
    fun getFileShortName(filePath: String): String {
        val fileName = getFileName(filePath)
        val separatorIndex = fileName.lastIndexOf('.')
        return if (separatorIndex > 0) fileName.substring(0, separatorIndex) else fileName
    }
}