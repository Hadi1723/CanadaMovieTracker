package ViewModel.mainViewMovieSelection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bridge.FIFOCollection;
import model.Movie;
import repository.MovieRepository;

public class GetNowMovies implements GetMoviesAlgorithm{

    private String title;

    public GetNowMovies(String title) {
        this.title = title;
    }

    @Override
    public MutableLiveData<FIFOCollection<Movie>> getMovies(MovieRepository movieRepository) {
        return movieRepository.getNowLiveData();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
