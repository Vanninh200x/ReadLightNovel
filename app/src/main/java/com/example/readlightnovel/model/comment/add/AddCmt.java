package com.example.readlightnovel.model.comment.add;

import com.example.readlightnovel.model.Success;

public class AddCmt extends Success {
    private Data data;

    public AddCmt(boolean success, String message, int status_code, Data data) {
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
