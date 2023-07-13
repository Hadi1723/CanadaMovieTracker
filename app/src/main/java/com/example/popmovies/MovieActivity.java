package com.example.popmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.popmovies.databinding.ActivityMovieBinding;

import java.util.ArrayList;
import java.util.List;

import Observer.*;
import ViewModel.MovieActivityViewModel;
import favoriteListDatabase.builder.FavDTOBuilder;
import favoriteListDatabase.builder.FavDTODataBuilder;
import favoriteListDatabase.builder.FavDTo;
import favoriteListDatabase.builder.Favorite;
import model.Movie;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    private int countRecords;

    private MovieActivityViewModel movieActivityViewModel;

    private List<Favorite> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        Intent i = getIntent();

        movieActivityViewModel = new ViewModelProvider(this).get(MovieActivityViewModel.class);

        movieActivityViewModel.favCount().observe(this, new androidx.lifecycle.Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                countRecords = integer;
            }
        });

        if (i != null) {
            //movie = i.getParcelableExtra("movie");
            Observer observer = ObserverImpl.getInstance();
            Subject subject = SubjectImpl.getInstance();

            movie = (Movie) observer.update();

            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());

        }

        //movieList = movieActivityViewModel.getFavMovieList().getValue();


        movieActivityViewModel.getFavMovieList().observe(MovieActivity.this, new androidx.lifecycle.Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                movieList = favorites;
            }
        });

        activityMovieBinding.addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavDTOBuilder favDTOBuilder = new FavDTODataBuilder();
                FavDTo favDTo = favDTOBuilder.withImage(movie.getPosterPath()).withName(movie.getTitle()).withRating(movie.getVoteAverage()).build();


                if (countRecords > 0) {
                    if (!isContain((Favorite) favDTo)) {
                        Toast.makeText(MovieActivity.this, "Added: " + favDTo.getName(), Toast.LENGTH_SHORT).show();

                        movieActivityViewModel.addFav((Favorite) favDTo);
                        startActivity(new Intent(MovieActivity.this, MovieActivity.class));

                    } else {
                        Toast.makeText(MovieActivity.this, "Already Added ", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(MovieActivity.this, "Added: " + favDTo.getName(), Toast.LENGTH_SHORT).show();

                    movieActivityViewModel.addFav((Favorite) favDTo);
                    startActivity(new Intent(MovieActivity.this, MovieActivity.class));

                }


            }
        });

    }

    private Boolean isContain(Favorite favorite){

        for (Favorite movie : movieList){
            //Toast.makeText(this, movie.getName() + " and " + favorite.getName(), Toast.LENGTH_SHORT).show();
            if (movie.getName().equals(favorite.getName())){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();

        startActivity(new Intent(MovieActivity.this, MainActivity.class));
        return super.onOptionsItemSelected(item);


    }
}