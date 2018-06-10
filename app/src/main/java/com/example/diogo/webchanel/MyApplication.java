package com.example.diogo.webchanel;

import android.app.Application;

import com.example.diogo.webchanel.model.Enterprise;

public class MyApplication extends Application {

    //APPLICATION GLOBAL VARIABLES
    private Enterprise enterprise;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
