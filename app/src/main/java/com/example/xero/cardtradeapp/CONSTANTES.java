package com.example.xero.cardtradeapp;

public class CONSTANTES {
    private String URLBASE;

    public CONSTANTES() {
        this.URLBASE= "http://192.168.1.2:49912/api/";
    }

    public String getURLBASE() {
        return URLBASE;
    }

    public void setURLBASE(String URLBASE) {
        this.URLBASE = URLBASE;
    }
}
