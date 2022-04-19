package ca.mohawkcollege.feng.network;

import com.google.gson.Gson;

import ca.mohawkcollege.feng.model.MovieDetailsResponse;
import ca.mohawkcollege.feng.utils.Constants;


/**
 * Movie Details Query
 */
public class QueryMovieDetailsTask extends HttpAsyncTask<MovieDetailsResponse> {

    /**
     * MovieDetailsTask constructor
     * @param query
     * @param callback
     */
    public QueryMovieDetailsTask(String query, OnDataResponseCallback<MovieDetailsResponse> callback) {
        super(Constants.getGetByIdApi(query), callback);
    }

    /**
     *parse data
     */
    @Override
    protected MovieDetailsResponse parseData(String json) {
        return new Gson().fromJson(json, MovieDetailsResponse.class);
    }


}
