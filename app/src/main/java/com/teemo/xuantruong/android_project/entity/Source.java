package com.teemo.xuantruong.android_project.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Source implements Serializable {
    private int source_id;
    private String source_title;
    private String source_img;
    private ArrayList<Category> source_categories;

    public Source() {
    }

    public Source(ArrayList<Category> source_categories) {
        this.source_categories = source_categories;
    }

    public Source(String source_title, String source_img, ArrayList<Category> source_categories) {
        this.source_title = source_title;
        this.source_img = source_img;
        this.source_categories = source_categories;
    }

    public Source(int source_id, String source_title, String source_img, ArrayList<Category> source_categories) {
        this.source_id = source_id;
        this.source_title = source_title;
        this.source_img = source_img;
        this.source_categories = source_categories;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public String getSource_title() {
        return source_title;
    }

    public void setSource_title(String source_title) {
        this.source_title = source_title;
    }

    public String getSource_img() {
        return source_img;
    }

    public void setSource_img(String source_img) {
        this.source_img = source_img;
    }

    public ArrayList<Category> getSource_categories() {
        return source_categories;
    }

    public void setSource_categories(ArrayList<Category> source_categories) {
        this.source_categories = source_categories;
    }
}
