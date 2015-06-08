package com.twopillar.jiba.api;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
/***************************************
 * 
 * @author lwli
 * @date 2014-9-18 
 * @time 下午5:30:05
 * 类说明:用于GET请求
 * 
 **************************************/
public class RequestJsonObject extends JsonObjectRequest {
	private Map<String, String>params;

	/**
	 * @param url
	 * @param jsonRequest
	 * @param listener
	 * @param errorListener
	 */
	public RequestJsonObject(String url, JSONObject jsonRequest,
			HttpCallBack callBack) {
		super(url, jsonRequest, callBack, callBack);
		setRetryPolicy(new DefaultRetryPolicy(6 * 1000, 1, 1.0f));
	}

	/**
	 * @param method
	 * @param url
	 * @param jsonRequest
	 * @param listener
	 * @param errorListener
	 */
	public RequestJsonObject(int method, String url, JSONObject jsonRequest,
			HttpCallBack callBack) {
		super(method, url, jsonRequest, callBack, callBack);
		setRetryPolicy(new DefaultRetryPolicy(6 * 1000, 1, 1.0f));
	}
	/**
	 * @param method
	 * @param url
	 * @param jsonRequest
	 * @param listener
	 * @param errorListener
	 */
	public RequestJsonObject(int method, String url, Map<String, String>params,int what,HttpCallBack callBack) {
		super(method, url, new JSONObject(params), callBack, callBack);
		this.params = params;
		setRetryPolicy(new DefaultRetryPolicy(6 * 1000, 1, 1.0f));
	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params;
	}


}
