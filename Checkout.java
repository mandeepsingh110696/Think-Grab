package com.example.amaranathyatra.thinkgrab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amaranathyatra.thinkgrab.network.Apiclient;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Deliveryaddressnetorkapi;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Signupnetworkapi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Button save;
    EditText city,locality,aptno,postalcode,province,landmark,name,mobile,alter_mobile;
    FirebaseFirestore firebaseFirestoredeliveryadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_delivery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Delivery Address");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        city=findViewById(R.id.city);
        locality=findViewById(R.id.locality);
        aptno=findViewById(R.id.aptno);
        postalcode=findViewById(R.id.postalcode);
        province=findViewById(R.id.province);
        landmark=findViewById(R.id.landmark);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        alter_mobile=findViewById(R.id.alter_mobile);
        save= findViewById(R.id.save_btn);
        firebaseFirestoredeliveryadd=FirebaseFirestore.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityy=city.getText().toString();
                String localityy=locality.getText().toString();
                String aptnoo=aptno.getText().toString();
                String postalcodee=postalcode.getText().toString();
                String provincee=province.getText().toString();
                String landmarkk=landmark.getText().toString();
                String namee=name.getText().toString();
                String mobilee=mobile.getText().toString();
                String altermobile=alter_mobile.getText().toString();
                if(TextUtils.isEmpty(cityy)){
                    city.setError("City name is required");
                    return;
                }
                if(TextUtils.isEmpty(localityy)){
                    locality.setError("Locality name is required");
                    return;
                }
                if(TextUtils.isEmpty(aptnoo)){
                    aptno.setError("Aptno is required");
                    return;
                }
                if(TextUtils.isEmpty(postalcodee)){
                    postalcode.setError("Postalcode is required");
                    return;
                }
                if(TextUtils.isEmpty(provincee)){
                    province.setError("Province name is required");
                    return;
                }
                if(TextUtils.isEmpty(namee)){
                    name.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(mobilee)){
                    mobile.setError("city name is required");
                    return;
                }
                submitDeliveryAddressData();
                sendDeliveryAddressData(cityy,localityy,aptnoo,postalcodee,provincee,landmarkk,namee,mobilee,altermobile);
                Intent intent = new Intent(Checkout.this,Payment.class);
                startActivity(intent);
            }
        });
    }
    public  void sendDeliveryAddressData(String  city,String locality,String aptno,String postalcode,String province,String landmark,String name,String mobile,String alter_mobile){
        DeliveryAddressModel deliveryAddressModel = new DeliveryAddressModel( city,locality,aptno,postalcode,province,landmark,name,mobile,alter_mobile);
        firebaseFirestoredeliveryadd.collection("DeliveryAddress")
                .add(deliveryAddressModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Your Delivery address has been saved Sucessfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });





    }


    public  void submitDeliveryAddressData(){
        city=findViewById(R.id.city);
        locality=findViewById(R.id.locality);
        aptno=findViewById(R.id.aptno);
        postalcode=findViewById(R.id.postalcode);
        province=findViewById(R.id.province);
        landmark=findViewById(R.id.landmark);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        alter_mobile=findViewById(R.id.alter_mobile);
      DeliveryAddressModel deliveryAddressModel= new DeliveryAddressModel(city.getText().toString(),locality.getText().toString(),aptno.getText().toString(),postalcode.getText().toString(),province.getText().toString(),landmark.getText().toString(),name.getText().toString(),mobile.getText().toString(),alter_mobile.getText().toString());
        Call<JSONObject> jsonObjectCall = new Apiclient().getRetrofit().create(Deliveryaddressnetorkapi.class).insertDeliveryAddress(deliveryAddressModel);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Toast.makeText(getApplicationContext(),"Your Delivery address has been saved Sucessfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Checkout.this,Payment.class);
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
