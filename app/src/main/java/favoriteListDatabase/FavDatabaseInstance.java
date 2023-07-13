package favoriteListDatabase;

import android.app.Application;

import androidx.room.Room;

public class FavDatabaseInstance {
    private static FavDatabaseInstance instance = null;
    private FavoriteDatabase favoriteDatabase;

    private FavDatabaseInstance(Application application){
        favoriteDatabase =  Room.databaseBuilder(
                        application.getApplicationContext(),
                        FavoriteDatabase.class,
                        "FavoriteDB").
                allowMainThreadQueries().build();
    }

    public static FavDatabaseInstance getInstance(Application application){
        synchronized (FavDatabaseInstance.class){
            if (instance == null){
                instance = new FavDatabaseInstance(application);
            }

            return instance;
        }
    }

    public FavoriteDatabase getTasksDatabase(){
        return favoriteDatabase;
    }
}
