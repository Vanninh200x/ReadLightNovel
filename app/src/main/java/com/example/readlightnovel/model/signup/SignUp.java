package com.example.readlightnovel.model.signup;


import com.example.readlightnovel.model.Success;

public class SignUp extends Success {
    private DataSignUp data;

    public SignUp(boolean success, String message, int status_code, DataSignUp data) {
        super(success, message, status_code);
        this.data = data;
    }


    public DataSignUp getData() {
        return data;
    }

    public void setData(DataSignUp data) {
        this.data = data;
    }


}
