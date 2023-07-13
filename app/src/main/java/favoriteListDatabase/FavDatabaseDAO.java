package favoriteListDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import favoriteListDatabase.builder.Favorite;

@Dao
public interface FavDatabaseDAO {
    @Insert
    public long addFav(Favorite task);

    @Update
    public void updateFav(Favorite task);

    @Query("DELETE FROM FavoriteMovies WHERE movie_id=:id")
    public void deleteFav(int id);


    @Query("select * from FavoriteMovies order by movie_name")
    public List<Favorite> getFav();

    @Query("select count(*) from FavoriteMovies")
    public int getCount();
}




