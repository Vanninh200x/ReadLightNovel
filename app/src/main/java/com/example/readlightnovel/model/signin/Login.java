package com.example.readlightnovel.model.signin;

import com.example.readlightnovel.model.Success;

public class Login extends Success {
    private Data data;

    public Login(boolean success, String message, int status_code,  Data data) {
        super(success, message, status_code);
        this.data = data;
    }
    public Data getDataLogin() {
        return data;
    }

    public void setDataLogin(Data dataLogin) {
        this.data = dataLogin;
    }


}
