package com.ufscar.dc.appbibliotecadejogos.services;

import android.os.Build;
import android.util.Log;

import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.models.Lancamento;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
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
                //Log.d("body", response.body().toString());
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                cb.onError(t.getMessage());
                //Log.d("body", t.getMessage());

            }
        });
    }

    public static void getLancamentos(LancamentosCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        Date date = new Date();
        //This method returns the time in millis
        int timeMilli = (int) (date.getTime()/1000);
        String field = "fields game.name,game.rating,game.cover.url,game.release_dates.human,game.genres.name,game.platforms.name,game.summary; where date < " + timeMilli + "; limit 50; sort date desc;";

        Log.d("teste", field);

        Call<List<Lancamento>> call = client.searchLancamentos(field);
        call.enqueue(new Callback<List<Lancamento>>() {
            @Override
            public void onResponse(Call<List<Lancamento>> call, Response<List<Lancamento>> response) {
                if (response.code()==200) {
                    Log.e("Here Arrived ID", String.valueOf(response.body()));
                    try {
                        Log.e("teste", "try");

                    } catch (Exception e) {
                        Log.e("teste", "erro");
                    }
                } else {
                    Log.e("teste", "json is null");
                }
                cb.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Lancamento>> call, Throwable t) {
                //Log.d("body", t.getMessage());
                cb.onError(t.getMessage());
            }
        });
    }

    public static void getSalvos(GamesCallback cb){
        GameInterface client = GameAPIClient
                .getClient()
                .create(GameInterface.class);

        String ids = "1";
        String field = "fields name,rating,cover.url,release_dates.human,genres.name,platforms.name,summary; where id = (" + ids + ");";
        Log.d("teste", field);

        Call<List<Game>> call = client.searchGame(field);
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //Log.d("body", response.body().toString());
                cb.onSuccess(response.body());
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

    public interface LancamentosCallback {
        void onSuccess(List<Lancamento> lancamentos);

        void onError(String errorMessage);
    }
}