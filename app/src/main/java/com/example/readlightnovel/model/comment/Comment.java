package com.example.readlightnovel.model.comment;

import com.example.readlightnovel.model.Success;

import java.util.List;

public class Comment extends Success {
    private List<Data> data;

    public Comment(boolean success, String message, int status_code, List<Data> data) {
        super(success, message, status_code);
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comment{" + "data=" + data + '}';
    }
}
