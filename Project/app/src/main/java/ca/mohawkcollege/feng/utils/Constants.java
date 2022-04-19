package ca.mohawkcollege.feng.utils;

/**
 * hold some constants
 */
public final class Constants {
    private Constants() {
    }

    private final static String API_BASE = "http://www.omdbapi.com/?apikey=d70afb89&";

    private final static String QUERY_API = API_BASE + "&s=%s&y=%s";

    private final static String GET_BY_ID_API = API_BASE + "&i=";

    /**
     * get query api
     */
    public static String getQueryApi(String query, String year) {
        return String.format(QUERY_API, query, year);
    }

    public static String getGetByIdApi(String id) {
        return GET_BY_ID_API + id;
    }

}
