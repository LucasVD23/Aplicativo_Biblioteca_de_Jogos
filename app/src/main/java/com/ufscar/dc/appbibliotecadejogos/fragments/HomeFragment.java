package com.ufscar.dc.appbibliotecadejogos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufscar.dc.appbibliotecadejogos.databinding.FragmentHomeBinding;
import com.ufscar.dc.appbibliotecadejogos.recyclers.BannersRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;
    private BannersRecyclerView bannersRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(getViewLifecycleOwner(), show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        int numberOfColumns = 2;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        MainViewModel myViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModel.getLancamentos().observe(getViewLifecycleOwner(), list_games -> {
            bannersRecyclerView = new BannersRecyclerView(
                    getActivity(),
                    list_games
            );
            binding.recyclerView.setAdapter(bannersRecyclerView);
        });

        mainViewModel.lancamentos();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}