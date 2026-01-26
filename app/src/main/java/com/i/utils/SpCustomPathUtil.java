package com.i.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SpCustomPathUtil {
    private static String fileName;
    private static String filePath;
    private static Context context;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void init(Context cxt, String path, String name) {
        filePath = path;
        fileName = name;
        context = cxt;
        Log.e("SpCustomPathUtil", "init filePath=" + filePath + ",fileName=" + fileName);
    }

    @SuppressLint("DiscouragedPrivateApi")
    public static void put(String key, Object data) {
        lock.writeLock().lock();
        try {
            Field field;
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);

            Object obj = field.get(context);
            if (obj == null) return;
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);

            File file = new File(filePath);
            field.set(obj, file);

            SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            String type = data.getClass().getSimpleName();

            switch (type) {
                case "Integer" -> editor.putInt(key, (Integer) data);
                case "Boolean" -> editor.putBoolean(key, (Boolean) data);
                case "String" -> editor.putString(key, (String) data);
                case "Float" -> editor.putFloat(key, (Float) data);
                case "Long" -> editor.putLong(key, (Long) data);
            }
            editor.apply();
        } catch (Exception e) {
            Log.e("SpCustomPathUtil", "putData Exception=" + e.getMessage());
        }
        lock.writeLock().unlock();
    }

    @SuppressLint("DiscouragedPrivateApi")
    public static Object get(String key, Object defValue) {
        lock.readLock().lock();
        try {
            Field field;
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);

            Object obj = field.get(context);
            if (obj == null) return defValue;
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);

            File file = new File(filePath);
            field.set(obj, file);

            SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            String type = defValue.getClass().getSimpleName();

            return switch (type) {
                case "Integer" -> sp.getInt(key, (Integer) defValue);
                case "Boolean" -> sp.getBoolean(key, (Boolean) defValue);
                case "String" -> sp.getString(key, (String) defValue);
                case "Float" -> sp.getFloat(key, (Float) defValue);
                case "Long" -> sp.getLong(key, (Long) defValue);
                default -> null;
            };
        } catch (Exception e) {
            Log.e("SpCustomPathUtil", "getData Exception=" + e.getMessage());
            return defValue;
        } finally {
            lock.readLock().unlock();
        }
    }

}
