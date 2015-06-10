package com.twopillar.jiba.model;

import org.litepal.crud.DataSupport;


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
	
	private String imgPath;//图片路径
	
	private String description;//描述
	
	private String videoUrl;//视频路径

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

    public String getVideoUrl()
    {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

}
