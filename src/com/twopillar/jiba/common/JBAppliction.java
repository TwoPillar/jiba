package com.twopillar.jiba.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import com.twopillar.jiba.model.Action;

public class JBAppliction extends LitePalApplication{

	@Override
	public void onCreate() {
		Action actionDB = DataSupport.findFirst(Action.class);//如果动作表没数据则加载资源文件到数据库
		if(actionDB == null) {
			try {
				InputStream is = getAssets().open("actionChineseName.json");//读取动作中文名json文件
				JSONObject actionChineseNameJson = inputStream2String(is);
				is.close();
				String[] folderList = {"shoulder","thorax","triceps","bicipital","back","abdominal","leg"};
				String[] actionNameList = null;
				List<Action> actionList = new ArrayList<Action>();
				for(String folder : folderList) { 
					actionNameList = getAssets().list(folder);
				    for(int i=0;i<actionNameList.length;++i)  
					{ 
				    	Action action = new Action();
				    	String imgPath = folder+"/"+actionNameList[i];//动作的路径 
				    	action.setBigType(folder);//类型
				    	action.setImgPath(imgPath);//图片路径
				    	try {
							action.setActionName(actionChineseNameJson.getString(actionNameList[i]).split("/")[0]);//动作名
							action.setDescription(actionChineseNameJson.getString(actionNameList[i]).split("/")[1]);//描述
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	actionList.add(action);
					}
				}
				Action.saveAll(actionList);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public JSONObject inputStream2String(InputStream in) throws IOException   { 
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
