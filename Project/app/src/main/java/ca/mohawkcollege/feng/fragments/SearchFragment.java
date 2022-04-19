package ca.mohawkcollege.feng.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.transition.MaterialElevationScale;

import java.util.ArrayList;
import java.util.List;

import ca.mohawkcollege.feng.R;
import ca.mohawkcollege.feng.adapter.MovieRecyclerViewAdapter;
import ca.mohawkcollege.feng.adapter.OnMovieCardClickListener;
import ca.mohawkcollege.feng.databinding.FragmentSearchBinding;
import ca.mohawkcollege.feng.model.MovieItem;
import ca.mohawkcollege.feng.model.MovieResponse;
import ca.mohawkcollege.feng.network.OnDataResponseCallback;
import ca.mohawkcollege.feng.network.QueryMoviesTask;
import ca.mohawkcollege.feng.utils.SnackbarUtils;


/**
 * Search Fragment
 */
public class SearchFragment extends Fragment implements OnDataResponseCallback<MovieResponse>, OnMovieCardClickListener {

    private static final String TAG =  "==" + SearchFragment.class.getName();

    /**
     * Data-binding reference of search fragment
     */
    private FragmentSearchBinding mBinding;

    private MovieRecyclerViewAdapter mMovieAdapter;

    public SearchFragment() {
    }

    /**
     * On create method
     * @param savedInstanceState state
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init animation of fragment switch
        MaterialElevationScale exitTransition = new MaterialElevationScale(false);
        exitTransition.setDuration(250);
        MaterialElevationScale enterTransition = new MaterialElevationScale(false);
        enterTransition.setDuration(250);
        setExitTransition(exitTransition);
        setEnterTransition(enterTransition);
    }

    /**
     * on create view method
     * @param inflater  inflater
     * @param container  container
     * @param savedInstanceState state
     * @return view
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mBinding = FragmentSearchBinding.bind(view);
        initViews();
        return view;
    }

    /**
     * Init listener & other components in search fragment
     */
    private void initViews() {
        mMovieAdapter = new MovieRecyclerViewAdapter(this, getContext(), new ArrayList<>());
        mBinding.resultListView.setAdapter(mMovieAdapter);
        mBinding.btnSearch.setOnClickListener((event) -> actionOnSearch());
    }

    /**
     * Execute search action
     */
    private void actionOnSearch() {
        String text = mBinding.searchTextField.getEditText().getText().toString();
        String year = mBinding.searchYearField.getEditText().getText().toString();
        new QueryMoviesTask(text, year, this).execute();
    }

    /**
     * on receive data mother
     * @param data response data
     */
    @Override
    public void onReceiveData(MovieResponse data) {
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
                Log.d(TAG, "onReceive movie response data: error " + res);
            } else {
                List<MovieItem> MovieResult = data.getSearch();
                if (MovieResult == null) {
                    MovieResult = new ArrayList<>();
                }
                Log.d(TAG, "onReceive network data: totally " + MovieResult.size() + " records");
                mMovieAdapter.clear();
                mMovieAdapter.addAll(MovieResult);
                mBinding.resultListView.invalidateViews(); // refresh list
                mBinding.resultListView.scrollBy(0, 0); // scroll to top
                isOk = true;
                res = getResources().getString(R.string.search_movie_successfully);
            }
        } else {
            Log.d(TAG, "onReceive network data: no response, there may be some network error or parse error");
        }
        SnackbarUtils.show(isOk, res, mBinding.getRoot());
    }

    /**
     * on item click lister
     * @param itemView item view
     * @param data MovieItem
     */

    @Override
    public void onItemClick(View itemView, MovieItem data) {
        if (!isVisible() || !isAdded()) {
            return;
        }
        // pop event to parent activity for fragment switch
        FragmentActivity bindingActivity = getActivity();
        if (!(bindingActivity instanceof OnMovieCardClickListener)) {
            return;
        }
        ((OnMovieCardClickListener) bindingActivity).onItemClick(itemView, data);
    }
}