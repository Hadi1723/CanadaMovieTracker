package ViewModel.mainViewMovieSelection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bridge.FIFOCollection;
import model.Movie;
import repository.MovieRepository;

public class GetPopMovies implements GetMoviesAlgorithm{

    private String title;

    public GetPopMovies(String title) {
        this.title = title;
    }

    @Override
    public MutableLiveData<FIFOCollection<Movie>> getMovies(MovieRepository movieRepository) {
        return movieRepository.getPopLiveData();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
