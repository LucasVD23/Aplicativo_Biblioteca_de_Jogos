package com.ufscar.dc.appbibliotecadejogos.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameAPIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.igdb.com/v4/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            
        }

        return retrofit;
    }
}
