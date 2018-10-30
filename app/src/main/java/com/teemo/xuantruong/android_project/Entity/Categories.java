package com.teemo.xuantruong.android_project.entity;

public class Categories {
    private String image;
    private String nameCate;

    public Categories(String image, String nameCate) {
        this.image = image;
        this.nameCate = nameCate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }
}
