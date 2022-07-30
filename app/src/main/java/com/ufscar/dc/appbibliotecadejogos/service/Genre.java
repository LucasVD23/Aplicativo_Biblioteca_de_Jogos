package com.ufscar.dc.appbibliotecadejogos.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Genre implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("genre")
    @Expose
    String genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genres) {this.genre = genre;}

    @Override
    public String toString(){
        return id + " - Genero: " + genre;
    }

}
