package com.example.diogo.webchanel;

import android.app.Application;

import com.example.diogo.webchanel.model.Enterprise;
import com.example.diogo.webchanel.model.User;

public class MyApplication extends Application {

    //APPLICATION GLOBAL VARIABLES
    private Enterprise enterprise;
    private User user;
    private boolean newEnterprise = false;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNewEnterprise() {
        return newEnterprise;
    }

    public void setNewEnterprise(boolean is_new_enterprise) {
        this.newEnterprise = is_new_enterprise;
    }
}
