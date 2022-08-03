package com.ufscar.dc.appbibliotecadejogos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lancamento {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("game")
    @Expose
    private Game game;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Lancamento.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("game");
        sb.append('=');
        sb.append(((this.game == null)?"<null>":this.game));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}