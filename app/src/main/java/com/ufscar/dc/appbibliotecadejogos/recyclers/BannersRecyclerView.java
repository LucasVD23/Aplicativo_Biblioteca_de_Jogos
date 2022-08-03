package com.ufscar.dc.appbibliotecadejogos.recyclers;

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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ufscar.dc.appbibliotecadejogos.GameActivity;
import com.ufscar.dc.appbibliotecadejogos.R;
import com.ufscar.dc.appbibliotecadejogos.models.Cover;
import com.ufscar.dc.appbibliotecadejogos.models.Game;
import com.ufscar.dc.appbibliotecadejogos.models.Lancamento;

import java.util.List;

public class BannersRecyclerView extends RecyclerView.Adapter<BannersRecyclerView.ViewHolder> {

    private List<Lancamento> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public BannersRecyclerView(Context context, List<Lancamento> data) {
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
        Cover cover = getItem(position).getGame().getCover();
        String gameUrl = null;
        Lancamento lancamento = getItem(position);
        Log.d("pos", position + "");
        if(cover != null){
            gameUrl = "https:" + cover.getUrl().replace("t_thumb", "t_cover_big");
        }
        /*else {
            gameUrl = "https://static.vecteezy.com/ti/vetor-gratis/p3/3052919-ilustracao-jogo-stick-controlador-cartoon-vetor.jpg";
        }*/
        holder.viewNome.setText(lancamento.getGame().getName());
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
            Lancamento lancamento = getItem(getAdapterPosition());

            Intent intent = new Intent(view.getContext(), GameActivity.class);

            //setResult(RESULT_OK,intent);
            Bundle bundle = new Bundle();
            bundle.putSerializable("game", lancamento.getGame());
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    Lancamento getItem(int id) {
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
