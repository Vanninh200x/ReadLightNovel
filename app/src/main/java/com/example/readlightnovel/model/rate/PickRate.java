package com.example.readlightnovel.model.rate;

public class PickRate {
    private long bookId;
    private long star;

    public PickRate(long bookId, long star) {
        this.bookId = bookId;
        this.star = star;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "PickRate{" + "bookId=" + bookId + ", star=" + star + '}';
    }
}
