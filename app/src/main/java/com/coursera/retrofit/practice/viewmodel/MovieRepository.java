package com.coursera.retrofit.practice.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.coursera.retrofit.practice.data.db.MovieDataBase;
import com.coursera.retrofit.practice.data.db.MoviesDao;
import com.coursera.retrofit.practice.data.model.ResponceBody;
import com.coursera.retrofit.practice.data.model.Result;
import com.coursera.retrofit.practice.data.network.ApiFactory;
import com.coursera.retrofit.practice.data.network.ApiServiceFlickr;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository instance;
    private ApiServiceFlickr apiServiceFlickr;
    private static Context context;

    private MovieDataBase movieDataBase;
    private MoviesDao moviesDao;
    private LiveData<List<Result>> listLiveData;

    /**Обычный сингл метод*/
    public static MovieRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MovieRepository(context);
        }

        return instance;
    }

    public MovieRepository(Context context) {
        apiServiceFlickr = ApiFactory.getInstance().getApiServiceFlickr();
        movieDataBase = MovieDataBase.getInstance(context);

        moviesDao = movieDataBase.getMovieDao();
        listLiveData = moviesDao.getAllMovies();
        MovieRepository.context = context;
    }

    /**Заполняем livedata данными из сети*/
    public MutableLiveData<ResponceBody> getMoviesNewsApi(String api_key) {

        final MutableLiveData<ResponceBody> moviesLiveData = new MutableLiveData<>();

        apiServiceFlickr.getMoviesApi(api_key)
                .enqueue(new Callback<ResponceBody>() {
                    @Override
                    public void onResponse(Call<ResponceBody> call, Response<ResponceBody> response) {
                        moviesLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponceBody> call, Throwable t) {
                        Toast.makeText(context, "Соединение с интернетом отсутствует", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

        return moviesLiveData;

    }

    public LiveData<List<Result>> getListLiveData() {
        return listLiveData;
    }

    /**Метод добавления элементов в базу данных*/
    public void insertMovie(final List<Result> bHabitsModel) {
        MovieDataBase.databaseWriteExecutor.execute(() -> {
            moviesDao.insert(bHabitsModel);
        });
    }
}
