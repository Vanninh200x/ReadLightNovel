package com.example.readlightnovel.model.comic;

public class Data {
    private int id;
    private String avatarUrl;
    private boolean status;
    private String description;
    private String title;
    private boolean isDone;
    private float star;
    private String category;
    private int chapter;


    public Data(int id, String avatarUrl, boolean status, String description, String title, boolean isDone, float star, String category, int chapter) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.description = description;
        this.title = title;
        this.isDone = isDone;
        this.star = star;
        this.category = category;
        this.chapter = chapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public int getChapter() {
        return chapter;
    }


    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String showChapter() {
        return isDone() ? "[Full]: " + getChapter() + " Chapter" : getChapter() + " Chapter";
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", isDone=" + isDone +
                ", star=" + star +
                ", category='" + category + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
