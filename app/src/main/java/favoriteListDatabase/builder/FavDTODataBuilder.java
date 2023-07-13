package favoriteListDatabase.builder;

public class FavDTODataBuilder implements FavDTOBuilder{

    private String name;
    private String image;
    private Double rating;
    private FavDTo dto;


    @Override
    public FavDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public FavDTOBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public FavDTOBuilder withRating(Double rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public FavDTo build() {
        Favorite fav = new Favorite();
        fav.setName(name);
        fav.setImage(image);
        fav.setRating(rating);

        dto = (FavDTo) fav;

        return dto;
    }

    @Override
    public FavDTo getTaskDTO() {
        return dto;
    }
}
