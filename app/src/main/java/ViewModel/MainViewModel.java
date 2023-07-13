package ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ViewModel.mainViewMovieSelection.GetMoviesAlgorithm;
import ViewModel.mainViewMovieSelection.GetPopMovies;
import ViewModel.mainViewMovieSelection.factory.AlgoFactory;
import ViewModel.mainViewMovieSelection.factory.Factory;
import bridge.FIFOCollection;
import favoriteListDatabase.builder.Favorite;
import model.Movie;
import repository.MovieRepository;

public class MainViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    private MutableLiveData<String> pageName;

    private Factory factory;
    private GetMoviesAlgorithm getMoviesAlgorithm;


    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        factory = new AlgoFactory();

        pageName = new MutableLiveData<>();

        getMoviesAlgorithm = (GetMoviesAlgorithm) factory.span("Upcoming Movies");
        pageName.postValue(getMoviesAlgorithm.getTitle());
    }

    // Live Data
    public MutableLiveData<FIFOCollection<Movie>> getAllMovies(){
        return getMoviesAlgorithm.getMovies(movieRepository);
    }

    public void setGetMoviesAlgorithm(String name){
        getMoviesAlgorithm = (GetMoviesAlgorithm) factory.span(name);
        pageName.postValue(getMoviesAlgorithm.getTitle());
    }

    public MutableLiveData<String> getPageName(){
        return pageName;
    }

}