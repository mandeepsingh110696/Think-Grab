package com.example.amaranathyatra.thinkgrab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Product_Grid_Adapter extends RecyclerView.Adapter<Product_Grid_Adapter.MyViewHolder> {

    private List<Product_Grid_Structure> grid_list;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, price;
        ImageView picture_product;
        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.hs_product_name);
            desc = view.findViewById(R.id.hs_product_desc);
            price = view.findViewById(R.id.hs_product_price);
            picture_product=view.findViewById(R.id.hs_product_image);
        }
    }
    public Product_Grid_Adapter(List<Product_Grid_Structure> grid_list) {
        this.grid_list=grid_list;
    }
    @NonNull
    @Override
    public Product_Grid_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_product, parent, false);
        return new Product_Grid_Adapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(Product_Grid_Adapter.MyViewHolder holder, int position) {
        Product_Grid_Structure products = grid_list.get(position);
        holder.name.setText(products.getProd_name());
        holder.desc.setText(products.getProd_desc());
        holder.price.setText(products.getProd_price());
        holder.picture_product.setImageResource(products.getProd_picture());
    }
    @Override
    public int getItemCount() {
        return grid_list.size();
    }
}
