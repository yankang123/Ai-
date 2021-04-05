package com.example.bmobs.Gn;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.bmobs.Adapter.YIshiAdapter;
import com.example.bmobs.R;
import com.example.bmobs.baiduUtil.toast;
import com.example.bmobs.shujulei.Wenzhang;
import com.example.bmobs.shujulei.Wenzhangad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class YiXueActivity extends AppCompatActivity {
    String title1;
    String url1;
    int imageid1;
    String title2;
    String url2;
    int imageid2;
    String title3;
    String url3;
    int imageid3;
    String title4;
    String url4;
    int imageid4;
    String title5;
    String url5;
    int imageid5;
    TextView txt;
    Wenzhang a;
    Wenzhang b;
    Wenzhang c;
    Wenzhang d;
    Wenzhang f;
    int shuju1;
    int shuju2;
    int shuju3;
    int shuju4;
    int shuju5;

    YIshiAdapter adapter;
    List<Wenzhangad> wenzhanglist=new ArrayList<>();

    public static final int UPDATE_TEXT = 1;


    private Wenzhang[] wzarray;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:

                    Wenzhangad ad1;
                    Wenzhangad ad2;
                    Wenzhangad ad3;
                    Wenzhangad ad4;
                    Wenzhangad ad5;
                    if(imageid1==0){
                        ad1=new Wenzhangad(title1,url1);
                    }else{
                        ad1=new Wenzhangad(title1,imageid1);
                    }
                    if(imageid2==0){
                        ad2=new Wenzhangad(b.getTitle(),b.getDizhi());
                    }else{
                        ad2=new Wenzhangad(b.getTitle(),b.getImageid());
                    }
                    if(imageid3==0){
                        ad3=new Wenzhangad(c.getTitle(),c.getDizhi());
                    }else{
                        ad3=new Wenzhangad(c.getTitle(),c.getImageid());
                    }
                    if(imageid4==0){
                        ad4=new Wenzhangad(d.getTitle(),d.getDizhi());
                    }else{
                        ad4=new Wenzhangad(d.getTitle(),d.getImageid());
                    }
                    if(imageid5==0){
                        ad5=new Wenzhangad(f.getTitle(),f.getDizhi());
                    }else{
                        ad5=new Wenzhangad(f.getTitle(),f.getImageid());
                    }

                    Wenzhangad[] articles={ad1,ad2,ad3,ad4,ad5};


                    for (int i = 0; i < 5; i++) {


                        wenzhanglist.add(articles[i]);
                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
                    GridLayoutManager layoutManager = new GridLayoutManager(YiXueActivity.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new YIshiAdapter(wenzhanglist);
                    recyclerView.setAdapter(adapter);

                    break;
                default:
                    break;
            }
        }
    };
// 在这里可以进行 UI 操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_xue);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("医学小知识");
        setSupportActionBar(toolbar);

        Log.d("wo", "done: 我hh");

        BmobQuery<Wenzhang> query = new BmobQuery<>();
        Log.d("wo", "done: 我hhss");
        query.setLimit(14).setSkip(0).order("-createdAt")

                .findObjects(new FindListener<Wenzhang>() {
                    @Override
                    public void done(List<Wenzhang> object, BmobException e) {
                        if (e == null) {
                            Log.d("wo", "done: 我hh");
                            Random random= new Random();
while(true) {
    shuju1 = random.nextInt(14);
    shuju2 = random.nextInt(14);
    shuju3 = random.nextInt(14);
    shuju4 = random.nextInt(14);
    shuju5 = random.nextInt(14);
    Log.d("wo", "done: 我hh");
    if(shuju1!=shuju2&&shuju1!=shuju3&&shuju1!=shuju4&&shuju1!=shuju5&&shuju2!=shuju3&&shuju2!=shuju5&&shuju2!=shuju4&&shuju3!=shuju4&&shuju3!=shuju5
    &&shuju4!=shuju5)
        break;
}

                       a = object.get(shuju1);
                        b = object.get(shuju2);
                           c = object.get(shuju3);
                        d = object.get(shuju4);
                         f = object.get(shuju5);
                            title1=a.getTitle();
                            imageid1=a.getImageid();
                                url1=a.getDizhi();
                            imageid2=b.getImageid();
                            imageid3=c.getImageid();
                            imageid4=d.getImageid();
                            imageid5=f.getImageid();
                            Log.d("我", "done:我 "+title1+imageid1+url1);
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message); // 将 将 Message 对象发送出去

                        } else {
                            // ...
                        }
                    }
                });

    }



}
