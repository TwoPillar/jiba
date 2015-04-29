package com.twopillar.jiba.model;

import org.litepal.crud.DataSupport;


/**
 * GuoJianAn
 * 2015-4-29
 * 类说明：计划动作
 */
public class PlanAction extends DataSupport {

	private int id;
	
	private int planId;//计划Id
	
	private int actionId;//动作Id
	
	private int planDaysId;//训练第几天Id
	
	private int reps;//次数
	
	private int sets;//组数
	
	private int sequence;//顺序

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getPlanDaysId() {
		return planDaysId;
	}

	public void setPlanDaysId(int planDaysId) {
		this.planDaysId = planDaysId;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
