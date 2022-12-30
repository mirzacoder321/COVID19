package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AfectedCountries extends AppCompatActivity {

    EditText edtSearch;
    ListView listview;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afected_countries);

        edtSearch=findViewById(R.id.edtSearch);
        listview = findViewById(R.id.listview);

        getSupportActionBar().setTitle("Afected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",position));
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url = "https://disease.sh/v3/covid-19/countries ";
//        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            int i;
                            for (i = 0; i<jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String todayCases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("deaths");
                                String todayDeaths = jsonObject.getString("todayDeaths");
                                String active = jsonObject.getString("active");
                                String critical = jsonObject.getString("critical");
                                String continent = jsonObject.getString("continent");
                                String recovered = jsonObject.getString("recovered");

                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = object.getString("flag");

                                countryModel = new CountryModel(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,active,recovered,critical,continent);
                                countryModelList.add(countryModel);

                            }

                            myCustomAdapter = new MyCustomAdapter(AfectedCountries.this,countryModelList);
                            listview.setAdapter(myCustomAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AfectedCountries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
////import com.leo.simplearcloader.SimpleArcLoader;
//
//import org.eazegraph.lib.models.PieModel;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;

//public class AfectedCountries extends AppCompatActivity {
//
//    EditText edtSearch;
//    ListView listView;
////    SimpleArcLoader simpleArcLoader;
//
//    public static List<CountryModel> countryModelsList = new ArrayList<>();
//    CountryModel countryModel;
//    MyCustomAdapter myCustomAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_afected_countries);
//
//        edtSearch = findViewById(R.id.edtSearch);
//        listView = findViewById(R.id.listview);
////        simpleArcLoader = findViewById(R.id.loader);
//
//        getSupportActionBar().setTitle("Affected Countries");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        fetchData();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",position));
//            }
//        });
//
//
//        edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                myCustomAdapter.getFilter().filter(s);
//                myCustomAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==android.R.id.home)
//            finish();
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void fetchData() {
//
//        String url  = "https://corona.lmao.ninja/v2/countries/";
//
////        simpleArcLoader.start();
//
//        StringRequest request = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//
//                            for(int i=0;i<jsonArray.length();i++){
//
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                                String countryName = jsonObject.getString("country");
//                                String cases = jsonObject.getString("cases");
//                                String todayCases = jsonObject.getString("todayCases");
//                                String deaths = jsonObject.getString("deaths");
//                                String todayDeaths = jsonObject.getString("todayDeaths");
//                                String recovered = jsonObject.getString("recovered");
//                                String active = jsonObject.getString("active");
//                                String critical = jsonObject.getString("critical");
//                                String continent = jsonObject.getString("continent");
//
//                                JSONObject object = jsonObject.getJSONObject("countryInfo");
//                                String flagUrl = object.getString("flag");
//
//                                countryModel = new CountryModel(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,active,recovered,critical,continent);
//                                countryModelsList.add(countryModel);
//
//
//                            }
//
//                            myCustomAdapter = new MyCustomAdapter(AfectedCountries.this,countryModelsList);
//                            listView.setAdapter(myCustomAdapter);
////                            simpleArcLoader.stop();
////                            simpleArcLoader.setVisibility(View.GONE);
//
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
////                            simpleArcLoader.stop();
////                            simpleArcLoader.setVisibility(View.GONE);
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                simpleArcLoader.stop();
////                simpleArcLoader.setVisibility(View.GONE);
//                Toast.makeText(AfectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//
//
//    }
//
//}