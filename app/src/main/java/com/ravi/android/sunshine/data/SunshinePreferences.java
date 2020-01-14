package com.ravi.android.sunshine.data;

import android.content.Context;

import androidx.annotation.NonNull;

public class SunshinePreferences {

    private static final String DEFAULT_WEATHER_LOCATION = "94043,USA";

    public static String getPreferredWeatherLocation(@NonNull final Context context) {
        return getDefaultWeatherLocation();
    }

    private static String getDefaultWeatherLocation() {
        return DEFAULT_WEATHER_LOCATION;
    }

    public static boolean isMetric(Context context) {
        /** This will be implemented in a future lesson **/
        return true;
    }
}
