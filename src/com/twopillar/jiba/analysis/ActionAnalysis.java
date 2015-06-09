package com.twopillar.jiba.analysis;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twopillar.jiba.common.Constants;
import com.twopillar.jiba.model.Action;

/**
 * @author GuoJian'an
 * @date 2015-6-9
 *
 */
public class ActionAnalysis
{   
    private List<Action> datas;
   
    public ActionAnalysis(JSONObject response) {
        datas = new ArrayList<Action>();
        JSONArray jsonArray;
        try
        {
            jsonArray = new JSONArray(response.getString("result").toString());
            for(int i=0;i<jsonArray.length();i++){              
                JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                Action info=new Action();
                info.setId(jsonObject.getInt("id"));
                info.setImgPath(Constants.url+jsonObject.getString("url"));
                info.setBigType(jsonObject.getString("type"));
                info.setDescription(jsonObject.getString("description"));
                info.setActionName(jsonObject.getString("actionName"));
                datas.add(info);
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Action> getResult() {
        return datas;
    }
}
