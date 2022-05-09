package com.example.mycovidtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycovidtracker.databinding.CountrySampleDesignBinding;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    Context context;
    ArrayList<CountryModel>countryModels;
    ArrayList<CountryModel>countryModelsFiltered;

    public CountryAdapter(Context context, ArrayList<CountryModel> countryModels) {
        this.context = context;
        this.countryModels = countryModels;
        this.countryModelsFiltered = countryModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_sample_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryModel model = countryModelsFiltered.get(position);

        holder.binding.tvCountryName.setText(model.getCountry());
        Glide.with(context).load(model.getFlag()).into(holder.binding.igFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryModelsFiltered.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }



    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countryModelsFiltered = countryModels;
                } else {
                    ArrayList<CountryModel> filteredList = new ArrayList<>();
                    for (CountryModel model : countryModels) {

                        if (model.getCountry().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(model);
                        }
                    }

                    countryModelsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countryModelsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryModelsFiltered = (ArrayList<CountryModel>) filterResults.values;
                CountryTracker.list = (ArrayList<CountryModel>)filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        CountrySampleDesignBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = CountrySampleDesignBinding.bind(itemView);
        }
    }
}
