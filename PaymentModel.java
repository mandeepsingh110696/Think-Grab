package com.example.amaranathyatra.thinkgrab;

public class PaymentModel {
// Payment Model

    String cardno,expdate,cvv,postalcode,countrycode,mobileno;

    public PaymentModel(String cardno, String expdate, String cvv, String postalcode, String countrycode, String mobileno) {
        this.cardno = cardno;
        this.expdate = expdate;
        this.cvv = cvv;
        this.postalcode = postalcode;
        this.countrycode = countrycode;
        this.mobileno = mobileno;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
