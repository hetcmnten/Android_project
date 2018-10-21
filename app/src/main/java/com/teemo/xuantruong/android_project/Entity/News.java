package com.teemo.xuantruong.android_project.Entity;

public class News {
    private String imageName;
    private String title;
    private String time;

    public News() {
    }

    public News(String imageName, String title, String time) {
        this.imageName = imageName;
        this.title = title;
        this.time = time;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
