package com.example.amaranathyatra.thinkgrab;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.amaranathyatra.thinkgrab.network.Apiclient;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Paymentinfonetworkapi;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Signupnetworkapi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

       //Payment Main Class
    Button checkout;
    AlertDialog.Builder alertBuilder;
    EditText cardno,expdate,cvv,postalcode,countrycode,mobileno;
    FirebaseFirestore firebaseFirestoredbbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_payment_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Payment Information");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        cardno=findViewById(R.id.cardno);
        expdate=findViewById(R.id.expdate);
        cvv=findViewById(R.id.cvv);
        postalcode=findViewById(R.id.postalcode);
        countrycode=findViewById(R.id.countrycode);
        mobileno=findViewById(R.id.mobileno);
        checkout = findViewById(R.id.checkout);

        firebaseFirestoredbbb = FirebaseFirestore.getInstance();





        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardnoo=cardno.getText().toString();
                String expdatee=expdate.getText().toString();
                String cvvv=cvv.getText().toString();
                String postalcodee=postalcode.getText().toString();
                String countrycodee=countrycode.getText().toString();
                String mobilenoo=mobileno.getText().toString();
                if(TextUtils.isEmpty(cardnoo)){
                    cardno.setError("cardno is required");
                    return;
                }
                if(TextUtils.isEmpty(expdatee)){
                    expdate.setError("expiration date is required");
                    return;
                }
                if(TextUtils.isEmpty(cvvv)){
                    cvv.setError("cvv is required");
                    return;
                }
                if(TextUtils.isEmpty(postalcodee)){
                    postalcode.setError("postalcode is required");
                    return;
                }
                if(TextUtils.isEmpty(countrycodee)){
                    countrycode.setError("countrycode is required");
                    return;
                }
                if(TextUtils.isEmpty(mobilenoo)){
                    mobileno.setError("mobileno is required");
                    return;
                }
                submitPaymentData();
                sendPaymentData(cardnoo,expdatee,cvvv,postalcodee,countrycodee,mobilenoo);

                Intent intent = new Intent(Payment.this,ThankYou.class);
                startActivity(intent);
            }
        });



    }

    public  void sendPaymentData(String cardno,String expdate,String cvv,String postalcode,String countrycode,String mobileno){
        PaymentModel paymentModel = new PaymentModel(cardno,expdate,cvv,postalcode,countrycode,mobileno);
        firebaseFirestoredbbb.collection("PaymentInfo")
                .add(paymentModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Your Payment has been done Sucessfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });





    }





    public  void submitPaymentData(){
        PaymentModel paymentModel = new PaymentModel(cardno.getText().toString(),expdate.getText().toString(),cvv.getText().toString(),postalcode.getText().toString(),countrycode.getText().toString(),mobileno.getText().toString());
        Call<JSONObject> jsonObjectCall = new Apiclient().getRetrofit().create(Paymentinfonetworkapi.class).insertPaymentInfo(paymentModel);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Toast.makeText(getApplicationContext(),"Your Payment has been done Sucessfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Payment.this,ThankYou.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "You have encountered an error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(getApplicationContext(),ProfileData.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
