package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.ProductStructure;
import com.example.amaranathyatra.thinkgrab.Product_Grid_Structure;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Productshorizontalnetworkapi {

    @POST("/homeproducts")
    Call<JSONObject> insertProductsHorizontal(@Body ProductStructure  productStructure);
}

