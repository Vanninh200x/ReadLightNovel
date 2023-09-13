package com.example.readlightnovel.model.user;


public class Data {
    private int id;
    private String username;
    private String avatarUrl;


    public Data(int id, String username, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatarUrl;
    }

    public void setAvatar_url(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
