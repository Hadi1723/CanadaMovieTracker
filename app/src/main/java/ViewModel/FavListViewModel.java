package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import favoriteListDatabase.builder.Favorite;
import model.Movie;
import repository.MovieRepository;

public class FavListViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;


    public FavListViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    // Live Data
    public LiveData<List<Favorite>> getAllFavMovies(){
        return movieRepository.getFavs();
    }

    // Live Data
    public LiveData<Integer> getFavMoviesCouny(){
        return movieRepository.getCount();
    }

    public MovieRepository getMovieRepository(){
        return movieRepository;
    }


}
