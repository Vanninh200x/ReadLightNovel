package com.example.readlightnovel.model.comic;

import com.example.readlightnovel.model.Success;

import java.util.ArrayList;

public class Comic extends Success {
    private ArrayList<Data> data;

    public Comic(boolean success, String message, int status_code,ArrayList<Data> data) {
        super(success, message, status_code);
        this.data = data;
    }


    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "data=" + data +
                '}';
    }
}
