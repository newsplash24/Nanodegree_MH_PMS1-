package com.nanodegree.mh.pms1.utilites;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.nanodegree.mh.pms1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Mohamed on 10/7/2017.
 */

public final class NetworkUtilites {

    private static final String IMAGES_URL = "http://image.tmdb.org/t/p/w780/";
    private static final String IMAGES_THUMBNAIL_URL = "http://image.tmdb.org/t/p/w342/";
    private static Context context;

    public NetworkUtilites(Context context) {
        this.context = context;

    }

    private static final String TAG = NetworkUtilites.class.getSimpleName();


    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/";


    private static final String API_KEY = "api_key";



    /**
     * Builds the URL used to talk to the moviesDB server using a category. This category is based
     * on the user preferences.
     *
     * @param category The category that will be queried for.
     * @return The URL to use to query the moviesDB server.
     */
    public static URL buildUrl(Context context, String category) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(category)
                .appendQueryParameter(API_KEY, context.getString(R.string.API_KEY))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }


    public static String getResponseDataFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String result = scanner.next();
                Log.i("REQUEST_RESULT", result);
                return result;
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getImagesURL() {

        return IMAGES_URL;
    }


    public static String getImagesThumbnailUrl() {

        return IMAGES_THUMBNAIL_URL;
    }


}
