package com.ufscar.dc.appbibliotecadejogos.services;

import android.util.Log;

import com.ufscar.dc.appbibliotecadejogos.models.Game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {
    public static void getGames(String name, GamesCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        String field = "fields name,rating,cover.url,release_dates.human,genres.name,platforms.name,summary;" +
                "search \"" + name + "\"; limit 20;";

        Log.d("teste", field);

        Call<List<Game>> call = client.searchGame(field);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.code()==200) {
                    Log.e("teste", String.valueOf(response.body()));
                    try {
                        Log.e("teste", "try");
                        cb.onSuccess(response.body());
                    } catch (Exception e) {
                        Log.e("teste", "erro");
                    }
                } else {
                    Log.e("teste", "json is null");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                cb.onError(t.getMessage());
                //Log.d("body", t.getMessage());

            }
        });
    }

    public static void getRecomendacoes(GamesCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        Date date = new Date();
        //This method returns the time in millis
        int timeMilli = (int) (date.getTime()/1000);
        String field = "fields name,rating,cover.url,release_dates.human,genres.name,platforms.name,summary;" +
                " where follows > 10 & first_release_date < " + timeMilli + "; limit 50; sort first_release_date desc;";

        Log.d("teste", field);

        Call<List<Game>> call = client.searchRecommends(field);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.code()==200) {
                    Log.e("teste", String.valueOf(response.body()));
                    try {
                        Log.e("teste", "try");
                        cb.onSuccess(response.body());
                    } catch (Exception e) {
                        Log.e("teste", "erro");
                    }
                } else {
                    Log.e("teste", "json is null");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                //Log.d("body", t.getMessage());
                cb.onError(t.getMessage());
            }
        });
    }

    public static void getSalvos(ArrayList<Integer> collection, GamesCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < collection.size(); i++) {
            if (i == collection.size()-1)
                ids.append(collection.get(i));
            else
                ids.append(collection.get(i)).append(",");
        }
        String field = "fields name,rating,cover.url,release_dates.human,genres.name,platforms.name,summary;" +
                " where id = (" + ids + ");";

        Log.d("teste", field);

        Call<List<Game>> call = client.searchGame(field);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.code()==200) {
                    Log.e("teste", String.valueOf(response.body()));
                    try {
                        Log.e("teste", "try");
                        cb.onSuccess(response.body());
                    } catch (Exception e) {
                        Log.e("teste", "erro");
                    }
                } else {
                    Log.e("teste", "json is null");
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                cb.onError(t.getMessage());
                //Log.d("body", t.getMessage());

            }
        });
    }

    public interface GamesCallback {
        void onSuccess(List<Game> games);

        void onError(String errorMessage);
    }
}