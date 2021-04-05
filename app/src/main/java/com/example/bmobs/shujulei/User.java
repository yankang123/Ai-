package com.example.bmobs.shujulei;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    String all;
}
