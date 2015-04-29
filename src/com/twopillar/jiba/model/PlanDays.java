package com.twopillar.jiba.model;

import org.litepal.crud.DataSupport;


/**
 * GuoJianAn
 * 2015-4-29
 * 类说明：训练一周次数
 */
public class PlanDays extends DataSupport {
	
	private int id;
	
	private Plan plan;//训练计划
	
	private int day;//第几天
	
	private String type;//类型
	
	private String description;//描述
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
