package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.SignupModel;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Signupnetworkapi {
    @POST("/signup")
    Call<JSONObject> insertSignupData(@Body SignupModel signupModel);

    @GET("/fetchsignup")
    Call<List<SignupModel>> fetchSignupData();
}
