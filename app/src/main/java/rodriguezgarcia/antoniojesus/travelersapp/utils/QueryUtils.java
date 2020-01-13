package rodriguezgarcia.antoniojesus.travelersapp.utils;

import android.text.TextUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.model.Country;

public class QueryUtils {

    public static List<Country> extractFeatureFromJson(String response) {
        if (TextUtils.isEmpty(response)) {
            return null;
        }

        List<Country> countries = new ArrayList<>();

        try {

        }
    }
}
