package com.example.loginapp.api;


import com.example.loginapp.models.LoginRequest;
import com.example.loginapp.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/auth/login/")
    Call<User> loginUser(@Body LoginRequest loginRequest);
}
