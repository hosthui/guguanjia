package com.lyh.guguanjia.mapper;

import org.springframework.util.StringUtils;

public class SysOfficeSqlProvider {


	public String selectSqlPage(String conName){
		StringBuffer buffer = new StringBuffer("SELECT sso.*,ssa.`name` " +
				" area_name FROM sys_office sso,sys_area ssa " +
				" WHERE sso.del_flag='0' " +
				" and sso.area_id=ssa.id ");

		if ( !StringUtils.isEmpty(conName) ){
			buffer.append("and sso.`name` LIKE CONCAT('%',#{conName},'%')");
		}

		return buffer.toString();
	}
}
