package com.example.projet_rse;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.nio.channels.FileChannel;

import static android.content.Context.MODE_PRIVATE;

public class UserAccountHelper {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private SharedPreferences UserAccountPreferences;

    public UserAccountHelper(Context context) {
        UserAccountPreferences = context.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
    }

    public UserAccount GetUserAccount(){
        Gson gson = new Gson();
        String json = UserAccountPreferences.getString("userAccount", "");
        UserAccount obj = gson.fromJson(json, UserAccount.class);
        return obj;
    }

    public void StoreUserAccount(UserAccount userAccount){

        SharedPreferences.Editor prefsEditor = UserAccountPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userAccount);
        prefsEditor.putString("userAccount", json);
        prefsEditor.commit();

    }
}
