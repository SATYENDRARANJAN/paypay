package com.example.paypay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paypay.R;
import com.example.paypay.currencyDTO.CurrencyExchangeQuotes;
import com.example.paypay.view.CurrencyView;

import java.util.ArrayList;

public class CurrencyExchangeAdapter extends RecyclerView.Adapter<CurrencyExchangeAdapter.CurrencyViewHolder> {

    private Context context;
    private ArrayList<CurrencyExchangeQuotes> quotesArrayList;

    public CurrencyExchangeAdapter(Context context, ArrayList<CurrencyExchangeQuotes> arrayList){
        this.context =context;
        this.quotesArrayList = arrayList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_viewholder,viewGroup,false);
        CurrencyViewHolder viewHolder = new CurrencyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder viewHolder, int i) {
       CurrencyExchangeQuotes quote = quotesArrayList.get(i);
       final CurrencyViewHolder holder = (CurrencyViewHolder)viewHolder;
       //
       holder.currency.setText(quote.countryPair);
       holder.currency.setText(String.valueOf(quote.rate));


    }

    @Override
    public int getItemCount() {
        return quotesArrayList.size();
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        public TextView currency ;
        public TextView ex_rate;

        public CurrencyViewHolder(View v) {
            super(v);
            currency = v.findViewById(R.id.text_curr);
            ex_rate = v.findViewById(R.id.text_rate);
        }
    }
}
