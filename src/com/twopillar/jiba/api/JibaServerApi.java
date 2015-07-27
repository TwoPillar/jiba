
package com.twopillar.jiba.api;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.twopillar.jiba.common.Constants;


public class JibaServerApi {   
    private RequestQueue queue;
    public static JibaServerApi getInstance(Context context) {
        return new JibaServerApi(context);
    }
    
    private JibaServerApi(Context context) {
        queue = Volley.newRequestQueue(context);
    }
    
    public void add(Request request) {
        queue.add(request);
    }
    
    /**
     * 手机登录
     * @param phone
     * @param password
     * @param callBack
     */
    public void login(String phone, String password, HttpCallBack callBack) {
        
        String passwordtoMD5 = null;
        try {
            passwordtoMD5 = encryptMD5(password);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String method = "user/login";
        RequestJsonObject requestJsonObject = new RequestJsonObject(
                Request.Method.GET, Constants.url + "/" + method + "?phone=" + phone +"&password="+ passwordtoMD5, null,
                callBack);
        add(requestJsonObject);
    }
    
    /**
     * 注册
     * @param phone
     * @param password
     * @param callBack
     */
    public void register(String phone, String password, HttpCallBack callBack) {
        
        String passwordtoMD5 = null;
        try {
            passwordtoMD5 = encryptMD5(password);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String method = "user/register";
        RequestJsonObject requestJsonObject = new RequestJsonObject(
                Request.Method.GET, Constants.url + "/" + method + "?phone=" + phone +"&password="+ passwordtoMD5, null,
                callBack);
        add(requestJsonObject);
    }
    
    /**
     * MD5加密
     */
    public static String encryptMD5(String val) throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer(32);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] byteArray;
        try {
            byteArray = val.getBytes("gb2312");
            byte[] m = md5.digest(byteArray);
            for (int i = 0; i < m.length; i++) {
                int ch = m[i];
                sb.append(org.apache.commons.lang.StringUtils.leftPad(
                        Integer.toHexString(ch & 0xff), 2, '0'));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
