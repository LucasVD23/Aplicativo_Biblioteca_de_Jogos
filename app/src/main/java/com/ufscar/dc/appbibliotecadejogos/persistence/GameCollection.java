package com.ufscar.dc.appbibliotecadejogos.persistence;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class GameCollection {
    @PrimaryKey
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){return id;}
}
