
package com.twopillar.jiba.api;

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
    
    public void getActionByType(String type, HttpCallBack callBack) {
        String method = "action/getActionByType";
        RequestJsonObject requestJsonObject = new RequestJsonObject(
                Request.Method.GET, Constants.url + "/" + method + "?type=" + type, null,
                callBack);
        add(requestJsonObject);
    }
}
