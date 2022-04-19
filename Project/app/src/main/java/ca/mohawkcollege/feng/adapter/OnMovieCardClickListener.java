package ca.mohawkcollege.feng.adapter;

import android.view.View;

import ca.mohawkcollege.feng.model.MovieItem;

/**
 * movie card onclick listener interface
 */
public interface OnMovieCardClickListener {

    void onItemClick(View itemView, MovieItem data);

}
