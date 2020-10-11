package com.example.amaranathyatra.thinkgrab;

public class ProductStructure {
    private  String prod_name;
    private  String prod_desc;
    private  String prod_price;
    private  int prod_picture;

    public ProductStructure(String prod_name, String prod_desc, String prod_price, int prod_picture) {
        this.prod_name = prod_name;
        this.prod_desc = prod_desc;
        this.prod_price = prod_price;
        this.prod_picture = prod_picture;
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

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public int getProd_picture() {
        return prod_picture;
    }

    public void setProd_picture(int prod_picture) {
        this.prod_picture = prod_picture;
    }
}
