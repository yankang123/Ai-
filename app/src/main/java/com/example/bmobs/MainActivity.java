package com.example.bmobs;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bmobs.shujulei.Myid;
import com.example.bmobs.shujulei.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText text1;
    EditText text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "6cd7dae4834e5d89f5ab255f37b315c3");

        Button btn1 = (Button) findViewById(R.id.zhuce);
        Button btn2 = (Button) findViewById(R.id.denglu);
        text1 = (EditText) findViewById(R.id.zhanghao);
        text2 = (EditText) findViewById(R.id.password);
        btn1.setOnClickListener(MainActivity.this);
        btn2.setOnClickListener(MainActivity.this);
    }

    public void onClick(final View view) {
        final User user = new User();
        final String a=   text1.getText().toString();
       final  String b=text2.getText().toString();
        switch (view.getId()) {
            case R.id.zhuce:
                user.setAll("总");
                user.setUsername(a);
                user.setPassword(b);
                Myid.myid=a;
                Log.d("傻", "傻onClick: "+a+Myid.myid);
                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                           Toast.makeText(MainActivity.this ,"注册成功"+user.getUsername(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));



                        } else {
                            Toast.makeText(MainActivity.this ,"注册失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });



break;
            case R.id.denglu:

                String txt = text1.getText().toString();
                String txt1 = text2.getText().toString();
                Log.d("傻", "傻onClick: "+a+Myid.myid);
                Myid.myid=txt;
                //此处替换为你的用户名
                user.setUsername(txt);
                //此处替换为你的密码
                user.setPassword(txt1);
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User bmobUser, BmobException e) {
                        if (e == null) {
                            User user = BmobUser.getCurrentUser(User.class);
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            Snackbar.make(view, "登录成功：" + user.getUsername(), Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(view, "登录失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }

}