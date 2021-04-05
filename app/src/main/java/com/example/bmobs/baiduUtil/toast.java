package com.example.bmobs.baiduUtil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class toast {
   public static void toast(Context context , String message){
      Toast.makeText(context, message, Toast.LENGTH_LONG).show();

   }
}
