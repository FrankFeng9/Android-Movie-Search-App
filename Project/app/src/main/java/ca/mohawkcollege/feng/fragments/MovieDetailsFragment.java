package ca.mohawkcollege.feng.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.transition.MaterialContainerTransform;

import ca.mohawkcollege.feng.R;
import ca.mohawkcollege.feng.databinding.FragmentMovieDetailsBinding;
import ca.mohawkcollege.feng.model.MovieDetailsResponse;
import ca.mohawkcollege.feng.network.OnDataResponseCallback;
import ca.mohawkcollege.feng.network.QueryMovieDetailsTask;
import ca.mohawkcollege.feng.utils.SnackbarUtils;

/**
 * Movie Details Fragment
 */

public class MovieDetailsFragment extends Fragment implements OnDataResponseCallback<MovieDetailsResponse> {

    private static final String ARG_MOVIE_ID = "args_imdb_id";

    private static final String TAG = "=="+ MovieDetailsFragment.class.getName();

    private String mMovieId;

    private FragmentMovieDetailsBinding mBinding;

    public MovieDetailsFragment() {
    }

    /**
     * movie details constructor
     * @param movieId id
     * @return fragment
     */
    public static MovieDetailsFragment newInstance(String movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * on create method
     * @param savedInstanceState state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSharedElementEnterTransition(new MaterialContainerTransform());
        if (getArguments() != null) {
            mMovieId = getArguments().getString(ARG_MOVIE_ID);
        }
        Log.e(TAG, "start to view movie details, movie id is: " + mMovieId);
    }

    /**
     * on create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState instance
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movie_details, container, false);
        mBinding = FragmentMovieDetailsBinding.bind(v);
        loadMovieData();
        return v;
    }

    /**
     * load movie data
     */
    private void loadMovieData() {
        new QueryMovieDetailsTask(mMovieId, this).execute();
    }

    /**
     * on receive data method Binding
     * @param data movie details
     */
    @Override
    public void onReceiveData(MovieDetailsResponse data) {
        if (!isVisible() || !isAdded()) {
            return;
        }
        boolean isOk = false;
        String res = getResources().getString(R.string.unable_to_fetchdata);
        if (data != null) {
            if ("False".equals(data.getResponse()) || data.getError() != null) {
                if (data.getError() != null) {
                    res = data.getError();
                }
                Log.d(TAG, "onReceive network data: occurs error " + res);
            } else {
                // render information
                Glide.with(getContext()).load(data.getPoster()).into(mBinding.moviePoster);
                mBinding.movieTitle.setText(data.getTitle());
                mBinding.movieReleased.setText(data.getReleased());
                mBinding.movieRuntime.setText(data.getRuntime());
                mBinding.movieDirector.setText(data.getDirector());
                mBinding.movieGenre.setText(data.getGenre());
                mBinding.movieWriter.setText(data.getWriter());
                mBinding.movieActors.setText(data.getActors());
                mBinding.moviePlot.setText(data.getPlot());

                isOk = true;
                res = getResources().getString(R.string.get_movie_details_successfully);
            }
        } else {
            Log.d(TAG, "onReceive network data: no response, there may be some network error or parse error");
        }
        SnackbarUtils.show(isOk, res, mBinding.getRoot());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}