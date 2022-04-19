package ca.mohawkcollege.feng.model;

import java.util.List;

/**
 * represents movie responses
 */
public class MovieResponse {
    private List<MovieItem> Search;
    private String Response;
    private String Error;

    /**
     * movie search
     * @return search result
     */
    public List<MovieItem> getSearch() {
        return Search;
    }


    //getter and setter

    public void setSearch(List<MovieItem> search) {
        Search = search;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
