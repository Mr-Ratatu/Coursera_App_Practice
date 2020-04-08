package com.coursera.retrofit.practice.data.network;

import com.coursera.retrofit.practice.data.model.ResponceBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceFlickr {
    @GET("3/movie/top_rated")
    Call<ResponceBody> getMoviesApi(@Query("api_key") String api_key);
}
