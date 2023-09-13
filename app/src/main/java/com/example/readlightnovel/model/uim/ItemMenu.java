package com.example.readlightnovel.model.uim;

import android.graphics.drawable.Drawable;

import java.util.List;

public class ItemMenu {
    private int icon;
    private String title;

    public ItemMenu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public ItemMenu(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
