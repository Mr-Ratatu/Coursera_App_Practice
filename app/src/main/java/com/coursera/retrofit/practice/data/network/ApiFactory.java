package com.coursera.retrofit.practice.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiFactory instance;
    private Retrofit retrofit;
    private final static String MOVIE_BASE_URL = "http://api.themoviedb.org/";

    public ApiFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiFactory getInstance() {
        if (instance == null) {
            instance = new ApiFactory();
        }

        return instance;
    }

    public ApiServiceFlickr getApiServiceFlickr() {
        return retrofit.create(ApiServiceFlickr.class);
    }
}

