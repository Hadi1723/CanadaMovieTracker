package com.example.popmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popmovies.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import ViewModel.MainViewModel;
import adpater.MovieAdapter;
import bridge.FIFOCollection;
import model.Movie;

public class MainActivity extends AppCompatActivity {
    private FIFOCollection<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainActivityViewModel;

    private ActivityMainBinding activityMainBinding;

    private MutableLiveData<String> pageName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Movie Pro App");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        pageName = mainActivityViewModel.getPageName();
        pageName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                activityMainBinding.pageTitle.setText(s);
            }
        });


        getMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });

    }

    private void getMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<FIFOCollection<Movie>>() {
            @Override
            public void onChanged(FIFOCollection<Movie> moviesFromLiveData) {
                movies = moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });


    }

    private void ShowOnRecyclerView() {

        recyclerView = activityMainBinding.rvMovies;
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

    // 2- Adding the Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.favorite_movies){
            startActivity(new Intent(this, FavoriteMovieList.class));
        } else{
            if (itemID == R.id.popular) {
                mainActivityViewModel.setGetMoviesAlgorithm("Popular Movies");
            } else if (itemID == R.id.running) {
                mainActivityViewModel.setGetMoviesAlgorithm("Currently Running Movies");
            } else if (itemID == R.id.upcoming){
                mainActivityViewModel.setGetMoviesAlgorithm("Upcoming Movies");
            }

            getMovies();
        }


        return super.onOptionsItemSelected(item);


    }


}