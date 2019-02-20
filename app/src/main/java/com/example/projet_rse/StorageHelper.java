package com.example.projet_rse;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StorageHelper {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private SharedPreferences StoragePreferences;

    public StorageHelper(Context context) {
        StoragePreferences = context.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
    }

    public UserAccount GetUserAccount(){
        Gson gson = new Gson();
        String json = StoragePreferences.getString("userAccount", "");
        UserAccount obj = gson.fromJson(json, UserAccount.class);
        return obj;
    }

    public void StoreUserAccount(UserAccount userAccount){

        SharedPreferences.Editor prefsEditor = StoragePreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userAccount);
        prefsEditor.putString("userAccount", json);
        prefsEditor.commit();
    }

    public List<History> GetHistories(){
        Gson gson = new Gson();
        String json = StoragePreferences.getString("Histories", "");
        Type type = new TypeToken<List<History>>() {}.getType();
        List<History> obj = gson.fromJson(json, type);
        return obj;
    }

    public void StoreHistories(List<History> histories){

        SharedPreferences.Editor prefsEditor = StoragePreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(histories);
        prefsEditor.putString("Histories", json);
        prefsEditor.commit();
    }

    public void StoreHistory(History history){

        List<History> histories = GetHistories();
        histories.add(history);
        StoreHistories(histories);

    }

    public void InitData(){

        UserAccount userAccount = new UserAccount("DUPONT", "Marcel", "28 rue Dupont \n 75020 Paris","azerty","m.dupont@gmail.com");
        StoreUserAccount(userAccount);

        List<History> histories = new ArrayList<History>();

        for(int i=0; i < 10;i++){
            histories.add(new History(new RandomDateGenerator().Generate(),Integer.toString(i)+1,"25 rue du Pont \n 75050 PARIS"));
        }
        StoreHistories(histories);

    }
}
