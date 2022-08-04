package com.ufscar.dc.appbibliotecadejogos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ufscar.dc.appbibliotecadejogos.databinding.FragmentCollectionBinding;
import com.ufscar.dc.appbibliotecadejogos.recyclers.CardsRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CollectionFragment extends Fragment {

    private @NonNull FragmentCollectionBinding binding;
    private MainViewModel mainViewModel;
    private CardsRecyclerView cardsRecyclerViewZerados;
    private CardsRecyclerView cardsRecyclerViewQuero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCollectionBinding.inflate(inflater, container, false);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(getViewLifecycleOwner(), show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        binding.recyclerViewZerados.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        MainViewModel myViewModelZerados = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModelZerados.getZerados().observe(getViewLifecycleOwner(), list_games -> {
            cardsRecyclerViewZerados = new CardsRecyclerView(
                    getActivity(),
                    list_games
            );
            binding.recyclerViewZerados.setAdapter(cardsRecyclerViewZerados);
        });

        binding.recyclerViewQuero.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        MainViewModel myViewModelQuero = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModelQuero.getQueroJogar().observe(getViewLifecycleOwner(), list_games -> {
            cardsRecyclerViewQuero = new CardsRecyclerView(
                    getActivity(),
                    list_games
            );
            binding.recyclerViewQuero.setAdapter(cardsRecyclerViewQuero);
        });

        myViewModelZerados.zerados(loadCollection("zerados"));
        myViewModelQuero.quero_jogar(loadCollection("quero_jogar"));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private ArrayList<Integer> loadCollection(String name) {
        SharedPreferences preferences = this.requireActivity().getSharedPreferences("game_collection",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("collection_" + name, null);

        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        ArrayList<Integer> game_collection = gson.fromJson(json, type);

        if (game_collection == null) {
            // if the array list is empty
            // creating a new array list.
            game_collection = new ArrayList<>();
        }
        return (game_collection);
    }
}