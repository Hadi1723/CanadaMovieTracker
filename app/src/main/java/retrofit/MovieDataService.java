package retrofit;

import model.NowResult;
import model.PopResult;
import model.UpcomingResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    // Base Url
    // https://api.themoviedb.org/3/
    //
    // End Point Url
    // movie/popular?api_key=890a86f5656fdca2767b6be3222e3526
    @GET("movie/popular")
    Call<PopResult> getPopularMovies(@Query("api_key") String apiKey, @Query("region") String region);

    @GET("movie/now_playing")
    Call<NowResult> getRunningMovies(@Query("api_key") String apiKey, @Query("region") String region);

    @GET("movie/upcoming")
    Call<UpcomingResult> getUpcomingMovies(@Query("api_key") String apiKey, @Query("region") String region);

}
