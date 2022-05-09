package com.example.mycovidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mycovidtracker.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private int countryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent;
        intent = getIntent();
        countryPosition = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of " +CountryTracker.list.get(countryPosition).getCountry());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.tvAffectedCountryNo.setText(CountryTracker.list.get(countryPosition).getCountry());
        binding.tvCaseNo.setText(CountryTracker.list.get(countryPosition).getCases());
        binding.tvRecoverdNo.setText(CountryTracker.list.get(countryPosition).getRecovered());
        binding.tvCriticalNo.setText(CountryTracker.list.get(countryPosition).getCritical());
        binding.tvActiveNo.setText(CountryTracker.list.get(countryPosition).getActive());
        binding.tvTotalDeathNo.setText(CountryTracker.list.get(countryPosition).getDeaths());
        binding.tvTodayDeathNo.setText(CountryTracker.list.get(countryPosition).getTodayDeath());
        binding.tvTodayCaseNo.setText(CountryTracker.list.get(countryPosition).getTodayCases());




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
}