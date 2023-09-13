package com.example.readlightnovel.model.signup;

import android.text.TextUtils;
import android.util.Patterns;

public class Account {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private String username;

    public Account(String email, String firstName, String lastName, String password, String role, String username) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private boolean isValidEmail() {
        return !TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    private boolean isValidPassword() {
        return !TextUtils.isEmpty(getPassword()) && getPassword().length() > 6;
    }

}