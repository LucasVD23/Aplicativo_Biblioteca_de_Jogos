package com.ufscar.dc.appbibliotecadejogos.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ufscar.dc.appbibliotecadejogos.MainViewModel;
import com.ufscar.dc.appbibliotecadejogos.databinding.GamefragmentBinding;

public class GameFragment extends Fragment {
    private GamefragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainViewModel mainViewModel =
                new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = GamefragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        
        return root;
    }

}
