package com.lyh.guguanjia.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class SysAreaSqlProvider {

	public String selectSqlPage(Map<String,String> areacondition){

		return new SQL(){{
			SELECT("sc.id," + 
					"sc.parent_id," + 
					"sc.parent_ids," + 
					"sc.`code`," + 
					"sc.`name`," + 
					"sc.type," + 
					"sc.create_by," + 
					"sc.create_date," + 
					"sc.update_by," + 
					"sc.update_date," + 
					"sc.remarks," + 
					"sc.del_flag," + 
					"sc.icon," + 
					"sp.`name` parent_name");
			FROM("sys_area sc");
			LEFT_OUTER_JOIN("sys_area sp on sc.parent_id=sp.id");
			WHERE("sc.del_flag=0");
			if ( areacondition.containsKey("name")&& !StringUtils.isEmpty(areacondition.get("name")) ){
				WHERE("sc.`name` like CONCAT('%',#{name},'%')");
			}
			if ( areacondition.containsKey("id")&& !StringUtils.isEmpty(areacondition.get("id")) ){
				WHERE("FIND_IN_SET(#{id},sc.parent_ids) or sc.id=#{id}");
			}
		}}.toString();

	}
}
