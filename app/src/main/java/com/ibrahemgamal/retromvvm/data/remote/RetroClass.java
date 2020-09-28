package com.ibrahemgamal.retromvvm.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit getRetrofit() {
            return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
    }
    public static APIService getAPIService(){
        return getRetrofit().create(APIService.class);
    }
}
