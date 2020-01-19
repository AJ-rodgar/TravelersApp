package rodriguezgarcia.antoniojesus.travelersapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;

import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.fragments.DetailsFragment;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

public class DetailsActivity extends AppCompatActivity {

    private Country country;
    private MutableLiveData<List<Country>> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_details);
    }

    @Override
    protected void onResume() {
        super.onResume();

        country = (Country) getIntent().getExtras().get("country");
        model = (MutableLiveData<List<Country>>) getIntent().getExtras().get("countries");

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);

        detailsFragment.setData(model, country);

    }
}
