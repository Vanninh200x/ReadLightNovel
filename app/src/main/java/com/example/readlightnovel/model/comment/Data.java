package com.example.readlightnovel.model.comment;

public class Data {
    private int userId;
    private String avatar_url;
    private String userName;
    private String content;

    public Data(int userId, String avatar_url, String userName, String content) {
        this.userId = userId;
        this.avatar_url = avatar_url;
        this.userName = userName;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Data{" + "userId=" + userId + ", avatar_url='" + avatar_url + '\'' + ", userName='" + userName + '\'' + ", content='" + content + '\'' + '}';
    }
}
