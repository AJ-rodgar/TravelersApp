package rodriguezgarcia.antoniojesus.travelersapp.model;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.data.Country;
import rodriguezgarcia.antoniojesus.travelersapp.data.DataBaseRoom;
import rodriguezgarcia.antoniojesus.travelersapp.utils.QueryUtils;

public class CountryViewModel extends AndroidViewModel {

    private static String API_REQUEST_URL = "https://restcountries.eu/rest/v2/all";

    private Application application;
    private MutableLiveData<List<Country>> countries;
    private DataBaseRoom db;
    private List<Country> lista;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        db = DataBaseRoom.getInstance(application);
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

    private List<String> getCountriesByState(int state){
        return db.countryDAO().getCountriesByState(state);
    }

    public int getCountryState(String name) {
        return db.countryDAO().getState(name);
    }

    public void addCountry(Country country) {
        //new AsyncAddCountryDB().execute(country);
    }

    public void addCountries(List<Country> countries) {
        new AsyncAddCountriesDB().execute(countries);
    }

    public void updateCountry(Country country) {
        new AsyncEditCountryDB().execute(country);
    }

    /*private class AsyncAddCountryDB extends AsyncTask<Country, Void, Long> {

        Country country;

        @Override
        protected Long doInBackground(Country... countries) {

            long id = -1;

            if (countries.length != 0) {
                country = countries[0];
                if (!db.countryDAO().getCountries().contains(country)){
                    db.countryDAO().insertCountry(countries[0]);
                }
            }

            return id;
        }
    }*/

    private class AsyncEditCountryDB extends AsyncTask<Country, Void, Integer> {

        @Override
        protected Integer doInBackground(Country... countries) {
            int updaterows = 0;

            if (countries.length != 0) {
                updaterows = db.countryDAO().updateCountry(countries[0]);
            }

            return updaterows;
        }
    }

    private class AsyncAddCountriesDB extends AsyncTask<List<Country>, Void, List<Country>> {

        @Override
        protected List<Country> doInBackground(List<Country>... lists) {

            List<Country> added = new ArrayList<>();
            List<Country> list = lists[0];
            for (Country country : list) {
                long id = db.countryDAO().insertCountry(country);
                country.setId(id);
                added.add(country);
            }
            return added;
        }

        @Override
        protected void onPostExecute(List<Country> added) {
            super.onPostExecute(added);

            countries.postValue(added);
        }
    }
}
