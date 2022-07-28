package com.ufscar.dc.appbibliotecadejogos.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String toString(){
        return id + " - Nome: " + name;
    }
}