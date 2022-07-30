package com.ufscar.dc.appbibliotecadejogos.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReleaseDate implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("release_date")
    @Expose
    String release_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) { this.release_date = release_date;}
    @Override
    public String toString(){
        return id + " - Release: " + release_date;
    }

}

