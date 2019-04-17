package com.androidproject.apidata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KoreanJsonService {
    @GET("/v1/events")
    Call<Example> listPosts(@Query("country")String country);
//    Call<Example> listPosts(@Query("q") String query);
}
