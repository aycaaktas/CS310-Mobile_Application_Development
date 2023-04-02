package edu.sabanciuniv.myapplication;

public class Comments {

private int id;
private int newsid;
private String mtext;
private String name;


    public Comments(int id, int newsid, String mtext, String name) {
        this.id = id;
        this.newsid = newsid;
        this.mtext = mtext;
        this.name = name;
    }

    public Comments(int newsid, String mtext, String name) {
        this.newsid = newsid;
        this.mtext = mtext;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
