package com.example.paypay.view;

import com.example.paypay.currencyDTO.CurrencyResponseDTO;

public interface CurrencyView {
    public void onResponseFailure(String s) ;
    public void onResponseSuccess(CurrencyResponseDTO responseDTO);
}
