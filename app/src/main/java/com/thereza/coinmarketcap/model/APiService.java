package com.thereza.coinmarketcap.model;

import com.thereza.coinmarketcap.data.CoinData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by theReza on 2/18/2018.
 */

public interface APiService {
    @GET("/v1/ticker")
    Call<List<CoinData>> getCoinData();
}
