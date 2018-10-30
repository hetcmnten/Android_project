package com.teemo.xuantruong.android_project.entity;

public class Category {
    private String title;
    private String ImageName;

    public Category(String title, String imageName) {
        this.title = title;
        ImageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }
}
