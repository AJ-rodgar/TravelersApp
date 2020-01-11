package rodriguezgarcia.antoniojesus.travelersapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.model.Country;

public class CountryAdapter extends ArrayAdapter {

    List<Country> countries;
    Context context;

    public CountryAdapter(@NonNull Context context, List<Country> countries){
        super(context, 0, countries);
        this.countries = countries;
        this.context = context;
    }

}
