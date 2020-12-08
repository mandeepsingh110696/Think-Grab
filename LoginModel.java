package com.example.amaranathyatra.thinkgrab;

import com.google.gson.annotations.SerializedName;

public class LoginModel {


    @SerializedName("email")
    String email_signin;
    @SerializedName("password")
    String    pass_signin;

    public LoginModel(String email_signin, String pass_signin) {
        this.email_signin = email_signin;
        this.pass_signin = pass_signin;
    }

    public String getEmail_signin() {
        return email_signin;
    }

    public void setEmail_signin(String email_signin) {
        this.email_signin = email_signin;
    }

    public String getPass_signin() {
        return pass_signin;
    }

    public void setPass_signin(String pass_signin) {
        this.pass_signin = pass_signin;
    }
}
