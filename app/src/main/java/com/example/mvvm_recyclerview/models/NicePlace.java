package com.example.mvvm_recyclerview.models;

public class NicePlace {
private String title;
private String ImageUrl;

    public NicePlace(String title, String imageUrl) {
        this.title = title;
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
