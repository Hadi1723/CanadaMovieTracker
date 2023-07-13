package adpater;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popmovies.MovieActivity;
import com.example.popmovies.R;
import com.example.popmovies.databinding.MovieListItemBinding;

import Observer.*;

import bridge.FIFOCollection;
import model.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private Context context;
    private FIFOCollection<Movie> movieArrayList;

    public MovieAdapter(Context context, FIFOCollection<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding  = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()),
                        R.layout.movie_list_item,
                        parent,
                        false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieArrayList.get(position);
        holder.movieListItemBinding.setMovie(movie);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.getSize();
    }


    // View Holder Class
    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        Movie selectedMovie = movieArrayList.get(position);

                        Observer observer = ObserverImpl.getInstance();
                        Subject subject = SubjectImpl.getInstance();

                        observer.setSubject(subject);
                        subject.register(observer);

                        subject.postTask(selectedMovie);



                        Intent i = new Intent(context, MovieActivity.class);

                        //i.putExtra("movie", selectedMovie);
                        context.startActivity(i);

                    }


                }
            });

        }
    }






}

