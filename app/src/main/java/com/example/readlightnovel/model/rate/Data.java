package com.example.readlightnovel.model.rate;


public class Data {
    private long id;
    private long bookId;
    private long userId;
    private long star;


    public Data(long id, long bookId, long userId, long star) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.star = star;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", star=" + star +
                '}';
    }
}
