package com.coursera.retrofit.practice.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coursera.retrofit.practice.R;
import com.coursera.retrofit.practice.adapter.MovieAdapter;
import com.coursera.retrofit.practice.data.model.Result;
import com.coursera.retrofit.practice.viewmodel.MovieViewModel;
import com.coursera.retrofit.practice.viewmodel.MovieViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Result> list;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerInitialize();

        MovieViewModel flickrViewModel = new ViewModelProvider(this,
                new MovieViewModelFactory(this.getApplication())).get(MovieViewModel.class);

        /**Получаем данные из сети и записываем их в базу данных*/
        flickrViewModel.getMoviesLiveData().observe(this, responceBody -> {
            list = responceBody.getResults();
            movieAdapter.setList(list);
            flickrViewModel.insert(list);
        });

        /**Получаем данные из базы данных*/
        if (flickrViewModel.getListLiveData() != null) {
            flickrViewModel.getListLiveData()
                    .observe(this, resultList -> movieAdapter.setList(resultList));
        }
    }

    private void recyclerInitialize() {
        list = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        movieAdapter = new MovieAdapter(list);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieAdapter);
    }
}
