package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;
import com.ufscar.dc.appbibliotecadejogos.service.Game;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getShowLoading().observe(this, show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });

        int numberOfColumns = 2;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        MainViewModel myViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        myViewModel.getGames().observe(this, list_games -> {
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(
                    MainActivity.this,
                    list_games
            );
            binding.recyclerView.setAdapter(myRecyclerViewAdapter);
        });

        binding.newButton.setOnClickListener(v -> {
            String jogo = binding.pesquisarJogo.getText().toString();
            mainViewModel.search(jogo);
        });
    }
}