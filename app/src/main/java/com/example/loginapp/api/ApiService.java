package com.example.loginapp.api;


import com.example.loginapp.models.LoginRequest;
import com.example.loginapp.models.Resena;
import com.example.loginapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/auth/login/")
    Call<User> loginUser(@Body LoginRequest loginRequest);

    @GET("/api/resenas/")
    Call<List<Resena>> getResenas(@Header("Authorization") String token);
}
