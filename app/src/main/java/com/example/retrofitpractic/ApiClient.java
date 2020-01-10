package com.example.retrofitpractic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(){

        if(mRetrofit == null){
            mRetrofit =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return mRetrofit;
    }
}
