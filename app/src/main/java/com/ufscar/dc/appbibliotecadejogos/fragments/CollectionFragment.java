package com.ufscar.dc.appbibliotecadejogos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ufscar.dc.appbibliotecadejogos.databinding.FragmentHomeBinding;
import com.ufscar.dc.appbibliotecadejogos.recyclers.CardsRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CollectionFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;
    private CardsRecyclerView cardsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(getViewLifecycleOwner(), show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        int numberOfColumns = 2;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        MainViewModel myViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModel.getSalvos().observe(getViewLifecycleOwner(), list_games -> {
            cardsRecyclerView = new CardsRecyclerView(
                    getActivity(),
                    list_games
            );
            binding.recyclerView.setAdapter(cardsRecyclerView);
        });

        mainViewModel.salvos(loadCollection());

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private ArrayList<Integer> loadCollection() {
        SharedPreferences preferences = this.requireActivity().getSharedPreferences("game_collection",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("collection", null);

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