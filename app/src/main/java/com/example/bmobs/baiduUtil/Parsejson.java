package com.example.bmobs.baiduUtil;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Parsejson {
    public  void Parsejsona(String jsonData, TextView a_1,TextView b_1,TextView text) {
        try {
            String name = null;
            String score = null;
            String result = null;
            Log.d("MainActivity", "我是测试地1 ");
            JSONObject jsonObjectALL = new JSONObject(jsonData);

            JSONArray jsonArray = jsonObjectALL.getJSONArray("results");


            Log.d("MainActivity", "我是测试地 2");
            for (int i = 0; i < 1; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                name = jsonObject.getString("name");
                score = jsonObject.getString("score");
                if (result == null) {
                    DecimalFormat format = new DecimalFormat("0.00");

                    String scorecore = format.format(new BigDecimal(score));
                    String kexindu = "可信度：" + scorecore;

                    a_1.setText(kexindu);
                    b_1.setText(name);
                    break;
                }
                Log.d("MainActivity", "我test ");
                a_1.setText("您的图片不符合要求或您未得病");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
