package com.ufscar.dc.appbibliotecadejogos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ufscar.dc.appbibliotecadejogos.service.Cover;
import com.ufscar.dc.appbibliotecadejogos.service.Game;

import java.util.List;

public class CardsRecyclerView extends RecyclerView.Adapter<CardsRecyclerView.ViewHolder> {

    private List<Game> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CardsRecyclerView(Context context, List<Game> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cover cover = getItem(position).getCover();
        String gameUrl = null;
        Game game = getItem(position);
        Log.d("pos", position + "");
        if(cover != null){
            gameUrl = "https:" + cover.getUrl().replace("t_thumb", "t_cover_big");
        }
        /*else {
            gameUrl = "https://static.vecteezy.com/ti/vetor-gratis/p3/3052919-ilustracao-jogo-stick-controlador-cartoon-vetor.jpg";
        }*/
        holder.viewNome.setText(game.getName());
        //Log.d("teste", game.getId().toString());
        Picasso
                .get()
                .load(gameUrl)
                .error(R.drawable.image_not_found)
                .into(holder.mGameCover);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView viewNome;
        ImageView mGameCover;

        ViewHolder(View itemView) {
            super(itemView);
            mGameCover = itemView.findViewById(R.id.viewCover);
            viewNome = itemView.findViewById(R.id.viewNome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());*/
            Game game = getItem(getAdapterPosition());

            Intent intent = new Intent(view.getContext(), GameActivity.class);

            //setResult(RESULT_OK,intent);
            Bundle bundle = new Bundle();
            bundle.putSerializable("game", game);
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);

            /*
            String id = game.getId().toString();
            Bundle bundle = new Bundle();
            bundle.putString("id", id);

            GameFragment fragment = new GameFragment();
            fragment.setArguments(bundle);

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();*/
        }
    }

    // convenience method for getting data at click position
    Game getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
