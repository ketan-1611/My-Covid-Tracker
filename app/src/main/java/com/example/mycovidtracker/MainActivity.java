package com.example.mycovidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycovidtracker.databinding.ActivityMainBinding;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchData();

        binding.btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCountryTrack();
            }
        });
    }

    private void fetchData(){

        String url = "https://corona.lmao.ninja/v2/all/";
        binding.loader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response.toString());
                            binding.tvCaseNo.setText(object.getString("cases"));
                            binding.tvRecoverdNo.setText(object.getString("recovered"));
                            binding.tvCriticalNo.setText(object.getString("critical"));
                            binding.tvActiveNo.setText(object.getString("active"));
                            binding.tvTodayCaseNo.setText(object.getString("todayCases"));
                            binding.tvTodayDeathNo.setText(object.getString("todayDeaths"));
                            binding.tvTotalDeathNo.setText(object.getString("deaths"));
                            binding.tvAffectedCountryNo.setText(object.getString("affectedCountries"));


                            binding.piechart.addPieSlice(new PieModel("Cases",Integer.parseInt(binding.tvCaseNo.getText().toString()),Color.parseColor("#e6b800")));
                            binding.piechart.addPieSlice(new PieModel("Recovered",Integer.parseInt(binding.tvRecoverdNo.getText().toString()),Color.parseColor("#00e600")));
                            binding.piechart.addPieSlice(new PieModel("Death",Integer.parseInt(binding.tvTotalDeathNo.getText().toString()),Color.parseColor("#52527a")));
                            binding.piechart.addPieSlice(new PieModel("Active",Integer.parseInt(binding.tvActiveNo.getText().toString()),Color.parseColor("#ff3333")));
                            binding.piechart.startAnimation();

                            binding.loader.stop();
                            binding.loader.setVisibility(View.GONE);
                            binding.scrollViewId.setVisibility(View.VISIBLE);






                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            binding.loader.stop();
                            binding.loader.setVisibility(View.GONE);
                            binding.scrollViewId.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                binding.loader.stop();
                binding.loader.setVisibility(View.GONE);
                binding.scrollViewId.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getCountryTrack(){
        Intent intent = new Intent(MainActivity.this,CountryTracker.class);
        startActivity(intent);
    }
}