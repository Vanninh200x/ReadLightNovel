package com.example.readlightnovel.model.comment.add;

public class Data {
    private int id;
    private int userId;
    private int bookId;
    private String content;

    public Data(int id, int userId, int bookId, String content) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
