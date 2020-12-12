package com.example.amaranathyatra.thinkgrab.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {


   public Retrofit getRetrofit() {
       return  initializeRetrofitService("http://192.168.2.18:4000");
    }


    /**
     * Initialize Retrofit Api Service
     *
     * @param pBaseUrl - Url for Api
     *
     *
     * Build Connection or handshaking with url and Model
     */


    private Retrofit initializeRetrofitService(String pBaseUrl){
        return new  Retrofit.Builder()
                .baseUrl(pBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client( new  OkHttpClient.Builder().build())
                .build();

    }
}


