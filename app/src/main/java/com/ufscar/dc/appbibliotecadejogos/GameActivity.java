package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityGameBinding;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nome.setText(getIntent().getExtras().getString("name"));
    }
}