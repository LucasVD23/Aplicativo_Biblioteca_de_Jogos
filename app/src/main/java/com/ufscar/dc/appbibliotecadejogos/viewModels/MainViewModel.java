package com.ufscar.dc.appbibliotecadejogos.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.services.GameRepository;

import java.util.List;


public class MainViewModel extends ViewModel {
    final MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    final MutableLiveData<List<Game>> games = new MutableLiveData<>();

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
                Log.d("teste", list_games.get(0).getName());
                games.setValue(list_games);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}