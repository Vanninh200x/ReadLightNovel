package com.example.readlightnovel.model.rate;

import com.example.readlightnovel.model.Success;

public class Rate extends Success {
    private Data data;

    public Rate(boolean success, String message, int status_code,Data data) {
        super(success, message, status_code);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "data=" + data +
                '}';
    }
}
