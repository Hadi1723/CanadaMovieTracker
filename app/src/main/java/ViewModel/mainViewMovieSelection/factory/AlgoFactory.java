package ViewModel.mainViewMovieSelection.factory;

import ViewModel.mainViewMovieSelection.GetNowMovies;
import ViewModel.mainViewMovieSelection.GetPopMovies;
import ViewModel.mainViewMovieSelection.GetUpMovies;

public class AlgoFactory implements Factory{
    @Override
    public Object span(String item) {
        if (item == "Popular Movies"){
            return new GetPopMovies(item);
        } else if (item == "Upcoming Movies"){
            return new GetUpMovies(item);
        } else {
            return new GetNowMovies("Currently Playing Movies");
        }
    }
}
