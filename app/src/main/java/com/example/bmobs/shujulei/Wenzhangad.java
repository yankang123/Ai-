package com.example.bmobs.shujulei;

public class Wenzhangad {
    String title;
    String url;
    int ImageId;
    public Wenzhangad(String title, String url){
        this.title=title;
        this.url=url;
    }

    public Wenzhangad(String title, int imageId){
        this.title=title;
        this.ImageId=imageId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
