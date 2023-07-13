package favoriteListDatabase.builder;

public interface FavDTOBuilder {
    //methods to build "parts" of product at a time
    FavDTOBuilder withName(String name) ;

    FavDTOBuilder withImage(String image);

    FavDTOBuilder withRating(Double rating);

    //method to "assemble" final product
    FavDTo build();
    //(optional) method to fetch already built object
    FavDTo getTaskDTO();
}
