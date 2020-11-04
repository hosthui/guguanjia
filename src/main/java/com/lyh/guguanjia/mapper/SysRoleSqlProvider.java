package com.lyh.guguanjia.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class SysRoleSqlProvider {

	public String selectSqlPage(Map<String,String> sysRoleCondition){

		/**
		 * SELECT sr.*, so.`name` office_name FROM sys_role sr,sys_office so WHERE sr.del_flag='0'
		 * and sr.office_id=so.id
		 * and sr.data_scope='1'
		 * and sr.office_id='56'
		 * and sr.`name` LIKE CONCAT('%','超','%')
		 */
		return new SQL(){{
			SELECT("sr.*, so.`name` office_name");
			FROM("sys_role sr,sys_office so");
			WHERE("sr.del_flag='0' and sr.office_id=so.id");
			if (sysRoleCondition.containsKey("dataScope") &&!StringUtils.isEmpty(sysRoleCondition.get("dataScope")) ){
				WHERE("sr.data_scope=#{dataScope}");
			}
			if ( sysRoleCondition.containsKey("officeId") &&!StringUtils.isEmpty(sysRoleCondition.get("officeId")) ){
				WHERE("sr.office_id=#{officeId}");
			}
			if ( sysRoleCondition.containsKey("name") &&!StringUtils.isEmpty(sysRoleCondition.get("name")) ){
				WHERE("sr.`name` LIKE CONCAT('%',#{name},'%')");
			}
		}}.toString();

	}
}
