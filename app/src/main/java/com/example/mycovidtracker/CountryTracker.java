package com.example.mycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycovidtracker.databinding.ActivityCountryTrackerBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryTracker extends AppCompatActivity {

    ActivityCountryTrackerBinding binding;
    public static ArrayList<CountryModel>list;
    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountryTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = new ArrayList<>();
        adapter = new CountryAdapter(this,list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvCountry.setLayoutManager(layoutManager);
        binding.rvCountry.setAdapter(adapter);

        fetchData();




       binding.etSearch.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adapter.getFilter().filter(charSequence);
            adapter.notifyDataSetChanged();
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchData()
    {
        String url = "https://corona.lmao.ninja/v2/countries/";
        binding.loader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response.toString());

                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String countryName = object.getString("country");
                                String cases = object.getString("cases");
                                String todayCases = object.getString("todayCases");
                                String deaths = object.getString("deaths");
                                String todayDeath = object.getString("todayDeaths");
                                String recovered = object.getString("recovered");
                                String active = object.getString("active");
                                String critical = object.getString("critical");

                                JSONObject obj = object.getJSONObject("countryInfo");
                                String flag = obj.getString("flag");
                                String id = obj.getString("_id");

                                list.add(new CountryModel(id,countryName,cases,todayCases,deaths,todayDeath,recovered,active,critical,flag));
                                adapter.notifyDataSetChanged();
                                binding.loader.stop();
                                binding.loader.setVisibility(View.GONE);
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            binding.loader.stop();
                            binding.loader.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CountryTracker.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.loader.stop();
                binding.loader.setVisibility(View.GONE);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}