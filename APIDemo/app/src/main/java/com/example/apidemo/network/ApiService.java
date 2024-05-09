package com.example.apidemo.network;

import com.example.apidemo.models.DataModels;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/create")
    Call<DataModels> postData(@Body DataModels DataModels);

}
