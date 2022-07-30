package com.ufscar.dc.appbibliotecadejogos.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ufscar.dc.appbibliotecadejogos.CardsRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.MainViewModel;
import com.ufscar.dc.appbibliotecadejogos.R;
import com.ufscar.dc.appbibliotecadejogos.databinding.GamefragmentBinding;
import com.ufscar.dc.appbibliotecadejogos.service.GameRepository;

public class GameFragment extends Fragment {
    private GamefragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = GamefragmentBinding.inflate(inflater, container, false);

        mainViewModel.getGames().observe(getViewLifecycleOwner(), game -> {
            if (game != null)
                binding.nome.setText(game.get(0).getName());
        });

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String id = bundle.get("id").toString();
            //Log.d("teste", id);
            mainViewModel.details(id);
        }

        return binding.getRoot();
    }

}
