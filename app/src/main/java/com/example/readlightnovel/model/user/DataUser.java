package com.example.readlightnovel.model.user;

import com.example.readlightnovel.model.Success;

import java.util.ArrayList;

public class DataUser extends Success {

    private Data data;

    public DataUser(boolean success, String message, int status_code, Data data) {
        super(success, message, status_code);
        this.data = data;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}
