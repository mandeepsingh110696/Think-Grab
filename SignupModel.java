package com.example.amaranathyatra.thinkgrab;

public class SignupModel {
    String email, name, pass, conf_pass;

    public SignupModel(String email, String name, String pass, String conf_pass) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.conf_pass = conf_pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConf_pass() {
        return conf_pass;
    }

    public void setConf_pass(String conf_pass) {
        this.conf_pass = conf_pass;
    }
}
