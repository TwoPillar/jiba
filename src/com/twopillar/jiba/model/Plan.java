package com.twopillar.jiba.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

/**
 * GuoJianAn
 * 2015-4-29
 * 类说明:训练计划
 */
public class Plan extends DataSupport {
	
	private int id;
	
	private String recommend;//推荐计划类型
	
	private String planName;//计划名
	
	private int currentDay;//当前第几个训练日
	
	private int smallRestTime;//小组间休息时间
	
	private int bigRestTime;//大组间休息时间
	
	private boolean start;//启动
	
	private Date startDate;//开始时间
	
	private List<PlanDays> PlanDaysList = new ArrayList<PlanDays>();//训练一周次数

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public int getSmallRestTime() {
		return smallRestTime;
	}

	public void setSmallRestTime(int smallRestTime) {
		this.smallRestTime = smallRestTime;
	}

	public int getBigRestTime() {
		return bigRestTime;
	}

	public void setBigRestTime(int bigRestTime) {
		this.bigRestTime = bigRestTime;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public List<PlanDays> getPlanDaysList() {
		return PlanDaysList;
	}

	public void setPlanDaysList(List<PlanDays> planDaysList) {
		PlanDaysList = planDaysList;
	}
}
