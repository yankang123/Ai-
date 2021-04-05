package com.example.bmobs.shujulei;

import cn.bmob.v3.BmobObject;

public class Wenzhang extends BmobObject {
    private String title;
    private String dizhi;

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    private int imageid;
    private String neirong;


    public String getNeirong(){
        return neirong;
    }
    public void setNeirong(String neirong){
        this.neirong=neirong;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

}