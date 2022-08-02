package com.ufscar.dc.appbibliotecadejogos.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface GameCollectionDao {

    // select all scores and observes for changes
    @Query("SELECT * FROM games")
    public Flowable<List<GameCollection>> getAllScores();

    // return when the insertion is completed
    @Insert
    public Completable insertGameCollection(GameCollection gameCollection);

    // return the number of rows affected
    @Query("DELETE FROM games")
    public Single<Integer> deleteAllGames();

    // select all scores
    @Query("DELETE FROM games WHERE id == :id")
    public Single<Integer> deleteGame(int id);

}
