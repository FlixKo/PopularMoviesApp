package com.example.popularmoviesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private final static String JSON_RESULTS = "results";
    private final static String JSON_TITLE = "original_title";
    private final static String JSON_DATE = "release_date";
    private final static String JSON_DESC = "overview";
    private final static String JSON_RATING = "vote_average";
    private final static String JSON_POSTER_PATH = "poster_path";

    private final static String POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE = "w342";

    private final static String LOG_TAG = JsonUtils.class.getName();

    public static ArrayList<Movie> extractMovieFromJSON(String mJsonString) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject jsonString = new JSONObject(mJsonString);
        JSONArray resultsArray = jsonString.getJSONArray(JSON_RESULTS);

        String title = "";
        String year = "";
        String description = "";
        String rating = "";
        String poster_path = "";

        for(int i = 0; i<resultsArray.length();i++){

            JSONObject currentMovie = resultsArray.getJSONObject(i);
            title = currentMovie.getString(JSON_TITLE);
            year = currentMovie.optString(JSON_DATE);
            description = currentMovie.optString(JSON_DESC);
            rating = currentMovie.optString(JSON_RATING);
            poster_path = currentMovie.optString(JSON_POSTER_PATH);

            movies.add(new Movie(createImageString(poster_path),formatYear(year),title,formatRating(rating),description));
        }
        return movies;
    }

    public static String createImageString(String _poster_path){
        if(_poster_path.equals("")){
            return _poster_path;
        }
        return POSTER_BASE_URL + POSTER_SIZE + _poster_path;
    }

    public static String formatYear(String _year){
        if(_year.equals("")){
            return _year;
        }
        return _year.substring(0,4);
    }

    public static String formatRating(String _rating){
        return _rating + "/10";
    }
}
