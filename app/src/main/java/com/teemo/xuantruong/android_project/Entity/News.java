package com.teemo.xuantruong.android_project.entity;

import java.io.Serializable;

public class News implements Serializable {
    private String imageName;
    private String title;
    private String time;
    private String conttent;

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

    public News(String imageName, String title, String time, String conttent) {
        this.imageName = imageName;
        this.title = title;
        this.time = time;
        this.conttent = conttent;
    }

    public String getConttent() {
        return conttent;
    }

    public void setConttent(String conttent) {
        this.conttent = conttent;
    }
}
