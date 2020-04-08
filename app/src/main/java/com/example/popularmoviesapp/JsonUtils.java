package com.example.popularmoviesapp;

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

    private final static String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE = "w185";


    public static ArrayList<Movie> extractMovieFromJSON(String mJsonString) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject jsonString = new JSONObject(mJsonString);
        JSONArray resultsArray = jsonString.getJSONArray(JSON_RESULTS);

        for(int i = 0; i<resultsArray.length();i++){
            JSONObject currentMovie = resultsArray.getJSONObject(i);
            String title = currentMovie.getString(JSON_TITLE);
            String year = currentMovie.getString(JSON_DATE);
            String description = currentMovie.getString(JSON_DESC);
            String rating = currentMovie.getString(JSON_RATING);
            String poster_path = currentMovie.getString(JSON_POSTER_PATH);

            String duration = "not available";

            movies.add(new Movie(createImageString(poster_path),year,title,duration,rating,description));
        }
        return movies;
    }

    public static String createImageString(String imagePath){
        return POSTER_BASE_URL + POSTER_SIZE + imagePath;
    }
}
