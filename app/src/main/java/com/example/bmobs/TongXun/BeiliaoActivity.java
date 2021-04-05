package com.example.bmobs.TongXun;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bmobs.Adapter.ChatAdapter;
import com.example.bmobs.R;
import com.example.bmobs.TongXun.TongxunUtil.ABshuju;
import com.example.bmobs.baiduUtil.toast;
import com.example.bmobs.shujulei.Liaotian;
import com.example.bmobs.shujulei.Liaotian2;
import com.example.bmobs.shujulei.Msg;
import com.example.bmobs.shujulei.Myid;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class BeiliaoActivity extends AppCompatActivity implements View.OnClickListener {
    String hangId;
    String a;
    String b;
    String zong;
    String shijian;
    String shijian2;
    int i;
    ABshuju aBshu;
    TextView textView;
    EditText editText;
    Button button;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);

                    break;
                default:
                    break;
            }
        }
    };
    private RecyclerView msgRecyclerView;
    private ChatAdapter adapter;
    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiliao);

        editText=(EditText)findViewById(R.id.fa_news);
        button=(Button)findViewById(R.id.Fa_song) ;
        button.setOnClickListener(BeiliaoActivity.this);

        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_rececyler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new ChatAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        NEWThread();

    }

    public void onClick(View view) {
    switch (view.getId()) {
        case R.id.Fa_song:
       String xiaoxi =editText.getText().toString();

            Msg msg=new Msg(xiaoxi,Msg.Type_Send);
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size()-1);
            msgRecyclerView.scrollToPosition(msgList.size()-1);
            editText.setText("");
        Liaotian2 p2 = new Liaotian2();
        //点击上传edittext里的数据
        p2.setAid("1");
        p2.setBid(Myid.myid);//如果我是医生
        p2.setB(xiaoxi);


        p2.save(new SaveListener<String>() {

            @Override

            public void done(String objectId, BmobException e) {
                if (e == null) {

                    toast.toast(BeiliaoActivity.this, "添加数据成功，返回objectId为：" + objectId);
                } else {
                    toast.toast(BeiliaoActivity.this, "创建数据失败：" + e.getMessage());

                }
            }

        });


            break;
    }

    }

    public void NEWThread(){
        new Thread(new Runnable() {   //点击事件上传数据之后开启线程
            @Override
            public void run() {

                while (true){
                    BmobQuery<Liaotian> categoryBmobQuery = new BmobQuery<>();//查询的是医生的表
                    categoryBmobQuery.addWhereEqualTo("Aid", "1");//医生与客户1的聊天记录
                    categoryBmobQuery.findObjects(new FindListener<Liaotian>() {
                        @Override
                        public void done(List<Liaotian> object, BmobException e) {
                            if (e == null) {
                                if (object.size() == 0) {
                                    Log.d("lfl", "BMOB表为空 ");
                                } else {//bmob表不空
                                    List<ABshuju> aBshujus = DataSupport.findAll(ABshuju.class);
                                    if (aBshujus.size() == 0) {//数据库为空,存储所有
                                        for (Liaotian liaotian2 : object) {
                                            ABshuju aBshu = new ABshuju();
                                            aBshu.setAid("1");
                                            aBshu.setBid(Myid.myid);
                                            aBshu.setA(liaotian2.getA());
                                            aBshu.setTime(liaotian2.getCreatedAt());
                                            aBshu.save();
                                            Msg msg=new Msg(liaotian2.getB(),Msg.Type_Receive);
                                            msgList.add(msg);
                                            Message message=new Message();
                                            message.what=1;
                                            handler.sendMessage(message);
                                        }
                                        toast.toast(BeiliaoActivity.this, object.get(0).getB());
                                    } else {
                                        //数据库不为空，存储未存储的
                                        List<ABshuju> aBshuju2s = DataSupport.findAll(ABshuju.class);
                                        toast.toast(BeiliaoActivity.this, object.get(0).getA() + aBshuju2s.get(0).getA());
                                        if (object.size() > aBshuju2s.size()) {
                                            int n = object.size() - aBshuju2s.size();
                                            ABshuju abshu2 = new ABshuju();
                                            abshu2.setAid("1");
                                            abshu2.setBid(Myid.myid);
                                            abshu2.setA(object.get(object.size()-n+1).getA());
                                            abshu2.setTime(object.get(object.size()-n+1).getCreatedAt());
                                            abshu2.save();
                                            Msg msg=new Msg(object.get(object.size()-n+1).getA(),Msg.Type_Receive);
                                            msgList.add(msg);
                                            Message message=new Message();
                                            message.what=1;
                                            handler.sendMessage(message);

                                        }

                                    }
                                }

                            } else {
                                Log.e("BMOB", e.toString());
                                toast.toast(BeiliaoActivity.this, "查询失败2"+e.getErrorCode());
                            }
                        }
                    });
                    try {
                        Thread.currentThread().sleep(3500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();
    }
}
