package com.example.bmobs.TongXun;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bmobs.Adapter.ShequAdapter;
import com.example.bmobs.MainActivity;
import com.example.bmobs.R;
import com.example.bmobs.baiduUtil.toast;
import com.example.bmobs.shujulei.Myid;
import com.example.bmobs.shujulei.SheChat;
import com.example.bmobs.shujulei.ShequItem;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class ChatCommunity extends AppCompatActivity {
private ShequAdapter adapter;
private List<ShequItem> itemList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_community);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.Shequ_Recycler_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new ShequAdapter(itemList);
        recyclerView.setAdapter(adapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).
                        show();
                showAlertDialog();
                break;

            default:
        }
        return true;
    }
    private void showAlertDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialog, null));
        dialog.show();


        dialog.getWindow().setContentView(R.layout.alert_dialog);
        Button btnPositive = (Button) dialog.findViewById(R.id.bt_ok);
        Button btnNegative = (Button) dialog.findViewById(R.id.bt_cancel);
        final EditText etContent = (EditText) dialog.findViewById(R.id.input);
        btnPositive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String str = etContent.getText().toString();
                if (isNullEmptyBlank(str)) {
                    etContent.setError("输入内如不能为空");
                } else {
                    dialog.dismiss();
                     String a=etContent.getText().toString();


                    SheChat p2 = new SheChat();
                    p2.setName(Myid.myid);
                    p2.setContent(a);
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if(e==null){
                                toast.toast(ChatCommunity.this,"添加数据成功，返回objectId为：" + objectId);
                                ReinitList();
                                adapter.notifyDataSetChanged();
                            }else{
                                toast.toast(ChatCommunity.this,"创建数据失败：" + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }
    public void ReinitList(){
        BmobQuery<SheChat> query = new BmobQuery<>();
        query.setLimit(8).setSkip(0).order("-createdAt")
                .findObjects(new FindListener<SheChat>() {
                    @Override
                    public void done(List<SheChat> object, BmobException e) {
                        if (e == null) {
                            // ...
                            itemList.clear();
                            itemList.add(new ShequItem(Myid.myid,object.get(0).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(1).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(2).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(3).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(4).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(5).getContent()));
                            itemList.add(new ShequItem(Myid.myid,object.get(6).getContent()));

                        } else {
                            // ...
                        }
                    }
                });
    }

}
