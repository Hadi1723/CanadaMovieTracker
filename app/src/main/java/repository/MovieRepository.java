package repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.popmovies.R;

import java.util.ArrayList;
import java.util.List;

import bridge.FIFOCollection;
import bridge.Queue;
import bridge.SinglyLinkedList;
import favoriteListDatabase.FavDatabaseInstance;
import favoriteListDatabase.builder.Favorite;
import model.Movie;
import model.NowResult;
import model.PopResult;
import model.UpcomingResult;
import retrofit.MovieDataService;
import retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {

    private FIFOCollection<Movie> movies = new Queue<>(new SinglyLinkedList<>());
    private MutableLiveData<FIFOCollection<Movie>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Favorite>> mutableFavData = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableCountData = new MutableLiveData<>();

    private Application application;
    private FavDatabaseInstance favDatabaseInstance;

    public MovieRepository(Application application) {
        this.application = application;
        favDatabaseInstance = FavDatabaseInstance.getInstance(application);

    }

    public void addTask(Favorite favorite){
        long id = favDatabaseInstance.getTasksDatabase().getTaskDAO()
                .addFav(favorite);
    }

    public LiveData<List<Favorite>> getFavs(){
        mutableFavData.postValue(favDatabaseInstance.getTasksDatabase().getTaskDAO().getFav());
        return mutableFavData;
    }

    public LiveData<Integer> getCount(){
        mutableCountData.postValue(favDatabaseInstance.getTasksDatabase().getTaskDAO().getCount());
        return mutableCountData;
    }

    public void deleteFav(int id){
        favDatabaseInstance.getTasksDatabase().getTaskDAO().deleteFav(id);
    }


    public MutableLiveData<FIFOCollection<Movie>> getPopLiveData(){

        movies.removeAll();

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<PopResult> call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key), "CA");

        call.enqueue(new Callback<PopResult>() {
            @Override
            public void onResponse(Call<PopResult> call, Response<PopResult> response) {
                PopResult result = response.body();

                if (result != null && result.getResults() != null){

                    for (Movie movie : result.getResults()){
                        movies.offer(movie);
                    }

                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<PopResult> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<FIFOCollection<Movie>> getNowLiveData(){

        movies.removeAll();

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<NowResult> call = movieDataService.getRunningMovies(application.getApplicationContext().getString(R.string.api_key), "CA");

        call.enqueue(new Callback<NowResult>() {
            @Override
            public void onResponse(Call<NowResult> call, Response<NowResult> response) {
                NowResult result = response.body();

                if (result != null && result.getResults() != null){

                    for (Movie movie : result.getResults()){
                        movies.offer(movie);
                    }

                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<NowResult> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<FIFOCollection<Movie>> getFutureLiveData(){

        movies.removeAll();

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<UpcomingResult> call = movieDataService.getUpcomingMovies(application.getApplicationContext().getString(R.string.api_key), "CA");

        call.enqueue(new Callback<UpcomingResult>() {
            @Override
            public void onResponse(Call<UpcomingResult> call, Response<UpcomingResult> response) {
                UpcomingResult result = response.body();

                if (result != null && result.getResults() != null){

                    for (Movie movie : result.getResults()){
                        movies.offer(movie);
                    }

                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<UpcomingResult> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }



}

