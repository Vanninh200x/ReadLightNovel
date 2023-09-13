package com.example.readlightnovel.utils.desa;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class DataController {
    public DataController() {
    }

    private static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static void setData(Context context, String key, String data) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public static String getData(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getString(key, "");
    }

    public static String getData(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getString(key, defaultValue);
    }

    public static void setData(Context context, String key, boolean trueOrFalse) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, trueOrFalse);
        editor.apply();
    }

    public static boolean getData(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setData(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getData(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getInt(key, defaultValue);
    }

    public static void setData(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getData(Context context, String key, float defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getFloat(key, defaultValue);
    }

    public static void setData(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getData(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getLong(key, defaultValue);
    }

    public static void setData(Context context, String key, Set<String> setString) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, setString);
        editor.apply();
    }

    public static Set<String> getData(Context context, String key, Set<String> defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), 0);
        return sp.getStringSet(key, defaultValue);
    }
}
