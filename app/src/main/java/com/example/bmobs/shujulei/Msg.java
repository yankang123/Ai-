package com.example.bmobs.shujulei;

public class Msg {
    public static final int Type_Receive=0;
    public static final int Type_Send=1;
    private int type;
    private String content;
    public  Msg(String content,int type){
     this.content=content;
     this.type=type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}