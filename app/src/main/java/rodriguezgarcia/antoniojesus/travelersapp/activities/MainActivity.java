package rodriguezgarcia.antoniojesus.travelersapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.adapters.CountryAdapter;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.DetailsFragment;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.ListFragment;
import rodriguezgarcia.antoniojesus.travelersapp.model.Country;
import rodriguezgarcia.antoniojesus.travelersapp.model.CountryViewModel;

public class MainActivity extends AppCompatActivity implements ListFragment.OnCountrySent {

    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);

    }

    @Override
    public void onChange(Country country) {

        if (detailsFragment == null) {
            Intent intent = new Intent(this, DetailsFragment.class);
            intent.putExtra("country", country);
            startActivity(intent);
        } else {
            //detailsFragment.setData();
        }

    }
}
