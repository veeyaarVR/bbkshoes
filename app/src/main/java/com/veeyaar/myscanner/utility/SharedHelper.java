package com.veeyaar.myscanner.utility;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedHelper {

    private Activity activity;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String PREF_USER = "user";

    public SharedHelper(Activity activity) {
        this.activity = activity;
    }

    public void storeValue(String key, String value){
        preferences = activity.getSharedPreferences(PREF_USER, Activity.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String retrieveValue(String key) {
        preferences = activity.getSharedPreferences(PREF_USER, Activity.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    
}
