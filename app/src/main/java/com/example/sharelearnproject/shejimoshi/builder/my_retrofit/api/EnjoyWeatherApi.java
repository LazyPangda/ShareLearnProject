package com.example.sharelearnproject.shejimoshi.builder.my_retrofit.api;


import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.annotation.Field;
import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.annotation.GET;
import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.annotation.POST;
import com.example.sharelearnproject.shejimoshi.builder.my_retrofit.annotation.Query;

import okhttp3.Call;

public interface EnjoyWeatherApi {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);


    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
