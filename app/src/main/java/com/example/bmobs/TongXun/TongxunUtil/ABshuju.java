package com.example.bmobs.TongXun.TongxunUtil;


import org.litepal.crud.DataSupport;

public class ABshuju extends DataSupport {
    String Aid;
    String A;
    String Bid;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    String B;
}
