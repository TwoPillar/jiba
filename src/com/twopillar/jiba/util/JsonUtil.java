package com.twopillar.jiba.util;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	
	public static JSONObject inputStream2String(InputStream in) throws IOException   { 
        StringBuffer out = new StringBuffer(); 
        byte[] b = new byte[4096]; 
        for(int n; (n = in.read(b)) != -1;)   { 
        	out.append(new String(b, 0, n)); 
        } 
        JSONObject json = new JSONObject();
		try {
			json = new JSONObject(out.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
	} 
}
