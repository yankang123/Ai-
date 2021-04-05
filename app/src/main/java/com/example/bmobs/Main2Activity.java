package com.example.bmobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bmobs.Gn.DaiBanActivity;
import com.example.bmobs.Gn.ShibieActivity;
import com.example.bmobs.Gn.YiXueActivity;
import com.example.bmobs.TongXun.StartChatActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView glide= (ImageView)findViewById(R.id.glide_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CardView card1=(CardView)findViewById(R.id.shibie1);
        CardView card2=(CardView)findViewById(R.id.yanghu);
        CardView card3=(CardView)findViewById(R.id.yixue);
        CardView card4=(CardView)findViewById(R.id.shoucang);
        CardView card5=(CardView)findViewById(R.id.richeng);
        CardView card6=(CardView)findViewById(R.id.daiban);
        card1.setOnClickListener(Main2Activity.this);
        card2.setOnClickListener(Main2Activity.this);
        card3.setOnClickListener(Main2Activity.this); card6.setOnClickListener(Main2Activity.this);
        card4.setOnClickListener(Main2Activity.this);
        card5.setOnClickListener(Main2Activity.this);
 Glide.with(this)
.load(R.drawable.yishigulei)                .into(glide);
    }

public void onClick(View view){
        switch (view.getId()){
            case R.id.shibie1:
                startActivity(new Intent(Main2Activity.this, ShibieActivity.class));
                break;
            case R.id.yanghu:
                startActivity(new Intent(Main2Activity.this, ShibieActivity.class));
                break;
            case  R.id.yixue:
                startActivity(new Intent(Main2Activity.this, YiXueActivity.class));
                break;
            case R.id.shoucang:
                startActivity(new Intent(Main2Activity.this, ShibieActivity.class));
                break;
            case  R.id.richeng:
                startActivity(new Intent(Main2Activity.this, StartChatActivity.class));
                break;
            case  R.id.daiban:
                startActivity(new Intent(Main2Activity.this, DaiBanActivity.class));
                break;
        }
}

}
