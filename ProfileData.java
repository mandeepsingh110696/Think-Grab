package com.example.amaranathyatra.thinkgrab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.amaranathyatra.thinkgrab.network.Apiclient;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Signupnetworkapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileData extends AppCompatActivity {

    Profileadapter profileadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        fetchSignupData();

    }

    public  void fetchSignupData(){
        Call<List<SignupModel>> Call = new Apiclient().getRetrofit().create(Signupnetworkapi.class).fetchSignupData();
        Call.enqueue(new Callback<List<SignupModel>>() {
            @Override
            public void onResponse(Call<List<SignupModel>> call, Response<List<SignupModel>> response) {
                List<SignupModel> modelList =response.body();
                if(modelList!=null && !modelList.isEmpty()) {

                    RecyclerView recyclerView = findViewById(R.id.recyclerViewprofile);
                    profileadapter = new Profileadapter(modelList,ProfileData.this);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(profileadapter);
                }
                //Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<SignupModel>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "You have encountered an error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
