package com.example.amaranathyatra.thinkgrab;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class Payment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardForm cardForm;
    Button checkout;
    AlertDialog.Builder alertBuilder;

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


      //  cardForm = findViewById(R.id.card_form);
        checkout = findViewById(R.id.checkout);
//        cardForm.cardRequired(true)
//                .expirationRequired(true)
//                .cvvRequired(true)
//                .postalCodeRequired(true)
//                .mobileNumberRequired(true)
//                .mobileNumberExplanation("SMS is required on this number")
//                .setup(Payment.this);
//        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//        checkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cardForm.isValid()) {
//                    alertBuilder = new AlertDialog.Builder(Payment.this);
//                    alertBuilder.setTitle("Confirm before purchase");
//                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
//                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
//                            "Card CVV: " + cardForm.getCvv() + "\n" +
//                            "Postal code: " + cardForm.getPostalCode() + "\n" +
//                            "Phone number: " + cardForm.getMobileNumber());
//                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                            Toast.makeText(Payment.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//                    AlertDialog alertDialog = alertBuilder.create();
//                    alertDialog.show();
//
//                } else {
//                    Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this,ThankYou.class);
                startActivity(intent);
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
