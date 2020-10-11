package com.example.amaranathyatra.thinkgrab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductStructure> productslist;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, price;
        ImageView picture_product;
        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
            desc = view.findViewById(R.id.product_desc);
            price = view.findViewById(R.id.product_price);
            picture_product=view.findViewById(R.id.product_picture);
        }
    }
    public ProductAdapter(List<ProductStructure> productslist) {
        this.productslist = productslist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_product_design, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductStructure products = productslist.get(position);
        holder.name.setText(products.getProd_name());
        holder.desc.setText(products.getProd_desc());
        holder.price.setText(products.getProd_price());
        holder.picture_product.setImageResource(products.getProd_picture());
    }
    @Override
    public int getItemCount() {
        return productslist.size();
    }

}
