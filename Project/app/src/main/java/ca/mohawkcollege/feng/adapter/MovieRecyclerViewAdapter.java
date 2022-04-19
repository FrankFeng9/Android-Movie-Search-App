package ca.mohawkcollege.feng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import ca.mohawkcollege.feng.R;
import ca.mohawkcollege.feng.databinding.MovieListItemBinding;
import ca.mohawkcollege.feng.model.MovieItem;

/**
 * list/recycler view adapter class
 */
public class MovieRecyclerViewAdapter extends ArrayAdapter<MovieItem> {

    private static final int resource = R.layout.movie_list_item;

    /**
     * Listener called when movie clicked
     */
    private final OnMovieCardClickListener listener;

    /**
     * recycler view adapter
     * @param listener onclick listener
     * @param context  context
     * @param objects  movie object
     */
    public MovieRecyclerViewAdapter(@NonNull OnMovieCardClickListener listener, @NonNull Context context, @NonNull List<MovieItem> objects) {
        super(context, resource, objects);
        this.listener = listener;
    }

    /**
     * check view inflated and render
     * @param position  movie item position
     * @param convertView  view
     * @param parent view parent
     * @return view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) { // check if view is inflated already, if null, render it
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView; // rendered already, re-use it
        }

        MovieListItemBinding binding = MovieListItemBinding.bind(view);
        MovieItem movie = getItem(position); // get current item

        binding.movieItemTitle.setText(movie.getTitle());
        binding.movieItemYear.setText(movie.getYear());

        binding.getRoot().setTransitionName(view.getContext().getString(R.string.open_details_transition_name) + "$" + position);
        binding.getRoot().setOnClickListener((v) -> listener.onItemClick(binding.getRoot(), movie));
        Glide.with(view.getContext()).load(movie.getPoster()).into(binding.movieItemPicture);

        return view;
    }
}