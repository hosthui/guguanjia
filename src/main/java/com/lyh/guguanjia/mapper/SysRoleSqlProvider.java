package com.lyh.guguanjia.mapper;

import org.apache.ibatis.annotations.Param;
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
	public String insertSql(@Param("roleId") Long roleId, @Param("dxIds") Long[] dxIds){
		/**
		 * INSERT INTO sys_user_role(role_id, user_id,  del_flag) VALUES ( 2, 45, '0');
		 */
		return new SQL(){{
			INSERT_INTO("sys_user_role");
			INTO_COLUMNS("role_id, user_id,  del_flag");
			StringBuffer buffer = new StringBuffer();
			for ( int i = 0; i < dxIds.length; i++ ) {
				buffer.append("#{roleId},#{dxIds["+i+"]},'0'");
				INTO_VALUES(buffer.toString());
				ADD_ROW();
				buffer.delete(0,buffer.length());
			}
		}}.toString();
	}

	/**
	 * DELETE FROM sys_user_role WHERE role_id=1 and user_id in (65,61)
	 */
	public String delSql(@Param("roleId") Long roleId,@Param("yxIds") Long[] yxIds){
		StringBuffer buffer = new StringBuffer(" DELETE FROM sys_user_role WHERE role_id=1 and user_id in (");
		for ( int i = 0; i < yxIds.length; i++ ) {
			buffer.append("#{yxIds["+i+"]},");
		}
		buffer.deleteCharAt(buffer.lastIndexOf(","));
		buffer.append(")");
		return buffer.toString();

	}
}
