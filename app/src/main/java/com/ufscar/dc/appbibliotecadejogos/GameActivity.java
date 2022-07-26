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
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;
    ArrayList<Integer> collection_zerados;
    ArrayList<Integer> collection_quero_jogar;

    private ArrayList<Integer> loadCollection(String name) {
        SharedPreferences preferences = getSharedPreferences("game_collection",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("collection_" + name, null);

        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        ArrayList<Integer> game_collection = gson.fromJson(json, type);

        if (game_collection == null) {
            // if the array list is empty
            // creating a new array list.
            game_collection = new ArrayList<>();
        }
        return (game_collection);
    }

    private void saveCollection(int saved, String name, ArrayList<Integer> collection){
        SharedPreferences sharedPreferences = getSharedPreferences("game_collection", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(collection);

        editor.putString("collection_" + name, json);

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
        getSupportActionBar().hide();

        Game game = (Game) bundle.getSerializable("game");

        if (game.getName() != null)
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
                binding.Rating.setTextColor(0xffc21000);
            else if (game.getRating() > 50 && game.getRating() < 75)
                binding.Rating.setTextColor(0xffffa500);
            else if (game.getRating() >= 75)
                binding.Rating.setTextColor(0xff00a000);
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
        Picasso.get()
                .load(gameUrl)
                .error(R.drawable.image_not_found)
                .into(binding.imageView2);

        collection_zerados = loadCollection("zerados");
        collection_quero_jogar = loadCollection("quero_jogar");
        analisaCollections(collection_quero_jogar, game, binding.AddCollectionQuero, "quero_jogar");
        analisaCollections(collection_zerados, game, binding.AddCollectionZerados, "zerados");
    }

    public void analisaCollections(ArrayList<Integer> collection, Game game, android.widget.Button button, String name){
        AtomicInteger saved = new AtomicInteger();
        if(collection.contains(game.getId())){
            saved.set(1);
            button.setBackgroundColor(Color.parseColor("#7A716E"));
            button.setText(getString(R.string.remove_collection));
        }

        button.setOnClickListener(view -> {
            if(saved.get() == 0){
                collection.add(game.getId());
                Log.d("teste","saved");
                saveCollection(saved.get(), name, collection);
                button.setBackgroundColor(Color.parseColor("#7A716E"));
                button.setText(getString(R.string.remove_collection));
                saved.set(1);
            }else{
                collection.remove(game.getId());
                Log.d("teste","deleted");
                saveCollection(saved.get(), name, collection);
                button.setBackgroundColor(Color.parseColor("#F44336"));
                if (name.equals("zerados"))
                    button.setText(getString(R.string.add_collection_completed));
                else
                    button.setText(getString(R.string.add_collection_wish_list));
                saved.set(0);
            }

        });
    }
}