package com.lyh.guguanjia.entity;

public class ExamineCondition {

	/**
	 * 1、我是产废方            2、我是处置方
	 */
	private String type;
	private String username;
	private String officeid;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
}
