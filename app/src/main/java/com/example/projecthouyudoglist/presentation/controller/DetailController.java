package com.example.projecthouyudoglist.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projecthouyudoglist.Constants;
import com.example.projecthouyudoglist.Singletons;
import com.example.projecthouyudoglist.presentation.model.Dog;
import com.example.projecthouyudoglist.presentation.model.RestDogResponse;
import com.example.projecthouyudoglist.presentation.view.DetailActivity;
import com.example.projecthouyudoglist.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private DetailActivity view;


    public DetailController(SharedPreferences sharedPreferences, Gson gson, DetailActivity view) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.view = view;
    }

    public void onStart(){
        String status = getStatusFromCache();

        if(status != null){
            view.showStatus(status);
        }else {
            MakeAPICall();
        }
    }

    private void MakeAPICall(){

        Call<RestDogResponse> call = Singletons.getDogApiInstance().getBreedResponse();
        call.enqueue(new Callback<RestDogResponse>() {
            @Override
            public void onResponse(Call<RestDogResponse> call, Response<RestDogResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    String status = response.body().getStatus();
                    Toast.makeText(view.getApplicationContext(),"API success", Toast.LENGTH_SHORT).show();
                    saveStatus(status);
                    view.showStatus(status);
                }else{
                    view.NoDetailInformation();
                }
            }


            @Override
            public void onFailure(Call<RestDogResponse> call, Throwable t) {
                view.NoDetailInformation();
            }
        });
    }

    private void saveStatus(String status) {
        String jsonString = gson.toJson(status);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_STATUS, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(),"Status Saved", Toast.LENGTH_SHORT).show();
    }


    private String getStatusFromCache() {
        String jsonStatus = sharedPreferences.getString(Constants.KEY_STATUS, null);

        if(jsonStatus == null){
            return null;
        }else{
            Type statusType = new TypeToken<String>(){}.getType();
            return gson.fromJson(jsonStatus, statusType);
        }
    }

//    public void onItemClick(Dog dog){
//        view.navigateToDetails(dog);
//    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
