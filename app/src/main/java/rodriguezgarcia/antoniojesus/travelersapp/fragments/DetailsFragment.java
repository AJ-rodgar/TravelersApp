package rodriguezgarcia.antoniojesus.travelersapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.activities.MapsActivity;
import rodriguezgarcia.antoniojesus.travelersapp.data.DataBaseRoom;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

public class DetailsFragment extends Fragment {

    private ImageView flag;
    private TextView name;
    private TextView capital;
    private TextView language;
    private TextView currency;

    private Button goToMap;
    private TextView visited;
    private TextView pending;
    private TextView notVisited;

    private DataBaseRoom db;

    private static Country countryDB;

    public DetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        flag = view.findViewById(R.id.imageview_flag);
        name = view.findViewById(R.id.countryName);
        capital = view.findViewById(R.id.countryCapital);
        language = view.findViewById(R.id.countryLanguage);
        currency = view.findViewById(R.id.countryCurrency);

        goToMap = view.findViewById(R.id.btn_map);
        visited = view.findViewById(R.id.textview_visited);
        pending = view.findViewById(R.id.textview_pending);
        notVisited = view.findViewById(R.id.textview_notVisited);

        db = DataBaseRoom.getInstance(getContext());

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setData(final Country country){

        countryDB = country;
        new AsyncGetCountryDB().execute(countryDB);

        if (country.getImageID() != 0) {
            flag.setImageResource(country.getImageID());
        }

        name.setText(country.getName());
        capital.setText(country.getCapital());
        language.setText(country.getLanguage());
        currency.setText(country.getCurrency());

        notifyChangeState(country);

        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("Country", country);
                startActivity(intent);
            }
        });

        visited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                country.setState(1);
                notifyChangeState(country);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryDB.setState(0);
                notifyChangeState(countryDB);
            }
        });

        notVisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryDB.setState(-1);
                notifyChangeState(countryDB);
            }
        });

    }

    public void notifyChangeState(Country country){
        if (country.getState() == -1) {
            notVisited.setBackgroundColor(Color.RED);
            visited.setBackgroundColor(Color.WHITE);
            pending.setBackgroundColor(Color.WHITE);
        } else if (country.getState() == 1) {
            notVisited.setBackgroundColor(Color.WHITE);
            visited.setBackgroundColor(Color.GREEN);
            pending.setBackgroundColor(Color.WHITE);
        } else {
            notVisited.setBackgroundColor(Color.WHITE);
            visited.setBackgroundColor(Color.WHITE);
            pending.setBackgroundColor(Color.YELLOW);
        }
    }

    private class AsyncGetCountryDB extends AsyncTask<Country, Void, Long> {

        Country country;

        @Override
        protected Long doInBackground(Country... countries) {

            long id = -1;

            if (countries.length != 0) {
                country = countries[0];
                countryDB.setState(country.getState());
            }

            return id;
        }
    }

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
}
