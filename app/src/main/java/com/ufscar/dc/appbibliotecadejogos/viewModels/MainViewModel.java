package com.ufscar.dc.appbibliotecadejogos.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.services.GameRepository;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends ViewModel {
    final MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    final MutableLiveData<List<Game>> games = new MutableLiveData<>();
    final MutableLiveData<List<Game>> salvos = new MutableLiveData<>();
    final MutableLiveData<List<Game>> populares = new MutableLiveData<>();

    public MutableLiveData<Boolean> getShowLoading() {
        return showLoading;
    }

    public MutableLiveData<List<Game>>getGames() {
        return games;
    }

    public MutableLiveData<List<Game>>getSalvos() {
        return salvos;
    }

    public MutableLiveData<List<Game>>getPopulares() {
        return populares;
    }

    public void search(String name) {
        showLoading.setValue(true);
        /*if (!isConnected) {
            errorMessage.setValue("No Internet connection. Try again later");
            return;
        }*/
        GameRepository.getGames(name, new GameRepository.GamesCallback() {
            @Override
            public void onSuccess(List<Game> list_games) {
                showLoading.setValue(false);
                //Log.d("teste", list_games.get(0).getName());
                games.setValue(list_games);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
    public void recommended() {
        showLoading.setValue(true);
        /*if (!isConnected) {
            errorMessage.setValue("No Internet connection. Try again later");
            return;
        }*/
        GameRepository.getRecomendacoes(new GameRepository.GamesCallback() {
            @Override
            public void onSuccess(List<Game> list_games) {
                showLoading.setValue(false);
                //Log.d("teste", list_games.get(0).getGame().getName());
                populares.setValue(list_games);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
    public void salvos(ArrayList<Integer> collection) {
        showLoading.setValue(true);
        /*if (!isConnected) {
            errorMessage.setValue("No Internet connection. Try again later");
            return;
        }*/
        if (collection.isEmpty()) {
            showLoading.setValue(false);
            Log.d("teste", "vazio");
            return;
        }
        GameRepository.getSalvos(collection, new GameRepository.GamesCallback() {
            @Override
            public void onSuccess(List<Game> list_games) {
                showLoading.setValue(false);
                //Log.d("teste", list_games.get(0).getGame().getName());
                salvos.setValue(list_games);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}