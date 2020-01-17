package rodriguezgarcia.antoniojesus.travelersapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.model.Country;

public class DetailsFragment extends Fragment {

    private ImageView flag;
    private TextView name;
    private TextView capital;
    private TextView language;
    private TextView currency;


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

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setData(Country country){

        name.setText(country.getName());
        capital.setText(country.getCapital());
        language.setText(country.getLanguage());
        currency.setText(country.getCurrency());

    }
}
