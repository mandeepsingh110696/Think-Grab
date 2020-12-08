package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.SignupModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Productnetworkapi {

    @POST("/homeproducts")
    Call<JSONObject> insertProducts(@Body SignupModel signupModel);
}
