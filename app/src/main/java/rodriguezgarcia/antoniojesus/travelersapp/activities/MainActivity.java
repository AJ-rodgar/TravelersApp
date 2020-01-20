package rodriguezgarcia.antoniojesus.travelersapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.DetailsFragment;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.ListFragment;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

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
            detailsFragment.getCountryData(country);
        }

    }
}
