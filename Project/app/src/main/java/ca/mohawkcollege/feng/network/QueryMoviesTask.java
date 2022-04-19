package ca.mohawkcollege.feng.network;

import com.google.gson.Gson;

import ca.mohawkcollege.feng.model.MovieResponse;
import ca.mohawkcollege.feng.utils.Constants;

/**
 * movie query
 */
public class QueryMoviesTask extends HttpAsyncTask<MovieResponse> {

    /**
     * movie query constructor
     * @param query query
     * @param year  year
     * @param callback
     */
    public QueryMoviesTask(String query, String year, OnDataResponseCallback<MovieResponse> callback) {
        super(Constants.getQueryApi(query, year), callback);
    }

    /**
     *parse data
     */
    @Override
    protected MovieResponse parseData(String json) {
        return new Gson().fromJson(json, MovieResponse.class);
    }

}
