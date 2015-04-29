package com.twopillar.jiba.model;

import java.util.Date;

import org.litepal.crud.DataSupport;



/**
 * GuoJianAn
 * 2015-4-29
 * 类说明：签到
 */
public class SignIn extends DataSupport {

	private int id;
	
	private int planId;//计划Id
	
	private Date signdate;//记录时间
	
	private String status;//类型
	
	private String durationTime;//运动时间

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public Date getSigndate() {
		return signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
