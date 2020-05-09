package com.example.projecthouyudoglist;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projecthouyudoglist.data.DogAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static DogAPI dogApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGsonInstance(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static DogAPI getDogApiInstance(){
        if(dogApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .build();

            dogApiInstance = retrofit.create(DogAPI.class);
        }
        return dogApiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context) {
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
