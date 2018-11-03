package com.teemo.xuantruong.android_project.entity;

import java.util.ArrayList;

public class Category {
    private String title;
    private String ImageName;
    private ArrayList<Poster_entity> listPosters;

    public Category(String title, String imageName) {
        this.title = title;
        ImageName = imageName;
    }

    public Category(String title, String imageName, ArrayList<Poster_entity> listPosters) {
        this.title = title;
        ImageName = imageName;
        this.listPosters = listPosters;
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

    public ArrayList<Poster_entity> getListPosters() {
        return listPosters;
    }

    public void setListPosters(ArrayList<Poster_entity> listPosters) {
        this.listPosters = listPosters;
    }
}
