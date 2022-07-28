package com.ufscar.dc.appbibliotecadejogos;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ufscar.dc.appbibliotecadejogos.service.Game;
import com.ufscar.dc.appbibliotecadejogos.service.GameRepository;

import java.util.List;


public class MainViewModel extends ViewModel {
    final MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    final MutableLiveData<List<Game>> games = new MutableLiveData<>();
    final MutableLiveData<Game> game = new MutableLiveData<>();

    public MutableLiveData<Boolean> getShowLoading() {
        return showLoading;
    }

    public MutableLiveData<List<Game>>getGames() {
        return games;
    }

    public void search(String name) {
        showLoading.setValue(true);
        GameRepository.getGames(name, new GameRepository.GamesCallback() {
            @Override
            public void onSuccess(List<Game> list_games) {
                showLoading.setValue(false);
                //Log.d("teste", list_games.toString());
                games.setValue(list_games);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    public void details(Integer id) {
        showLoading.setValue(true);
        GameRepository.getGameDetails(id, new GameRepository.GameCallback() {
            @Override
            public void onSuccess(Game selected_game) {
                showLoading.setValue(false);
                game.setValue(selected_game);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}