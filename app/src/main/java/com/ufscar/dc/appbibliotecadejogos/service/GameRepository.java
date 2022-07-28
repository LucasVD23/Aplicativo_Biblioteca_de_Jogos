package com.ufscar.dc.appbibliotecadejogos.service;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {
    public static void getGames(String name, GamesCallback cb){
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

    public static void getGameDetails(Integer id, GameCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        String field = "fields name; where id = " + id;
        //Log.d("teste", field);

        Call<Game> call = client.searchGameDetails(field);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                //Log.d("body", (response.body()).toString());
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

    public interface GamesCallback {
        public void onSuccess(List<Game> games);

        public void onError(String errorMessage);
    }

    public interface GameCallback {
        public void onSuccess(Game game);

        public void onError(String errorMessage);
    }
}