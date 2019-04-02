package com.example.paypay.presenter;

import android.content.Context;
import android.util.Log;

import com.example.paypay.currencyDTO.CurrencyResponseDTO;
import com.example.paypay.manager.CurrencyConnectionManager;
import com.example.paypay.network.APICallback;
import com.example.paypay.view.CurrencyView;

public class CurrencyPresenter {

    CurrencyView currencyView;
    public CurrencyPresenter(CurrencyView currencyView){
        this.currencyView =currencyView;
    }

    public void getCurrencyExchangeData(Context context, String country){
        Log.d("satya","1a1");
        CurrencyConnectionManager.getCurrencyExchangeDataSet(context, country, new APICallback<CurrencyResponseDTO>(){

            @Override
            public void onResponseFailure(String message) {
                CurrencyView view = currencyView;
                if(view== null)
                    return;
                view.onResponseFailure("Error occured!");
            }

            @Override
            public void onResponseError(CurrencyResponseDTO responseDTO) {
                CurrencyView view = currencyView;
                if(view== null)
                    return;
                view.onResponseFailure("Error occured!");
            }

            @Override
            public void onResponseSuccess(CurrencyResponseDTO responseDTO) {
                Log.d("satya","1b" +responseDTO.quotes);
                if(responseDTO==null || responseDTO.quotes==null)
                    return;
                Log.d("satya","1c");
                CurrencyView view = (CurrencyView)currencyView;
                if(view==null)
                    return;
                view.onResponseSuccess(responseDTO);
            }
        });

    }
}
