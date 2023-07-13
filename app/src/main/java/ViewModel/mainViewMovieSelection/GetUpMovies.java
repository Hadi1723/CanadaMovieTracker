package ViewModel.mainViewMovieSelection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bridge.FIFOCollection;
import model.Movie;
import repository.MovieRepository;

public class GetUpMovies implements GetMoviesAlgorithm{

    private String title;

    public GetUpMovies(String title) {
        this.title = title;
    }

    @Override
    public MutableLiveData<FIFOCollection<Movie>> getMovies(MovieRepository movieRepository) {
        return movieRepository.getFutureLiveData();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
