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

import java.util.List;

public class Profileadapter extends RecyclerView.Adapter<Profileadapter.MyViewHolder> {



    private List<SignupModel> signupModelList;
    private Context context;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, pass,conf_pass;
        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            pass = view.findViewById(R.id.pass);
            conf_pass=view.findViewById(R.id.conf_pass);
        }
    }
    public Profileadapter(List<SignupModel> signupModelList,Context context) {
        this.signupModelList=signupModelList;
        this.context=context;
    }
    @NonNull
    @Override
    public Profileadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_view, parent, false);
        return new Profileadapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(Profileadapter.MyViewHolder holder, int position) {
        final SignupModel regdata = signupModelList.get(position);
        holder.name.setText(regdata.getEmail());
        holder.email.setText(regdata.getName());
        holder.pass.setText(regdata.getPass());
        holder.conf_pass.setText(regdata.getConf_pass());


    }
    @Override
    public int getItemCount() {
        return signupModelList.size();
    }
}
