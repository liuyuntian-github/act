package com.example.act.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AuthUtil {

        public static final String APPID = "wxe34fa7d01f176688";
        public static final String APPSECRET = "572294a62ef190862815fef36380a029";

        public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
            JSONObject jsonObject = null;
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 把返回的结果转换为JSON对象
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSON.parseObject(result);
            }
            return jsonObject;
        }
    }

