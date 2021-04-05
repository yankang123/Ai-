package com.example.bmobs.Gn;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmobs.MainActivity;
import com.example.bmobs.R;
import com.example.bmobs.baiduUtil.Base64Util;
import com.example.bmobs.baiduUtil.Getbyte;
import com.example.bmobs.baiduUtil.Parsejson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

import static android.media.tv.TvContract.Programs.Genres.encode;
import static com.example.bmobs.baiduUtil.EasydlImageClassify.easydlImageClassify;

public class ShibieActivity extends AppCompatActivity implements View.OnClickListener {
public static final int CHOOSE_PHOTO =2;
    ImageView img1;
    String imagePath;
    public static String image;
    String result;
TextView a_1;
TextView b_1;
TextView text_1;
    Getbyte a=new Getbyte();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Parsejson parsejson =new Parsejson();
                    parsejson.Parsejsona(result,a_1,b_1,text_1);
                    break;
                    default:
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Log.d("ShibieActivity.this", "我d ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shibie);
        a_1=(TextView)findViewById(R.id.response_texta);
        b_1=(TextView)findViewById(R.id.response_textb);
        text_1=(TextView)findViewById(R.id.response_text);
        Button bt1=(Button)findViewById(R.id.shibie);
        Button bt2=(Button)findViewById(R.id.xiangce);
        bt1.setOnClickListener(ShibieActivity.this);
        bt2.setOnClickListener(ShibieActivity.this);


        img1=(ImageView)findViewById(R.id.image_shibie);




        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_CONTACTS);
        }
            if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                    permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                    permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.RECORD_AUDIO);
            }
            if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                    permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CALL_PHONE);
            }
        if (ContextCompat.checkSelfPermission(ShibieActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.
                    size()]);
            ActivityCompat.requestPermissions(ShibieActivity.this, permissions, 1);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }

                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }





    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.xiangce:
                openAlbum();
            case R.id.shibie:
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            result=easydlImageClassify();//网络请求，延时操作开启线程
            Message message =new Message();
            message.what=1;
            handler.sendMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}).start();

        }}





    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }



@Override
protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
// 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
// 4.4 及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
// 4.4 以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
}

private void handleImageOnKitKat(Intent data){
     imagePath = null;
    Uri uri = data.getData();
    if (DocumentsContract.isDocumentUri(this, uri)) {
// 如果是 document 类型的 Uri ，则通过 document id 处理
        String docId = DocumentsContract.getDocumentId(uri);
        if("com.android.providers.media.documents".equals(uri.getAuthority())) {
            String id = docId.split(":")[1]; // 解析出数字格式的 id
            String selection = MediaStore.Images.Media._ID + "=" + id;
            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
        } else if ("com.android.providers.downloads.documents".equals(uri.
                getAuthority())) {
            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
        }
    } else if ("content".equalsIgnoreCase(uri.getScheme())) {
// 如果是 content 类型的 Uri ，则使用普通方式处理
        imagePath = getImagePath(uri, null);
    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
// 如果是 file 类型的 Uri ，直接获取图片路径即可
        imagePath = uri.getPath();
    }
    displayImage(imagePath); // 根据图片路径显示图片    }

}
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {

            String picPath = imagePath;
            Log.d("wo","我"+ picPath);
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
              img1.setImageBitmap(bitmap);
              byte[] erjinzhi=a.getBitmapByte(bitmap);

image=Base64Util.encode(erjinzhi);

        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
// 通过 Uri 和 和 selection 来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.
                        Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
