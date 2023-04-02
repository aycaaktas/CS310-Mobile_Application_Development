package edu.sabanciuniv.myapplication;

import java.time.LocalDateTime;

public class News
{
    private int id;
    private String title, text, imagePath,catagoryName;
    private String date;

    public News(int id, String title, String text, String imagePath, String catagoryName, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.catagoryName = catagoryName;
        this.date = date;
    }

    public News(int id, String title, String text, String imagePath, String catagoryName) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.catagoryName = catagoryName;
    }

    public News()
    {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}