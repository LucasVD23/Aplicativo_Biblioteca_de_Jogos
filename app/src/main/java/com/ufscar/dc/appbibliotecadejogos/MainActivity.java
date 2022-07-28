package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;
import com.ufscar.dc.appbibliotecadejogos.service.Game;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //mainViewModel.getGames().observe(this, binding.textView::setText);
        mainViewModel.getShowLoading().observe(this, show -> {
            binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        });
        mainViewModel.getGames().observe(this, games -> {
            binding.container.removeAllViews();
            for (Game q: games) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(q.getName() + "/n");
                binding.container.addView(tv);
            }
        });

        binding.newButton.setOnClickListener(v -> {
            //String characterName = binding.personNameEditText.getText().toString();
            mainViewModel.search("mario kart");
        });
    }
}