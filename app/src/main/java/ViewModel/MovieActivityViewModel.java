package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import favoriteListDatabase.builder.Favorite;
import repository.MovieRepository;

public class MovieActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<List<Favorite>> listMutableLiveData;

    private List<Favorite> favoriteList;

    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        listMutableLiveData = new MutableLiveData<>();
    }

    public void addFav(Favorite favorite){
        movieRepository.addTask(favorite);
    }

    public LiveData<List<Favorite>> getFavMovieList(){
        return movieRepository.getFavs();
    }

    public LiveData<Integer> favCount(){
        return movieRepository.getCount();
    }



}
