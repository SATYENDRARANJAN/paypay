package com.example.paypay.network;

import android.content.Context;

import com.example.paypay.currencyDTO.CurrencyResponseDTO;

import retrofit2.Call;
import retrofit2.Response;

public abstract  class APICallback<T> implements  retrofit2.Callback {

    Context context;

    public APICallback(){
    }

    public APICallback(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if(response.isSuccessful()){
            CurrencyResponseDTO responseDTO = (CurrencyResponseDTO) response.body();
            if(responseDTO != null)
                onResponseSuccess(responseDTO);
            else
                onResponseError(responseDTO);
        }else{
            onResponseFailure(response.message());
        }
    }

    public abstract void onResponseFailure(String message);

    public abstract void onResponseError(CurrencyResponseDTO responseDTO);

    public abstract void onResponseSuccess(CurrencyResponseDTO responseDTO);

    @Override
    public void onFailure(Call call, Throwable t) {
        onResponseFailure("OOPS . There is a Problem ! "+t.toString());

    }
}
