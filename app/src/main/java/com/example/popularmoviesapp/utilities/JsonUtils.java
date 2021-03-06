package com.example.popularmoviesapp.utilities;

import com.example.popularmoviesapp.models.Movie;
import com.example.popularmoviesapp.models.Review;
import com.example.popularmoviesapp.models.Trailer;

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
    private final static String JSON_MOVIE_ID = "id";
    private final static String JSON_POPULARITY = "popularity";

    private final static String JSON_REVIEW_AUTHOR = "author";
    private final static String JSON_REVIEW_CONTENT = "content";
    private final static String JSON_REVIEW_ID = "id";
    private final static String JSON_REVIEW_URL = "url";

    private final static String JSON_TRAILER_ID = "id";
    private final static String JSON_TRAILER_ISO_639_1 = "iso_639_1";
    private final static String JSON_TRAILER_ISO_3166_1 = "iso_3166_1";
    private final static String JSON_TRAILER_KEY = "key";
    private final static String JSON_TRAILER_NAME = "name";
    private final static String JSON_TRAILER_SIZE = "size";
    private final static String JSON_TRAILER_SITE = "site";
    private final static String JSON_TRAILER_TYPE = "type";


    private final static String POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE = "w342";

    public static ArrayList<Movie> extractMovieFromJSON(String mJsonString) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject jsonString = new JSONObject(mJsonString);
        JSONArray resultsArray = jsonString.getJSONArray(JSON_RESULTS);

        String title;
        String year;
        String description;
        String rating;
        String poster_path;
        int movieID;
        double popularity;

        for (int i = 0; i < resultsArray.length(); i++) {

            JSONObject currentMovie = resultsArray.getJSONObject(i);
            title = currentMovie.getString(JSON_TITLE);
            year = currentMovie.optString(JSON_DATE);
            description = currentMovie.optString(JSON_DESC);
            rating = currentMovie.optString(JSON_RATING);
            poster_path = currentMovie.optString(JSON_POSTER_PATH);
            movieID = currentMovie.optInt(JSON_MOVIE_ID);
            popularity = currentMovie.optDouble(JSON_POPULARITY);

            movies.add(new Movie(createImageString(poster_path), formatYear(year), title, formatRating(rating), popularity, description, movieID));
        }
        return movies;
    }

    private static String createImageString(String _poster_path) {
        if (_poster_path.equals("")) {
            return _poster_path;
        }
        return POSTER_BASE_URL + POSTER_SIZE + _poster_path;
    }

    private static String formatYear(String _year) {
        if (_year.equals("")) {
            return _year;
        }
        return _year.substring(0, 4);
    }

    private static String formatRating(String _rating) {
        return _rating + "/10";
    }

    public static ArrayList<Review> extractReviewFromJSON(String mJsonString, int movieId) throws JSONException {
        ArrayList<Review> reviews = new ArrayList<>();
        JSONObject jsonString = new JSONObject(mJsonString);
        JSONArray resultsArray = jsonString.getJSONArray(JSON_RESULTS);

        String author;
        String content;
        String id;
        String url;


        for (int i = 0; i < resultsArray.length(); i++) {

            JSONObject currentReview = resultsArray.getJSONObject(i);
            author = currentReview.getString(JSON_REVIEW_AUTHOR);
            content = currentReview.optString(JSON_REVIEW_CONTENT).trim();
            id = currentReview.optString(JSON_REVIEW_ID);
            url = currentReview.optString(JSON_REVIEW_URL);

            reviews.add(new Review(movieId, author, content, id, url));
        }
        return reviews;
    }

    public static ArrayList<Trailer> extractTrailerFromJSON(String mJsonString, int movieId) throws JSONException {
        ArrayList<Trailer> trailer = new ArrayList<>();
        JSONObject jsonString = new JSONObject(mJsonString);
        JSONArray resultsArray = jsonString.getJSONArray(JSON_RESULTS);

        String id;
        String iso_639_1;
        String iso_3166_1;
        String key;
        String name;
        String site;
        String size;
        String type;

        for (int i = 0; i < resultsArray.length(); i++) {

            JSONObject currentTrailer = resultsArray.getJSONObject(i);
            id = currentTrailer.getString(JSON_TRAILER_ID);
            iso_639_1 = currentTrailer.optString(JSON_TRAILER_ISO_639_1).trim();
            iso_3166_1 = currentTrailer.optString(JSON_TRAILER_ISO_3166_1);
            key = currentTrailer.optString(JSON_TRAILER_KEY);
            name = currentTrailer.optString(JSON_TRAILER_NAME);
            site = currentTrailer.optString(JSON_TRAILER_SITE);
            size = currentTrailer.optString(JSON_TRAILER_SIZE);
            type = currentTrailer.optString(JSON_TRAILER_TYPE);

            trailer.add(new Trailer(movieId, id, iso_639_1, iso_3166_1, key, name, site, size, type));
        }
        return trailer;
    }
}
