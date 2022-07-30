package com.ufscar.dc.appbibliotecadejogos.service;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Game implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> release_dates;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("platforms")
    @Expose
    private List<Platform> platforms;


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

    public void setRelease_dates(List<ReleaseDate>release_dates){ this.release_dates = release_dates;}

    public List<ReleaseDate> getRelease_dates(){ return release_dates;}

    public void setGenres(List<Genre> genres) { this.genres = genres;}

    public List<Genre> getGenres(){ return genres;}

    public void setPlatforms(List<Platform> platforms) { this.platforms = platforms;}

    public List<Platform> getPlatforms(){ return platforms;}


}