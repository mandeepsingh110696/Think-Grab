package com.example.amaranathyatra.thinkgrab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ProductStructure> products_list = new ArrayList<>();
    private List<Product_Grid_Structure> grid_products_list = new ArrayList<>();
    private ProductAdapter productAdapter;
    private Product_Grid_Adapter grid_adapter;
    CarouselView carouselView;
    FirebaseAuth firebaseAuth;

    int[] sampleImages = {R.drawable.banneerecomm1, R.drawable.banner2ecomm, R.drawable.banner3ecomm, R.drawable.nikebanner4, R.drawable.nikestrip2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Think & Grab");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        firebaseAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        setView();


    }

    public void setView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        productAdapter = new ProductAdapter(products_list,this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);
        ProductStructure products = new ProductStructure("IPhone11Pro", "Memory, epic & clear",  R.drawable.mobileiphone,"1900$");
        products_list.add(products);
        products = new ProductStructure("BeautyGel", "Beautiful, Kids & Family",  R.drawable.beautygel,"200$");
        products_list.add(products);
        products = new ProductStructure("Sofaset", "Comfortable, Home & Rest",  R.drawable.sofaprod,"1000$");
        products_list.add(products);
        products = new ProductStructure("Weights", "Fitness, Hardwork & Discipline",  R.drawable.weightprod,"1040$");
        products_list.add(products);
        products = new ProductStructure("Earphone", "Inspiration, Enjoy & Mood",  R.drawable.earphone,"200$");
        products_list.add(products);
        products = new ProductStructure("Book", "Reading, Creativity & Thinking",  R.drawable.bookprod,"100$");
        products_list.add(products);
        productAdapter.notifyDataSetChanged();


        RecyclerView recyclerViewgrid = findViewById(R.id.recyclerViewGrid);
        grid_adapter = new Product_Grid_Adapter(grid_products_list,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerViewgrid.setLayoutManager(gridLayoutManager);
        recyclerViewgrid.setItemAnimator(new DefaultItemAnimator());
        recyclerViewgrid.setAdapter(grid_adapter);
        Product_Grid_Structure grid_products = new Product_Grid_Structure("Sofaset", "Comfortable, Home & Rest", R.drawable.sofaprod,"1900$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Book", "Reading, Creativity & Thinking",  R.drawable.bookprod,"200$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("BeautyGel", "Animation, Kids & Family", R.drawable.beautygel,"600$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Weights", "Fitness, Hardwork & Discipline",  R.drawable.weightprod,"800$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("IPhone11Pro", "Memory, epic & clear",R.drawable.mobileiphone,"1700$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Earphone", "Inspiration, Enjoy & Mood",  R.drawable.earphone,"100$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Book", "Reading, Creativity & Thinking", R.drawable.bookprod,"200$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("BeautyGel", "Animation, Kids & Family", R.drawable.beautygel,"600$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Weights", "Fitness, Hardwork & Discipline", R.drawable.weightprod,"800$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("IPhone11Pro", "Memory, epic & clear",  R.drawable.mobileiphone,"1700$");
        grid_products_list.add(grid_products);
        grid_products = new Product_Grid_Structure("Earphone", "Inspiration, Enjoy & Mood",  R.drawable.earphone,"100$");
        grid_products_list.add(grid_products);


        grid_adapter.notifyDataSetChanged();

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


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
            FirebaseAuth.getInstance().signOut();
            Intent in = new Intent(Navigation.this,Login.class);
            startActivity(in);
            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
