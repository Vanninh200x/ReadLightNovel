package com.example.readlightnovel.model.signin;

import android.text.TextUtils;
import android.util.Patterns;

public class User {
    private String account;
    private String password;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidEmail() {
        return !TextUtils.isEmpty(getAccount()) && Patterns.EMAIL_ADDRESS.matcher(getAccount()).matches();
    }

    public boolean isValidPassword() {
        return !TextUtils.isEmpty(getPassword()) && getPassword().length() > 6;
    }

    @Override
    public String toString() {
        return "User{" + "account='" + account + '\'' + ", password='" + password + '\'' + '}';
    }
}
