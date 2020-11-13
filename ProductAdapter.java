package com.example.amaranathyatra.thinkgrab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

//Product horizontal adapter

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductStructure> productslist;
    FirebaseFirestore firebaseFirestoredbbbb;
    private Context  context;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, price;
        ImageView picture_product;
        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
            desc = view.findViewById(R.id.product_desc);
            price = view.findViewById(R.id.product_price);
            picture_product=view.findViewById(R.id.product_picture);
            firebaseFirestoredbbbb=FirebaseFirestore.getInstance();

        }
    }
    public ProductAdapter(List<ProductStructure> productslist,Context context) {
        this.productslist = productslist;
        this.context=context;
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
        final ProductStructure products = productslist.get(position);
        holder.name.setText(products.getProd_name());
        holder.desc.setText(products.getProd_desc());
        holder.price.setText(products.getProd_price());
        holder.picture_product.setImageResource(products.getProd_picture());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendHorizontalProductData(products.getProd_name(),products.getProd_desc(),products.getProd_price(),products.getProd_picture());
                Intent intent = new Intent(context,Product_detailG.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product",products);
                intent.putExtra("product",bundle);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return productslist.size();
    }

}
