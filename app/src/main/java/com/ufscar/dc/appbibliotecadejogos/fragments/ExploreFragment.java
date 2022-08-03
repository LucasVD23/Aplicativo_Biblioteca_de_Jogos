package com.ufscar.dc.appbibliotecadejogos.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.ufscar.dc.appbibliotecadejogos.recyclers.CardsRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;
import com.ufscar.dc.appbibliotecadejogos.databinding.FragmentExploreBinding;

import java.util.Timer;
import java.util.TimerTask;

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

        final TextWatcher textWatcherSearchListener = new TextWatcher() {
            final android.os.Handler handler = new android.os.Handler();
            Runnable runnable;

            public void onTextChanged(final CharSequence s, int start, final int before, int count) {
                handler.removeCallbacks(runnable);
            }

            @Override
            public void afterTextChanged(final Editable s) {
                // ira executar apos um tempo de pausa na digitação
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        mainViewModel.search(binding.pesquisarJogo.getText().toString());
                    }
                };
                handler.postDelayed(runnable, 500);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        };

        // Pesquisa pelo teclado
        showKeyboard();
        binding.pesquisarJogo.addTextChangedListener(textWatcherSearchListener);
        binding.pesquisarJogo.setOnEditorActionListener((tv, actionId, keyEvent) -> {
            boolean handled = false;

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Fazendo o teclado sumir depois de pressionado o botão de busca
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                // Realizando a busca pelos jogos
                //mainViewModel.search(tv.getText().toString());
                handled = true;
            }

            return handled;
        });

        return binding.getRoot();

    }

    private void showKeyboard() {
        binding.pesquisarJogo.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    };
}