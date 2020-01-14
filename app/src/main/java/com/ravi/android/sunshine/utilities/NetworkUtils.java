package com.ravi.android.sunshine.utilities;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String DYNAMIC_WEATHER_URL = "https://andfun-weather.udacity.com/weather";
    private static final String STATIC_WEATHER_URL = "https://andfun-weather.udacity.com/staticweather";
    private static final String FORECAST_BASE_URL = STATIC_WEATHER_URL;
    private static final String FORMAT = "json";
    private static final String UNITS = "metric";
    private static final int NUM_DAYS = 14;
    private static final String QUERY_PARAM = "q";
    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";
    private static final String FORMAT_PARAM = "mode";
    private static final String UNITS_PARAM = "units";
    private static final String DAYS_PARAM = "cnt";

    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param locationQuery The location that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(@NonNull final String locationQuery) {
        Uri uri = Uri.parse(FORECAST_BASE_URL).buildUpon()
            .appendQueryParameter(QUERY_PARAM, locationQuery)
            .appendQueryParameter(FORMAT_PARAM, FORMAT)
            .appendQueryParameter(UNITS_PARAM, UNITS)
            .appendQueryParameter(DAYS_PARAM, String.valueOf(NUM_DAYS))
            .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    @Nullable
    public static String getResponseFromUrl(@NonNull URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try (Scanner scanner = new Scanner(urlConnection.getInputStream())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
