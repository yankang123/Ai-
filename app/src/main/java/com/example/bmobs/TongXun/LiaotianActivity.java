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

public class LiaotianActivity extends AppCompatActivity implements View.OnClickListener{


    TextView  ltxx;
    EditText bainji;
String TAG="默认";
    Button button;
    String a;
    String b;

    int i;
    String zong=null;
    String xiaoxi;
 Handler handler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         switch (msg.what){
             case 1:
                 Log.d(TAG, "你他娘的意大利炮呢handleMessage: ");
                 for(int j=0;j<msgList.size();j++) {
                        String bb=msgList.get(0).getContent();
                     Log.d(TAG, "只有吗我懂"+bb);
                 }
                 adapter.notifyItemInserted(msgList.size()-1);

                 Log.d(TAG, "你他娘的意大利炮呢222handleMessage: ");
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
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaotian);

        bainji=(EditText)findViewById(R.id.bianji);
        button=(Button)findViewById(R.id.btn_lt);
        button.setOnClickListener(LiaotianActivity.this);


        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_rececyler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new ChatAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        NewThread();


    }



    @Override//点击事件
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_lt:

xiaoxi=bainji.getText().toString();
if(!"".equals(xiaoxi)){
    Msg msg=new Msg(xiaoxi,Msg.Type_Send);
    msgList.add(msg);
    adapter.notifyItemInserted(msgList.size()-1);
    msgRecyclerView.scrollToPosition(msgList.size()-1);
    bainji.setText("");
    shngcuan();
}




break;
        }

    }
    public void shngcuan(){
        Liaotian p2 = new Liaotian();
        p2.setA(xiaoxi);//点击上传edittext里的数据
        p2.setAid(Myid.myid);

        p2.save(new SaveListener<String>() {

            @Override

            public void done(String objectId, BmobException e) {
                if (e == null) {

                    toast.toast(LiaotianActivity.this, "添加数据成功，返回objectId为：" + objectId);
                } else {
                    toast.toast(LiaotianActivity.this, "创建数据失败：" + e.getMessage());

                }
            }

        });
    }
    public void NewThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    BmobQuery<Liaotian2> categoryBmobQuery = new BmobQuery<>();//查询的是医生的表
                    categoryBmobQuery.addWhereEqualTo("Aid", "1");//医生与客户1的聊天记录
                    categoryBmobQuery.findObjects(new FindListener<Liaotian2>() {
                        @Override
                        public void done(List<Liaotian2> object, BmobException e) {
                            if (e == null) {
                                if (object.size() == 0) {
                                    Log.d(TAG, "BMOB表为空 ");
                                } else {//bmob表不空
                                    List<ABshuju> aBshujus = DataSupport.findAll(ABshuju.class);
                                    toast.toast(LiaotianActivity.this, "数据库表大小done: "+aBshujus.size());
                                    if (aBshujus.size() == 0) {
                                        //数据库为空,存储所有
                                        ABshuju aBshu = new ABshuju();
                                        for (Liaotian2 liaotian2 : object) {

                                            aBshu.setAid(Myid.myid);
                                            aBshu.setBid("2");
                                            aBshu.setB(liaotian2.getB()+"一起家的");
                                            aBshu.setTime(liaotian2.getCreatedAt());
                                            aBshu.save();
                                            Msg msg=new Msg(liaotian2.getB(),Msg.Type_Receive);
                                            msgList.add(msg);
                                            Message message=new Message();
                                            message.what=1;
                                            handler.sendMessage(message);
                                        }

                                        toast.toast(LiaotianActivity.this, "aa"+object.get(0).getB());
                                    } else {
                                        //数据库不为空，存储未存储的
                                        List<ABshuju> aBshuju2s = DataSupport.findAll(ABshuju.class);

                                        toast.toast(LiaotianActivity.this, "bb"+object.get(0).getB() + aBshuju2s.get(0).getB());
                                        if (object.size() > aBshuju2s.size()) {
                                            int n = object.size() - aBshuju2s.size();

                                                ABshuju abshu2 = new ABshuju();
                                                abshu2.setAid(Myid.myid);
                                                abshu2.setBid("2");
                                                abshu2.setB(object.get(object.size()-n+1).getB()+"单一甲等");
                                                abshu2.setTime(object.get(object.size()-n+1).getCreatedAt());
                                                abshu2.save();
                                                Msg msg=new Msg(object.get(object.size()-n+1).getB(),Msg.Type_Receive);
                                                msgList.add(msg);
                                                Message message=new Message();
                                                message.what=1;
                                                handler.sendMessage(message);


                                        }

                                    }
                                }

                            } else {
                                Log.e("BMOB", e.toString());
                                toast.toast(LiaotianActivity.this, "查询失败2"+e.getErrorCode());
                            }
                        }
                    });

                    List<ABshuju> all = DataSupport.findAll(ABshuju.class);
                    //此处打印数据库的数据用来检测错误，错误一，连续存储了两遍
                    for(ABshuju danyi:all){
                        String log =danyi.getB();
                        Log.d(TAG, "傻逼才不会做   "+log);
                    }

                    try {
                        Thread.currentThread().sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

}
