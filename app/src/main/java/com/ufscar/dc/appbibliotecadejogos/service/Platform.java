package com.ufscar.dc.appbibliotecadejogos.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Platform implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("platform")
    @Expose
    String platform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) { this.platform = platform;}
    @Override
    public String toString(){
        return id + " - Plataforma: " + platform;
    }
}
