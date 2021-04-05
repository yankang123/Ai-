package com.example.bmobs.TongXun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bmobs.Adapter.ChatAdapter;
import com.example.bmobs.R;
import com.example.bmobs.shujulei.Myid;

public class StartChatActivity extends AppCompatActivity implements View.OnClickListener{
TextView who_findme;
    String one;String one1;
    String two;String two1;
    String three ;String three1;
    String four;String four1;
    String five ;String five1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chat);
        Button you_1=(Button)findViewById(R.id.you_1);
        Button you_2=(Button)findViewById(R.id.you_2);
        you_1.setOnClickListener(StartChatActivity.this);
        you_2.setOnClickListener(StartChatActivity.this);
        if(Myid.myid.equals("2")){
            you_1.setText("客户");
        }
        who_findme=(TextView)findViewById(R.id.who_findme);
        Log.d("我", "我我onClick: ");
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.you_1://赵医生
                if(Myid.myid.equals("2")){
                    Intent intent2=new Intent(StartChatActivity.this,BeiliaoActivity.class);
                    Log.d("我", "我我onClick:22 ");
                    startActivity(intent2);
                    break;
                }
               //Liaottianactivity设置为专门为医生聊天的
Intent intent=new Intent(StartChatActivity.this,LiaotianActivity.class);//主动找2聊天，开启找人活动

startActivity(intent);
                break;
            case R.id.you_2:
     startActivity(new Intent(StartChatActivity.this, ChatCommunity.class));
               break;

        }
    }


}
