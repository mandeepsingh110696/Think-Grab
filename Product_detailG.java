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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


//Product detail activity

public class Product_detailG extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Button proceed;
    ProductStructure products;
    Product_Grid_Structure products_vertical;
    FirebaseFirestore firebaseFirestoredbbb;
    ImageView product_picture;
    TextView productname,productdesc,productprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        product_picture=findViewById(R.id.product_picture);
        productname=findViewById(R.id.product_name);
        productdesc=findViewById(R.id.product_desc);
        productprice=findViewById(R.id.product_price);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        firebaseFirestoredbbb=FirebaseFirestore.getInstance();
        if(getIntent().hasExtra("product")) {
            products = (ProductStructure) getIntent().getBundleExtra("product").getSerializable("product");
        }
        if(getIntent().hasExtra("verticalproduct")) {
            products_vertical = (Product_Grid_Structure) getIntent().getBundleExtra("verticalproduct").getSerializable("verticalproduct");
        }

        if(products!=null) {
            product_picture.setImageResource(products.getProd_picture());
            productname.setText(products.getProd_name());
            productdesc.setText(products.getProd_desc());
            productprice.setText(products.getProd_price());

        }
        if(products_vertical!=null) {
            product_picture.setImageResource(products_vertical.getProd_picture());
            productname.setText(products_vertical.getProd_name());
            productdesc.setText(products_vertical.getProd_desc());
            productprice.setText(products_vertical.getProd_price());

        }

        proceed= findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(products!=null) {
                    sendHorizontalProductData(products.getProd_name(), products.getProd_desc(), products.getProd_price(), products.getProd_picture());

                }
                if(products_vertical!=null)
                {
                    sendVerticalProductData(products_vertical.getProd_name(), products_vertical.getProd_desc(), products_vertical.getProd_price(), products_vertical.getProd_picture());
                }
            }
        });

    }

    public  void sendVerticalProductData(String prod_name,String prod_desc,String prod_price,int prod_picture){
        Product_Grid_Structure product_grid_structure= new Product_Grid_Structure(prod_name,prod_desc,prod_price,prod_picture);
        firebaseFirestoredbbb.collection("VerticalProduct")
                .add(product_grid_structure)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Product_detailG.this,"Your Product has been added Sucessfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Product_detailG.this,Checkout.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Product_detailG.this,"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });





    }
    public  void sendHorizontalProductData(String prod_name,String prod_desc,String prod_price,int prod_picture){
        ProductStructure productStructure= new ProductStructure(prod_name,prod_desc,prod_price,prod_picture);
        firebaseFirestoredbbb.collection("HorizontalProduct")
                .add(productStructure)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Product_detailG.this,"Your Product has been added Sucessfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Product_detailG.this,Checkout.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Product_detailG.this,"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
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
