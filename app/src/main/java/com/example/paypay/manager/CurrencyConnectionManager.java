package com.example.paypay.manager;

import android.content.Context;
import android.util.Log;

import com.example.paypay.currencyDTO.CurrencyResponseDTO;

import com.example.paypay.network.APICallback;
import com.example.paypay.network.APIServiceUtil;
import retrofit2.Call;

public class CurrencyConnectionManager {
//http://www.apilayer.net/api/live?access_key=6e9282e2ebbfe5b1f948f8880d449dfc
    public static void getCurrencyExchangeDataSet(Context context , String country , APICallback<CurrencyResponseDTO> apiCallback){
        Log.d("satya","1");
        Call<CurrencyResponseDTO> callObj = APIServiceUtil.getInstance(context).getAPIInterface()
                            .getCurrencyExchangeDataSet("6e9282e2ebbfe5b1f948f8880d449dfc"/*,country*/);
        callObj.enqueue(apiCallback);
    }
}
