package com.thereza.coinmarketcap.model;

/**
 * Created by theReza on 2/18/2018.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.coinmarketcap.com";

    public static APiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APiService.class);
    }
}