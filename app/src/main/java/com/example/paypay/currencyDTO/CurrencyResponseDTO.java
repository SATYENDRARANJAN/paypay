package com.example.paypay.currencyDTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrencyResponseDTO {

    @SerializedName("success")
    public boolean success;
    @SerializedName("terms")
    public String terms ;
    @SerializedName("privacy")
    public String privacy;
    @SerializedName("timestamp")
    public long timestamp;
    @SerializedName("source")
    public String source ;
    @SerializedName("quotes")
    public String quotes;
}
