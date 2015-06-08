package com.twopillar.jiba.api;

import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/***************************************
 * 
 * @author GuoJian'an
 * @date 2015-06-08
 * @time 下午5:29:50 类说明:网络请求接口回调
 * 
 **************************************/
public abstract class HttpCallBack implements Listener<JSONObject>,
		ErrorListener {


	@Override
	public void onErrorResponse(VolleyError arg0) {
	    onFailure(arg0);
	}

	@Override
	public void onResponse(JSONObject response) {
	    onSuccess(response);
	}
	
	/**
     * 
     * @param response
     *            请求成功
     */
    public abstract void onSuccess(JSONObject response);

    /**
     * 
     * @param arg0
     *            请求失败
     */
    public abstract void onFailure(VolleyError arg0);

    /**
     * 网络请求开始
     * 
     */

}
