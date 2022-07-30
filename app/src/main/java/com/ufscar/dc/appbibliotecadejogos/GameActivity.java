package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.squareup.picasso.Picasso;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityGameBinding;
import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.models.Genre;
import com.ufscar.dc.appbibliotecadejogos.models.Platform;
import com.ufscar.dc.appbibliotecadejogos.recyclers.CardsRecyclerView;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Game game = (Game) bundle.getSerializable("game");

        binding.nome.setText(game.getName());
        if (game.getRelease_dates().get(0) != null)
            binding.ReleaseDate.setText(game.getRelease_dates().get(0).getRelease());
        for (Platform p: game.getPlatforms()) {
            binding.Plataforms.setText(p.getPlatform_name());
        }
        for (Genre p: game.getGenres()) {
            binding.Genres.setText(p.getGenre_name());
        }
        if (game.getRating() != null)
            binding.Rating.setText(game.getRating().toString());
        binding.description.setText(game.getDescription());
        String gameUrl = game.getCover().getUrl();
        if(gameUrl!=null) {
            gameUrl = "https:" + gameUrl.replace("t_thumb", "t_cover_big");
        }
        Picasso
                .get()
                .load(gameUrl)
                .error(R.drawable.image_not_found)
                .into(binding.imageView);

        binding.AddCollection.setOnClickListener(view -> {
                    Log.d("teste", "teste_button");
                });
/*
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.details(game.getId());

        mainViewModel.getGame().observe(this, gameDetails -> {
            String gameUrl = game.getCover().getUrl();
            if(gameUrl!=null) {
                gameUrl = "https:" + gameUrl.replace("t_thumb", "t_cover_big");
            }
            Log.d("teste", gameDetails.get(0).getName());
            binding.nome.setText(game.getName());
            //binding.ReleaseDate.setText(game.getRelease_dates().get(0).getRelease());
            //binding.Rating.setText((String) game.getRating());
            Picasso
                    .get()
                    .load(gameUrl)
                    .error(R.drawable.image_not_found)
                    .into(binding.imageView);

            binding.AddCollection.setOnClickListener(view -> {
                Log.d("teste","teste_button");
            });
        });*/
    }
}