package com.example.projet_rse;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

class StorageHelper {

    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private final SharedPreferences StoragePreferences;

    public StorageHelper(Context context) {
        StoragePreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    }

    public UserAccount GetUserAccount() {
        Gson gson = new Gson();
        String json = StoragePreferences.getString("userAccount", "");
        return gson.fromJson(json, UserAccount.class);
    }

    public void StoreUserAccount(UserAccount userAccount) {

        SharedPreferences.Editor prefsEditor = StoragePreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userAccount);
        prefsEditor.putString("userAccount", json);
        prefsEditor.commit();
    }

    public List<History> GetHistories() {
        Gson gson = new Gson();
        String json = StoragePreferences.getString("Histories", "");
        Type type = new TypeToken<List<History>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private void StoreHistories(List<History> histories) {

        SharedPreferences.Editor prefsEditor = StoragePreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(histories);
        prefsEditor.putString("Histories", json);
        prefsEditor.commit();
    }

    public void StoreHistory(History history) {

        List<History> histories = GetHistories();
        histories.add(history);
        StoreHistories(histories);

    }

    public void InitData() {

        List<History> histories = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            int num = r.nextInt(6 - 1) + 1;
            histories.add(new History(new RandomDateGenerator().Generate(), String.valueOf(num), null));
        }

        UserAccount userAccount = new UserAccount("ILLIG Françoise", "Université Paris-Daphine\nPlace du Maréchal de Lattre de Tassigny\n75016 Paris", "azerty", "f.illig@gmail.com", "0€");
        StoreUserAccount(userAccount);


        StoreHistories(histories);

    }
}
