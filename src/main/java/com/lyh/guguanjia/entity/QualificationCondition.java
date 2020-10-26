package com.lyh.guguanjia.entity;

public class QualificationCondition {

	/**
	 * 1、我是产废方            2、我是运输方            3、我是处置方
	 */
	private String type;
	/**
	 * 0未审核            1通过审核            2审核失败
	 */
	private String check;

	private String startTime;
	private String endTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
