package com.example.bmobs.baiduUtil;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import static com.example.bmobs.Gn.ShibieActivity.image;
import static com.example.bmobs.baiduUtil.AuthService.getAuth;

public class EasydlImageClassify {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String easydlImageClassify() {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/yk233";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("top_num", "5");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken =getAuth();
            Log.d("skd", "参数easydlImageClassify: "+param);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            Log.d("我", "我easydlImageClassify: "+result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        EasydlImageClassify.easydlImageClassify();
    }
}