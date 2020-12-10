package com.example.amaranathyatra.thinkgrab;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Product grid structure

public class Product_Grid_Structure  implements Serializable {


    @SerializedName("name")
    private String prod_name;
    @SerializedName("description")
    private String prod_desc;
    @SerializedName("picture")
    private int prod_picture;
    @SerializedName("price")
    private String prod_price;

    public Product_Grid_Structure(String prod_name, String prod_desc, int prod_picture, String prod_price) {
        this.prod_name = prod_name;
        this.prod_desc = prod_desc;
        this.prod_picture = prod_picture;
        this.prod_price = prod_price;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public int getProd_picture() {
        return prod_picture;
    }

    public void setProd_picture(int prod_picture) {
        this.prod_picture = prod_picture;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }
}