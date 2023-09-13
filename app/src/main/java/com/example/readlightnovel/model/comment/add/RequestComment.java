package com.example.readlightnovel.model.comment.add;

public class RequestComment {
    private int bookId;
    private String content;

    public RequestComment(int bookId, String content) {
        this.bookId = bookId;
        this.content = content;
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
