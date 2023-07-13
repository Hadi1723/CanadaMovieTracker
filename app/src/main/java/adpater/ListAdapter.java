package adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popmovies.FavoriteMovieList;
import com.example.popmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import favoriteListDatabase.builder.Favorite;
import repository.MovieRepository;

public class ListAdapter extends
        RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<Favorite> taskList;
    private Context context;
    private MovieRepository movieRepository;

    public ListAdapter(List<Favorite> taskList, Context context, MovieRepository movieRepository){
        this.taskList = taskList;
        this.context = context;
        this.movieRepository = movieRepository;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView identification;
        public TextView level;
        public ImageView image;
        public Button deleteBTN;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            identification = (TextView) itemView.findViewById(R.id.name);

            level = (TextView) itemView.findViewById(R.id.rating);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            deleteBTN = (Button) itemView.findViewById(R.id.btnDelete);


        }

        public void selectedItem(int pos){
            deleteBTN.setOnClickListener(view -> {
                movieRepository.deleteFav(taskList.get(pos).getId());
                context.startActivity(new Intent(context, FavoriteMovieList.class));
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_model, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Favorite fav = taskList.get(position);

        //Cart model = listModelList.get(position);


        holder.identification.setText(fav.getName());
        holder.level.setText("Rating: " + Double.toString(fav.getRating())+"/10");
        Picasso.get().load("https://image.tmdb.org/t/p/w1280"+fav.getImage()).into(holder.image);

        holder.selectedItem(position);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}