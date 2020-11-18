package com.example.amaranathyatra.thinkgrab;

public class DeliveryAddressModel {
    String  city,locality,aptno,postalcode,province,landmark,name,mobile,alter_mobile;

    public DeliveryAddressModel(String city, String locality, String aptno, String postalcode, String province, String landmark, String name, String mobile, String alter_mobile) {
        this.city = city;
        this.locality = locality;
        this.aptno = aptno;
        this.postalcode = postalcode;
        this.province = province;
        this.landmark = landmark;
        this.name = name;
        this.mobile = mobile;
        this.alter_mobile = alter_mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAptno() {
        return aptno;
    }

    public void setAptno(String aptno) {
        this.aptno = aptno;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlter_mobile() {
        return alter_mobile;
    }

    public void setAlter_mobile(String alter_mobile) {
        this.alter_mobile = alter_mobile;
    }
}
