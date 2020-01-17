package rodriguezgarcia.antoniojesus.travelersapp.model;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.utils.QueryUtils;

public class CountryViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<List<Country>> countries;
    private static String API_REQUEST_URL = "https://restcountries.eu/rest/v2/all";

    public CountryViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Country>> getCountries() {
        if (countries == null) {
            countries = new MutableLiveData<>();
            loadCountries();
        }

        return countries;
    }

    private void loadCountries() {
        Uri baseUri = Uri.parse(API_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        RequestQueue requestQueue = Volley.newRequestQueue(application);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uriBuilder.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                countries.postValue(QueryUtils.extractFeatureFromJson(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }


}
