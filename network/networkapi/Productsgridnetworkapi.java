package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.Product_Grid_Structure;
import com.example.amaranathyatra.thinkgrab.SignupModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Productsgridnetworkapi {

    @POST("/homeproducts")
    Call<JSONObject> insertProductsGrid(@Body Product_Grid_Structure product_grid_structure);
}
