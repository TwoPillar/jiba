package com.twopillar.jiba.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils
{

    public static final String PREFERENCES_NAME = "cfg"; // 配置文件名称

    /**
     * 1.int写入
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void putIntPreferences(Context context, String key, int value)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 2.int获取
     * 
     * @param context
     * @param key
     * @return 若为空默认0
     */
    public static int getIntPreferences(Context context, String key, int defaultValue)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * int获取
     * 默认值为-1
     */
    public static int getIntPreferences(Context context, String key)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, -1);
    }

    /**
     * 3.String 写入
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void putStringPreferences(Context context, String key, String value)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 4.String 获取
     * 
     * @param context
     * @param key
     */
    public static String getStringValue(Context context, String key, String defValue)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defValue);
    }

    /**
     * String 获取
     * 默认值为空字符串
     */
    public static String getStringValue(Context context, String key)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    /**
     * 5.boolean 写入
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void putBooleanPreferences(Context context, String key, boolean value)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 
     * boolean 获取
     * 默认值为false
     * */
    public static boolean getBooleanPreferences(Context context, String key)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    /**
     * 6.boolean 获取
     * 
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBooleanPreferences(Context context, String key, boolean defValue)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defValue);
    }

    /**
     * 7.Long 写入
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void putLongPreferences(Context context, String key, long value)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 8.Long 获取
     * 
     * @param context
     * @param key
     * @return
     */
    public static long getLongPreferences(Context context, String key, Long defValue)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defValue);
    }

    /**
     * Long 获取
     * 默认值为-1
     * 
     */
    public static long getLongPreferences(Context context, String key)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, -1);
    }

}
