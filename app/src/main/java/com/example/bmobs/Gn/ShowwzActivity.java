package com.example.bmobs.Gn;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bmobs.R;
import com.example.bmobs.baiduUtil.toast;
import com.example.bmobs.shujulei.Wenzhang;
import com.example.bmobs.shujulei.Wenzhangad;

import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class ShowwzActivity extends AppCompatActivity {
    TextView txt;
    TextView title;
    String wz;
    String wz2;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
title.setText(wz);
txt.setText(wz2);

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwz);
        txt = (TextView) findViewById(R.id.wz_1);
        title = (TextView) findViewById(R.id.shabi);
        Intent intent = getIntent();
         wz = intent.getStringExtra("wzname");
        BmobQuery<Wenzhang> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("title", wz);
        categoryBmobQuery.findObjects(new FindListener<Wenzhang>() {
            @Override
            public void done(List<Wenzhang> object, BmobException e) {
                if (e == null) {
                    Wenzhang artivle=object.get(0);
                    wz2=artivle.getNeirong();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message); // 将 将 Message 对象发送出去
                } else {
                    Log.e("BMOB", e.toString());
                    toast.toast(ShowwzActivity.this,"网络传输异常");
                }
            }
        });




    }
}