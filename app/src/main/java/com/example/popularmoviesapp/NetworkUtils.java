package com.example.popularmoviesapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String LOG_TAG = NetworkUtils.class.getName();
    final static String API_KEY = "";
    final static String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    final static String API_KEY_PARAM = "api_key";
    final static String SORT_BY = "sort_by";
    final static String POPULARITY_DESCENDING  = "popularity.desc";


    public static URL buildUrl(String _movieDbQuery) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
               .appendQueryParameter(SORT_BY, _movieDbQuery)
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