package rodriguezgarcia.antoniojesus.travelersapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.activities.DetailsActivity;
import rodriguezgarcia.antoniojesus.travelersapp.adapters.CountryAdapter;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;
import rodriguezgarcia.antoniojesus.travelersapp.model.CountryViewModel;

public class ListFragment extends Fragment {

    private OnCountrySent callback;
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countries;
    private CountryViewModel model;
    private Context context;

    public ListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        countries = new ArrayList<>();

        context = view.getContext();

        loadCountries();

        return view;
    }

    public void loadCountries(){

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            model = ViewModelProviders.of(this).get(CountryViewModel.class);
            adapter = new CountryAdapter(context, countries, new CountryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Country country, int position) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("country", country);
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);

            model.getCountries().observe(this, new Observer<List<Country>>() {
                @Override
                public void onChanged(List<Country> countries) {
                    adapter.addAll(countries);
                    adapter.notifyDataSetChanged();
                    model.addCountries(countries);
                }
            });

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (OnCountrySent) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Error");
        }
    }

    public interface OnCountrySent{
        void onChange(Country country);
    }
}
