package com.thereza.coinmarketcap.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by theReza on 2/18/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
