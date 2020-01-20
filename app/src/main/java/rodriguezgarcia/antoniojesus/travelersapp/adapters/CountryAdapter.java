package rodriguezgarcia.antoniojesus.travelersapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rodriguezgarcia.antoniojesus.travelersapp.R;
import rodriguezgarcia.antoniojesus.travelersapp.data.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context context;
    private List<Country> countries;
    private OnItemClickListener listener;

    public CountryAdapter(Context context, List<Country> countries, OnItemClickListener listener){
        this.context = context;
        this.countries = countries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        //Cada region tendrá su propio color de fondo cuando se muestre en la lista
        Map<String, String> colorsByRegion = new TreeMap<String,String>();
        colorsByRegion.put("Europe", "#98ee99");
        colorsByRegion.put("Asia", "#ffff8b");
        colorsByRegion.put("Americas", "#ff6659");
        colorsByRegion.put("Africa", "#ffc947");
        colorsByRegion.put("Oceania", "#ff94c2");
        colorsByRegion.put("Polar", "#4f83cc");
        colorsByRegion.put("", "#4f83cc");

        final Country country = countries.get(position);

        holder.region.setText(country.getRegion());
        holder.name.setText(country.getName());
        holder.region.setBackgroundColor(Color.parseColor(colorsByRegion.get(country.getRegion())));

        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onItemClick(country, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addAll(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public void clear() {
        countries.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView region;
        TextView name;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.continent);
            name = itemView.findViewById(R.id.country);
            linearLayout = itemView.findViewById(R.id.linearLayout_countryRow);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Country country, int position);
    }
}
