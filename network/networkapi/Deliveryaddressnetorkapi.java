package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.DeliveryAddressModel;
import com.example.amaranathyatra.thinkgrab.ProductStructure;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Deliveryaddressnetorkapi {
    @POST("/deliveryaddress")
    Call<JSONObject> insertDeliveryAddress(@Body DeliveryAddressModel deliveryAddressModel);
}
