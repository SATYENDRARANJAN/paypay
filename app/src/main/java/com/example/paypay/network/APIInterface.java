package com.example.paypay.network;

import com.example.paypay.currencyDTO.CurrencyResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
//     http://www.apilayer.net/api/live?access_key=6e9282e2ebbfe5b1f948f8880d449dfc

    @GET("https://www.apilayer.net/api/live?access_key=6e9282e2ebbfe5b1f948f8880d449dfc")
    Call<CurrencyResponseDTO> getCurrencyExchangeDataSet(@Query("access_key") String accessKey/*,@Query("country") String country*/);

}
