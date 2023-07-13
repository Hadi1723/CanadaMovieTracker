package com.example.popmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.popmovies.databinding.ActivityMainBinding;

import java.util.List;

import ViewModel.FavListViewModel;
import adpater.ListAdapter;
import favoriteListDatabase.builder.Favorite;

public class FavoriteMovieList extends AppCompatActivity {

    private TextView count;
    private RecyclerView recyclerView;

    private FavListViewModel favListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie_list);

        favListViewModel = new ViewModelProvider(this).get(FavListViewModel.class);

        count = findViewById(R.id.favCount);
        recyclerView = findViewById(R.id.listShow);

        favListViewModel.getFavMoviesCouny().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                count.setText("Total Movies: " + Integer.toString(integer));
            }
        });

        favListViewModel.getAllFavMovies().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                recyclerView.setAdapter(new ListAdapter(favorites, FavoriteMovieList.this, favListViewModel.getMovieRepository()));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();

        startActivity(new Intent(FavoriteMovieList.this, MainActivity.class));
        return super.onOptionsItemSelected(item);


    }

}