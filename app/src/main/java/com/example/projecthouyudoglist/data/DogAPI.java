package com.example.projecthouyudoglist.data;

import com.example.projecthouyudoglist.presentation.model.RestDogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogAPI {
    @GET("myAPI.json")
    Call<RestDogResponse> getBreedResponse();
}
