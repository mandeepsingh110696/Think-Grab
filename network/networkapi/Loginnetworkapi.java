package com.example.amaranathyatra.thinkgrab.network.networkapi;

import com.example.amaranathyatra.thinkgrab.LoginModel;
import com.example.amaranathyatra.thinkgrab.PaymentModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Loginnetworkapi {
    @POST("/signin")
    Call<JSONObject> signinapi(@Body LoginModel loginModel);

}
