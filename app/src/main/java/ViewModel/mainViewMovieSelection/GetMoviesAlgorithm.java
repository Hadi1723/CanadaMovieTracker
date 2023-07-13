package ViewModel.mainViewMovieSelection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bridge.FIFOCollection;
import model.Movie;
import repository.MovieRepository;

public interface GetMoviesAlgorithm {

    public MutableLiveData<FIFOCollection<Movie>> getMovies(MovieRepository movieRepository);

    public String getTitle();
}
