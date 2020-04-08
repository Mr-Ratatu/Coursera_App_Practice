package com.coursera.retrofit.practice.data.db;

import com.coursera.retrofit.practice.data.model.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Result> resultList);

    @Query("SELECT * FROM movie_db")
    LiveData<List<Result>> getAllMovies();
}
