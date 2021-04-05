package com.example.bmobs.shujulei;

import cn.bmob.v3.BmobObject;

public class Liaotian extends BmobObject {
    String A;
    String B;
    String Aid;
    String Bid;
    int shunxu;

    public int getShunxu() {
        return shunxu;
    }

    public void setShunxu(int shunxu) {
        this.shunxu = shunxu;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }



    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }
}
