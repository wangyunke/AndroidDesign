# -*- coding: utf-8 -*-

import zipfile
import os
import glob

print("Hello, World!")


def zip_directory(source_dir, zip_name):
    exclude_dirs = []
    """
    将指定文件夹压缩为 ZIP 文件
    :param source_dir: 要压缩的文件夹路径（例如 "./my_folder"）
    :param zip_name: 输出的 ZIP 文件名（例如 "output.zip"）
    """
    with zipfile.ZipFile(zip_name, 'w', zipfile.ZIP_DEFLATED) as zipf:
        for root, dirs, files in os.walk(source_dir):
            for file in files:
                file_path = os.path.join(root, file)
                path = os.path.relpath(file_path, start=source_dir)
                print(f'write from {file_path} to {path}')
                zipf.write(file_path, arcname=path)


extract_to="."
zip_ref = zipfile.ZipFile("speech-hcp3-b-1.2-common-1.5-20241130083941.zip")
print("start extract files-------")
zip_ref.extractall(extract_to)

path = os.path.abspath(extract_to)
print("Files extract success, path="+path)

# speechFile = glob.glob(path+"/speech")

zip_directory(path+"/speech", "SpeechDB.zip")
print("zip success")