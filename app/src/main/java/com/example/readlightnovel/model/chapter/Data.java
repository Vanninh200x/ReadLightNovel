package com.example.readlightnovel.model.chapter;


public class Data {
    private int bookId;
    private String name;
    private String number;
    private String chapterTitle;
    private String content;

    public Data(int bookId, String name, String number, String chapterTitle, String content) {
        this.bookId = bookId;
        this.name = name;
        this.number = number;
        this.chapterTitle = chapterTitle;
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Data{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", chapterTitle='" + chapterTitle + '\'' +
                '}';
    }
}
