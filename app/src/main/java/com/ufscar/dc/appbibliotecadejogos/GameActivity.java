package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityGameBinding;
import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.models.Genre;
import com.ufscar.dc.appbibliotecadejogos.models.Platform;
import com.ufscar.dc.appbibliotecadejogos.viewModels.MainViewModel;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    private MainViewModel mainViewModel;
    ArrayList<Integer> collection;


    private ArrayList<Integer> loadCollection(){
        SharedPreferences preferences = getSharedPreferences("game_collection",Context.MODE_PRIVATE);
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

    private void saveCollection(int saved){
        SharedPreferences sharedPreferences = getSharedPreferences("game_collection", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(collection);

        editor.putString("collection", json);

        editor.apply();
        String msg = (saved == 0? getString(R.string.added_warning) : getString(R.string.removed_warning));
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Game game = (Game) bundle.getSerializable("game");

        binding.nome.setText(game.getName());
        if (game.getRelease_dates() != null)
            binding.ReleaseDate.setText(game.getRelease_dates().get(0).getRelease());

        StringBuilder platforms = new StringBuilder();
        StringBuilder genres = new StringBuilder();

        if (game.getPlatforms() != null) {
            for (Platform p : game.getPlatforms()) {
                platforms.append(" - ").append(p.getPlatform_name()).append("\n");
            }
            binding.Plataforms.setText(platforms.toString());
        }
        if (game.getGenres() != null) {
            for (Genre p : game.getGenres()) {
                genres.append(" - ").append(p.getGenre_name()).append("\n");
            }
            binding.Genres.setText(genres.toString());
        }
        if (game.getRating() != null) {
            binding.Rating.setText(String.valueOf(Math.round(game.getRating())));
            if (game.getRating() <= 50)
                binding.Rating.setBackgroundColor(0xffc21000);
            else if (game.getRating() > 50 && game.getRating() < 75)
                binding.Rating.setBackgroundColor(0xffffff00);
            else if (game.getRating() >= 75)
                binding.Rating.setBackgroundColor(0xff00a000);
        }
        if (game.getDescription() != null) {
            binding.description.setText(game.getDescription());
        }
        String gameUrl = null;
        if (game.getCover() != null){
            gameUrl = game.getCover().getUrl();
            if(gameUrl!=null) {
                gameUrl = "https:" + gameUrl.replace("t_thumb", "t_cover_big");
            }
        }
        Picasso
                .get()
                .load(gameUrl)
                .error(R.drawable.image_not_found)
                .into(binding.imageView);


        collection = loadCollection();

        AtomicInteger saved = new AtomicInteger();
        if(collection.contains(game.getId())){
            saved.set(1);
            binding.AddCollection.setBackgroundColor(Color.parseColor("#7A716E"));
            binding.AddCollection.setText(getString(R.string.remove_collection));
        }


        binding.AddCollection.setOnClickListener(view -> {
            if(saved.get() == 0){
                collection.add(game.getId());
                Log.d("teste","saved");
                saveCollection(saved.get());
                binding.AddCollection.setBackgroundColor(Color.parseColor("#7A716E"));
                binding.AddCollection.setText(getString(R.string.remove_collection));
                saved.set(1);
            }else{
                collection.remove(game.getId());
                Log.d("teste","deleted");
                saveCollection(saved.get());
                binding.AddCollection.setBackgroundColor(Color.parseColor("#F44336"));
                binding.AddCollection.setText(getString(R.string.add_collection));
                saved.set(0);
            }

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