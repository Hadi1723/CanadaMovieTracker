package favoriteListDatabase.builder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import favoriteListDatabase.builder.FavDTo;

@Entity(tableName = "FavoriteMovies")
public class Favorite implements FavDTo {

    @ColumnInfo(name = "movie_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_name")
    private String name;

    @ColumnInfo(name = "movie_image")
    private String image;

    @ColumnInfo(name = "movie_rating")
    private Double rating;

    public Favorite(){}

    @Ignore
    public Favorite(int id, String name, String image, Double rating) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
