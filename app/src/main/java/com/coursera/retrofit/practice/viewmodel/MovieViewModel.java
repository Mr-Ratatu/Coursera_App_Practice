package com.coursera.retrofit.practice.viewmodel;

import android.app.Application;

import com.coursera.retrofit.practice.data.model.ResponceBody;
import com.coursera.retrofit.practice.data.model.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.coursera.retrofit.practice.Constant.*;

public class MovieViewModel extends AndroidViewModel {

    private MutableLiveData<ResponceBody> moviesLiveData;
    private final MovieRepository movieRepository;
    private LiveData<List<Result>> listLiveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        movieRepository = MovieRepository.getInstance(application);

        moviesLiveData = movieRepository.getMoviesNewsApi(API_KEY);
        listLiveData = movieRepository.getListLiveData();
    }

    public MutableLiveData<ResponceBody> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void insert(List<Result> resultList) {
        movieRepository.insertMovie(resultList);
    }

    public LiveData<List<Result>> getListLiveData() {
        return listLiveData;
    }
}
