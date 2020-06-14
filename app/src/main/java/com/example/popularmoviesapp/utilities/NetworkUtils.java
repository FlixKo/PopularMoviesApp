package com.example.popularmoviesapp.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String LOG_TAG = NetworkUtils.class.getName();
    private final static String API_KEY = "api_key";
    private final static String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private final static String ADDITIONAL_INFO = "https://api.themoviedb.org/3/movie/";
    private final static String TRAILER = "/videos";
    private final static String REVIEWS = "/reviews";
    private final static String API_KEY_PARAM = "api_key";
    private final static String SORT_BY = "sort_by";
    private final static String VOTE_COUNT = "vote_count.gte";
    private final static String MINIMUM_VOTE_COUNT = "1000";
    private final static String RELEASE_DATE = "primary_release_date.gte";
    private final static String EARLIEST_RELEASE_DATE = "2000-01-01";


    public static URL buildUrl(String _movieDbQuery) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SORT_BY, _movieDbQuery)
                .appendQueryParameter(VOTE_COUNT, MINIMUM_VOTE_COUNT)
                .appendQueryParameter(RELEASE_DATE, EARLIEST_RELEASE_DATE)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildTrailerUrl(String _movie) {
        Uri builtUri = Uri.parse(ADDITIONAL_INFO + _movie + TRAILER).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildReviewUrl(String _movie) {
        Uri builtUri = Uri.parse(ADDITIONAL_INFO + _movie + REVIEWS).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}