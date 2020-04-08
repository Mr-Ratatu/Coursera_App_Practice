package com.coursera.retrofit.practice.data.network;

import com.coursera.retrofit.practice.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiFactory instance;
    private Retrofit retrofit;
    private final static String MOVIE_BASE_URL = "http://api.themoviedb.org/";

    public ApiFactory() {
        /**Логирование - получаем данные о запросах со всем параметрами, заголовками.*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        /**HttpLoggingInterceptor мне не можем напрямую передать в Retrofit, поэтому
         * создаем OkHttpClient, и уже его добавляем в качестве клиента*/
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
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

