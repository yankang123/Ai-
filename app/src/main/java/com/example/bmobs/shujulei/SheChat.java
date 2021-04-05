package com.example.bmobs.shujulei;

import cn.bmob.v3.BmobObject;

public class SheChat extends BmobObject {
    String name;
    String content;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
