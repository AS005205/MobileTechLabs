package com.dima.labwork3.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dima.labwork3.interfaces.DAO;
import com.dima.labwork3.pojo.MovieDetails;

@Database(entities = {MovieDetails.class}, version = 1, exportSchema = false)
public abstract class MoviesDB extends RoomDatabase {
    private static MoviesDB INSTANCE;

    public static MoviesDB getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MoviesDB.class, "MovieDB")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public abstract DAO moviesDAO();

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

