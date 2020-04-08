package com.coursera.retrofit.practice.data.db;

import android.content.Context;

import com.coursera.retrofit.practice.data.model.Result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {

    private final static String DB_NAME = "movie_db";
    private final static int NUMBER_OF_THREADS = 4;

    private static MovieDataBase instance;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MovieDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (MovieDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, MovieDataBase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract MoviesDao getMovieDao();
}
