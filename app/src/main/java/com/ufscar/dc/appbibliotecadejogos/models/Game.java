package com.ufscar.dc.appbibliotecadejogos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

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

    @SerializedName("rating")
    @Expose
    private Float rating;

    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> release_dates;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("platforms")
    @Expose
    private List<Platform> platforms;

    @SerializedName("summary")
    @Expose
    private String description;

    public Float getRating(){return rating;}

    public void setRating(Float rating){this.rating = rating;}

    public void setRelease_dates(List<ReleaseDate>release_dates){ this.release_dates = release_dates;}

    public List<ReleaseDate> getRelease_dates(){ return release_dates;}

    public void setGenres(List<Genre> genres) { this.genres = genres;}

    public List<Genre> getGenres(){ return genres;}

    public void setPlatforms(List<Platform> platforms) { this.platforms = platforms;}

    public List<Platform> getPlatforms(){ return platforms;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}
}