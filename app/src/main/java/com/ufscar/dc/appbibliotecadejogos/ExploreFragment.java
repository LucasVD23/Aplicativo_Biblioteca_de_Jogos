package com.ufscar.dc.appbibliotecadejogos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.ufscar.dc.appbibliotecadejogos.databinding.FragmentExploreBinding;

public class ExploreFragment extends Fragment {

    private FragmentExploreBinding binding;
    private MainViewModel mainViewModel;
    private CardsRecyclerView cardsRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExploreBinding.inflate(inflater, container, false);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(getViewLifecycleOwner(), show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        int numberOfColumns = 2;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        MainViewModel myViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModel.getGames().observe(getViewLifecycleOwner(), list_games -> {
            cardsRecyclerView = new CardsRecyclerView(
                    getActivity(),
                    list_games
            );
            binding.recyclerView.setAdapter(cardsRecyclerView);
        });

        // Pesquisa pelo teclado
        binding.pesquisarJogo.setOnEditorActionListener((tv, actionId, keyEvent) -> {
            boolean handled = false;

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mainViewModel.search(tv.getText().toString());
                handled = true;
            }

            return handled;
        });

        // Pesquisa pelo botão
        binding.newButton.setOnClickListener(v -> {
            String jogo = binding.pesquisarJogo.getText().toString();
            mainViewModel.search(jogo);
        });

        return binding.getRoot();

    }
}