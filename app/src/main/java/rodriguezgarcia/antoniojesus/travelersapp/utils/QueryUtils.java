package rodriguezgarcia.antoniojesus.travelersapp.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.model.Country;

public class QueryUtils {

    private static final String LOG_TAG = "QUERY UTILS";

    public static List<Country> extractFeatureFromJson(String response) {
        if (TextUtils.isEmpty(response)) {
            return null;
        }

        List<Country> countries = new ArrayList<>();

        try {

            JSONArray countryArray = new JSONArray(response);

            for (int i = 0; i < countryArray.length(); i++) {

                JSONObject currentCountry = countryArray.getJSONObject(i);

                String name = currentCountry.getString("name");
                String capital = currentCountry.getString("capital");
                String region = currentCountry.getString("region");
                String flag = currentCountry.getString("flag");

                JSONArray currencies = currentCountry.getJSONArray("currencies");
                JSONObject currencyObject = currencies.getJSONObject(0);
                String currency = currencyObject.getString("name");

                JSONArray languages = currentCountry.getJSONArray("languages");
                JSONObject languageObject = languages.getJSONObject(0);
                String language = languageObject.getString("name");

                double latitude = 0;
                double longitude = 0;
                /*JSONArray latlng = currentCountry.getJSONArray("latlng");
                latitude = latlng.getDouble(0);
                longitude = latlng.getDouble(1);*/


                Country country = new Country(name,region,capital,language, currency, longitude, latitude, flag);
                countries.add(country);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public static List<Country> fetchCountryData(String requestUrl) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Country> countries = extractFeatureFromJson(jsonResponse);

        return countries;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
