package com.example.paypay;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.paypay.adapter.CurrencyExchangeAdapter;
import com.example.paypay.currencyDTO.CurrencyExchangeQuotes;
import com.example.paypay.currencyDTO.CurrencyResponseDTO;
import com.example.paypay.presenter.CurrencyPresenter;
import com.example.paypay.view.CurrencyView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import android.support.v7.widget.RecyclerView;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CurrencyView {

    private Spinner spinner;
    private ConstraintLayout constraintLayout;
    private CurrencyPresenter currencyPresenter;
    private Map map;
    private RecyclerView recyclerView;
    private GridLayoutManager recyclerViewLayoutManagerTwo;
    private CurrencyExchangeAdapter currencyExchangeAdapter;
    private ArrayList<CurrencyExchangeQuotes> quotesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        constraintLayout = findViewById(R.id.gridL);
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerViewLayoutManagerTwo = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManagerTwo);
        recyclerView.setAdapter(currencyExchangeAdapter);
        currencyPresenter = new CurrencyPresenter(this);
        final Context mContext = this;

        //populate country spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.curr_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("satya","1a");
                currencyPresenter.getCurrencyExchangeData(mContext, spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResponseFailure(String s) {

    }

    @Override
    public void onResponseSuccess(CurrencyResponseDTO responseDTO) {
        if (responseDTO == null)
            return;
        String ex_quotes = responseDTO.quotes;
        //String ex_quotes = "{ 'USDAED':3.672901,'USDAFN':75.856698}";
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Double>>() {
        }.getType();
        HashMap<String, Double> map = gson.fromJson(ex_quotes, type);
        Log.d("satya","1");
        //add all the entries in map in the grid list
        for(String curr:map.keySet() ){
            Double rate =map.get(curr);
            quotesArrayList.add((new CurrencyExchangeQuotes(curr,rate)));
        }
        currencyExchangeAdapter.notifyDataSetChanged();
    }
}
