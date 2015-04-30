package com.twopillar.jiba.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import com.twopillar.jiba.model.Action;

public class JBAppliction extends LitePalApplication{

	@Override
	public void onCreate() {
		Action actionDB = DataSupport.findFirst(Action.class);//如果动作表没数据则加载资源文件到数据库
		if(actionDB == null) {
			String[] folderList = {"shoulder","thorax","triceps","bicipital","back","abdominal","leg"};
			String[] actionNameList = null;
			List<Action> actionList = new ArrayList<Action>();
			for(String folder : folderList) {
				try {  
					actionNameList = getAssets().list(folder);
				    for(int i=0;i<actionNameList.length;++i)  
					{ 
				    	Action action = new Action();
				    	String imgPath = folder+"/"+actionNameList[i];//动作的路径 
				    	action.setBigType(folder);//类型
				    	action.setImgPath(imgPath);//图片路径
				    	action.setActionName(actionNameList[i]);//动作名
				    	actionList.add(action);
					}
				} catch (IOException e1) {  
				    e1.printStackTrace();  
				}  	
			}
			Action.saveAll(actionList);
		}	
	}
}
