package com.example.bmobs.Gn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bmobs.R;

public class DaiBanActivity extends AppCompatActivity implements View.OnClickListener{
Button bt_sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dai_ban);
        bt_sc=(Button)findViewById(R.id.bt_sc);
        bt_sc.setOnClickListener(DaiBanActivity.this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.bt_sc:


        }
    }
}
