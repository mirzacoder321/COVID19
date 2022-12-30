package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private  int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AfectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry = findViewById(R.id.tvcountry);
        tvCases = findViewById(R.id.tvcases);
        tvRecovered = findViewById(R.id.tvrecovered);
        tvCritical = findViewById(R.id.tvcritical);
        tvActive = findViewById(R.id.tvactive);
        tvTodayCases = findViewById(R.id.tvtodayCases);
        tvTotalDeaths = findViewById(R.id.tvdeaths);
        tvTodayDeaths = findViewById(R.id.tvtodayDeaths);

        tvCountry.setText(AfectedCountries.countryModelList.get(positionCountry).getCountry());
        tvCases.setText(AfectedCountries.countryModelList.get(positionCountry).getCases());
        tvRecovered.setText(AfectedCountries.countryModelList.get(positionCountry).getRecovered());
        tvCritical.setText(AfectedCountries.countryModelList.get(positionCountry).getCritical());
        tvActive.setText(AfectedCountries.countryModelList.get(positionCountry).getActive());
        tvTodayCases.setText(AfectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AfectedCountries.countryModelList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AfectedCountries.countryModelList.get(positionCountry).getTodayDeaths());


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
