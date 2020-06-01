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
    private final static String API_KEY = "51ef7ace762b5d09cd2b2a5930bfde3c";
    private final static String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private final static String API_KEY_PARAM = "api_key";
    private final static String SORT_BY = "sort_by";
    private final static String VOTE_COUNT = "vote_count.gte";
    private final static String MINIMUM_VOTE_COUNT = "1000";

    public static URL buildUrl(String _movieDbQuery) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
               .appendQueryParameter(SORT_BY, _movieDbQuery)
                .appendQueryParameter(VOTE_COUNT,MINIMUM_VOTE_COUNT)
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
}