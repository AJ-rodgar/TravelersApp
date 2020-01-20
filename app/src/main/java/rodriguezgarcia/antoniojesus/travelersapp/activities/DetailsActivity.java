package rodriguezgarcia.antoniojesus.travelersapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.DetailsFragment;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

public class DetailsActivity extends AppCompatActivity {

    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);
    }

    @Override
    protected void onResume() {
        super.onResume();

        country = (Country) getIntent().getExtras().get("country");

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);

        detailsFragment.getCountryData(country);

    }
}
