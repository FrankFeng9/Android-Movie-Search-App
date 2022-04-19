package ca.mohawkcollege.feng.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http Async
 * @param <T>
 */
public abstract class HttpAsyncTask<T> extends AsyncTask<Void, Void, T> {

    protected String TAG = QueryMovieDetailsTask.class.getName();

    private OnDataResponseCallback<T> callback;

    private final String api;

    /**
     * http async task constructor
     * @param api
     * @param callback
     */

    public HttpAsyncTask(String api, OnDataResponseCallback<T> callback) {
        this.api = api;
        this.callback = callback;
    }


    /**
     * download in background
     * @param voids
     * @return
     */

    @Override
    protected T doInBackground(Void... voids) {
        HttpURLConnection conn = null;
        try {
            URL urls = new URL(api);
            conn = (HttpURLConnection) urls.openConnection();
            conn.setReadTimeout(150000); // 150s timeout
            conn.setConnectTimeout(15000); // 15s timeout
            conn.setRequestMethod("GET");
            conn.connect(); // connect to api
            // Check the connection status.
            int statusCode = conn.getResponseCode();

            // Connection success. Proceed to fetch the response.
            if (statusCode == 200) {
                // read data from response stream
                BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder data = new StringBuilder();
                String line;
                while ((line = buff.readLine()) != null) { // read line by line
                    data.append(line);
                }
                return parseData(data.toString()); // parse data
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract T parseData(String json);


    /**
     * on post method
     * @param movie movie
     */
    @Override
    protected void onPostExecute(T movie) {
        if (callback != null) {
            Log.e(TAG, "finish load: data from imdb");
            if (movie == null) {
                Log.e(TAG, "finish load: no data returned");
            }
            callback.onReceiveData(movie);
        }
    }

}
