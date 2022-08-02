package com.ufscar.dc.appbibliotecadejogos.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameCollection.class},version = 1)
public abstract  class GameCollectionDatabase extends RoomDatabase {

    private static volatile GameCollectionDatabase INSTANCE;

    public abstract GameCollectionDao gameCollectionDao();

    public static GameCollectionDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GameCollectionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GameCollectionDatabase.class, "collection.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
