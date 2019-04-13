package com.threeouncecheese.quizzoholic.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.threeouncecheese.quizzoholic.R;

import java.util.Dictionary;
import java.util.Hashtable;

public class SharedPreferencesHelper {

    static String prefName = "Pref";
    private Dictionary<String, Boolean> mapKeyValues = new Hashtable();
    private static SharedPreferencesHelper _sharedPrefHelper = null;

    private SharedPreferencesHelper()
    {

    }

    private boolean ReadBooleanFromSharedPref(Context context, String key, boolean defaultValue)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        boolean value = sharedPref.getBoolean(key, defaultValue);
        mapKeyValues.put(key, value);
        return value;
    }

    public void WriteBooleanToSharedPref(Context context, String key, boolean input)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, input);
        editor.apply();
        mapKeyValues.put(key, input);
    }

    public boolean GetBooleanFromSharedPref(Context context, String key, boolean defaultValue)
    {
        Boolean output = mapKeyValues.get(key);

        if(output == null)
        {
            output = ReadBooleanFromSharedPref(context, key, defaultValue);
        }

        return output;
    }

    public static synchronized SharedPreferencesHelper GetInstance()
    {
        if(_sharedPrefHelper == null)
        {
            synchronized (SharedPreferencesHelper.class) {
                _sharedPrefHelper = new SharedPreferencesHelper();
            }
        }

        return _sharedPrefHelper;
    }
}
