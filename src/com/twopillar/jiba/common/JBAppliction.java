package com.twopillar.jiba.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import com.twopillar.jiba.model.Action;
import com.twopillar.jiba.model.Plan;
import com.twopillar.jiba.model.PlanAction;
import com.twopillar.jiba.model.PlanDays;
import com.twopillar.jiba.util.JsonUtil;

public class JBAppliction extends LitePalApplication{

	@Override
	public void onCreate() {
/*		saveAction();
		saveRecommendPlan();*/
	}
	
	/**
	 * 保存动作图片
	 */
	private void saveAction() {
		Action actionDB = DataSupport.findFirst(Action.class);//如果动作表没数据则加载资源文件到数据库
		if(actionDB == null) {
			try {
				InputStream is = getAssets().open("actionChineseName.json");//读取动作中文名json文件
				JSONObject actionChineseNameJson = JsonUtil.inputStream2String(is);
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
	
	/**
	 * 保存推荐计划
	 */
	private void saveRecommendPlan() {
		List<Plan> plans = Plan.where("recommend = ?","1").find(Plan.class);
		if(plans.isEmpty()) {
			plans = new ArrayList<Plan>();
			String[] weeks = {"Mon","Tues","Wed","Thur","Fri","Sat","Sun"};
			InputStream is;
			try {
				is = getAssets().open("recommendPlan.json");//获取推荐计划
				JSONObject recommendPlan = JsonUtil.inputStream2String(is);//转成json格式
				JSONArray planJSONArray = recommendPlan.getJSONArray("Plans");
				for(int i=0;i<planJSONArray.length();i++) {
					Plan plan = new Plan();
					JSONObject item = planJSONArray.getJSONObject(i);
					String planName = item.getString("Name");//计划名
					int sets = item.getInt("Sets");//组数
					int reps = item.getInt("Reps");//次数
					
					for(int x=0; x<weeks.length; x++) {
						PlanDays planDays = new PlanDays();
						planDays.setDay(x);
						if(!item.isNull(weeks[x])) {
							planDays.setType("ACTION");
							JSONObject weekJsonObject = item.getJSONObject(weeks[x]);
							String description = weekJsonObject.getString("Description");//每天锻炼部位描述
							planDays.setDescription(description);
							
							String[] actions = weekJsonObject.getString("Action").split(",");
							for(int y=0; y<actions.length; y++) {
								int actionId = Action.where("actionName = ?", actions[y]).find(Action.class).get(0).getId();//找出动作Id
								PlanAction planAction = new PlanAction();
								planAction.setActionId(actionId);
								planAction.setSequence(y);
								planAction.setSets(sets);
								planAction.setReps(reps);
								planAction.save();
								planDays.getPlanActions().add(planAction);
							}
						}else {
							planDays.setType("REST");
						}
						planDays.save();
						plan.getPlanDaysList().add(planDays);
					}
					plan.setPlanName(planName);
					plan.setRecommend("1");//计划类型为推荐
					plans.add(plan);
				}
				Plan.saveAll(plans);
				is.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
