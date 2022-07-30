package com.ufscar.dc.appbibliotecadejogos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.squareup.picasso.Picasso;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityGameBinding;
import com.ufscar.dc.appbibliotecadejogos.databinding.ActivityMainBinding;
import com.ufscar.dc.appbibliotecadejogos.service.Game;

public class GameActivity extends AppCompatActivity {

    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Game game = (Game) bundle.getSerializable("game");
        String gameUrl = game.getCover().getUrl();
        if(gameUrl!=null) {
            gameUrl = "https:" + gameUrl.replace("t_thumb", "t_cover_big");
        }

        binding.nome.setText(game.getName());
        binding.ReleaseDate.setText(game.getRelease_dates().get(0).getRelease());
        //binding.Rating.setText((String) game.getRating());
        Picasso
                .get()
                .load(gameUrl)
                .error(R.drawable.image_not_found)
                .into(binding.imageView);

        binding.AddCollection.setOnClickListener(view -> {
            Log.d("teste","teste_button");
        });
    }


}