package com.twopillar.jiba.model;

import org.litepal.crud.DataSupport;

import android.R.integer;
import android.graphics.Bitmap;


/**
 * GuoJianAn
 * 2015-4-29
 * 类说明：动作名
 */
public class Action extends DataSupport{
	
	private int id;
	
	private String bigType;//一级类型
	
	private String smallType;//二级类型
	
	private String actionName;//动作名称
	
	private String description;//描述
	
	private int drawableId;

	public String getBigType() {
		return bigType;
	}

	public void setBigType(String bigType) {
		this.bigType = bigType;
	}

	public String getSmallType() {
		return smallType;
	}

	public void setSmallType(String smallType) {
		this.smallType = smallType;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

    public int getDrawableId()
    {
        return drawableId;
    }

    public void setDrawableId(int drawableId)
    {
        this.drawableId = drawableId;
    }

}
