package favoriteListDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import favoriteListDatabase.builder.Favorite;


@Database(entities = {Favorite.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    // Linking the DAO with our Database
    public abstract FavDatabaseDAO getTaskDAO();
}
