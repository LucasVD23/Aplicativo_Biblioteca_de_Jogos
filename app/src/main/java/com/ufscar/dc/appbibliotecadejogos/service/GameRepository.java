package com.ufscar.dc.appbibliotecadejogos.service;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {
    public static void getGame(String name, GamesCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        String field = "fields name,cover.url; search \"" + name + "\"; limit 20;";
        //Log.d("teste", field);

        Call<List<Game>> call = client.searchGame(field);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //Log.d("body", (response.body()).toString());
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public interface GamesCallback {
        public void onSuccess(List<Game> games);

        public void onError(String errorMessage);
    }
}
