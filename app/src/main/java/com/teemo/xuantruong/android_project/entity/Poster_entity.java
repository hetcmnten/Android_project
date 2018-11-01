package com.teemo.xuantruong.android_project.entity;

import java.io.Serializable;

public class Poster_entity implements Serializable {

    private  String id_poster;
    private  String type_poster;
    private  String title_poster;
    private  String link_poster;
    private  String category_poster;
    private  String content_poster;
    private  String image_poster;
    private  String author_poster;
    private String time_poster ;

    public Poster_entity() {

    }

    public Poster_entity(String id_poster, String type_poster, String title_poster, String link_poster, String category_poster, String content_poster, String image_poster, String author_poster, String time_poster) {
        this.id_poster = id_poster;
        this.type_poster = type_poster;
        this.title_poster = title_poster;
        this.link_poster = link_poster;
        this.category_poster = category_poster;
        this.content_poster = content_poster;
        this.image_poster = image_poster;
        this.author_poster = author_poster;
        this.time_poster = time_poster;
    }

    public Poster_entity(String image_poster, String title_poster , String time_poster, String content_poster ) {
        this.title_poster = title_poster;
        this.content_poster = content_poster;
        this.image_poster = image_poster;
        this.time_poster = time_poster;
    }

    public String getId_poster() {
        return id_poster;
    }

    public void setId_poster(String id_poster) {
        this.id_poster = id_poster;
    }

    public String getAuthor_poster() {
        return author_poster;
    }

    public void setAuthor_poster(String author_poster) {
        this.author_poster = author_poster;
    }

    public String getContent_poster() {
        return content_poster;
    }

    public void setContent_poster(String content_poster) {
        this.content_poster = content_poster;
    }

    public String getImage_poster() {
        return image_poster;
    }

    public void setImage_poster(String image_poster) {
        this.image_poster = image_poster;
    }

    public String getTime_poster() {
        return time_poster;
    }

    public void setTime_poster(String time_poster) {
        this.time_poster = time_poster;
    }

    public String getType_poster() {
        return type_poster;
    }

    public void setType_poster(String type_poster) {
        this.type_poster = type_poster;
    }

    public String getTitle_poster() {
        return title_poster;
    }

    public void setTitle_poster(String title_poster) {
        this.title_poster = title_poster;
    }

    public String getLink_poster() {
        return link_poster;
    }

    public void setLink_poster(String link_poster) {
        this.link_poster = link_poster;
    }

    public String getCategory_poster() {
        return category_poster;
    }

    public void setCategory_poster(String category_poster) {
        this.category_poster = category_poster;
    }
}
