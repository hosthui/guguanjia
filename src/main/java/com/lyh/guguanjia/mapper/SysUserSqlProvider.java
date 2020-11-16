package com.lyh.guguanjia.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

public class SysUserSqlProvider {

	/**
	 * SELECT
	 *  ssu.*,
	 *  ssr.`name` role_name,
	 *  sso.`name` office_name
	 * FROM
	 *  sys_user ssu
	 *  LEFT JOIN sys_user_role sur ON ssu.id=sur.user_id
	 *  LEFT JOIN sys_role ssr ON ssr.id=sur.role_id
	 *  LEFT JOIN sys_office sso ON sso.id=ssu.office_id
	 * WHERE
	 *  ssu.del_flag = '0'
	 *  GROUP BY ssu.id HAVING 1=1
	 *  and ssu.phone LIKE CONCAT('%','17','%')
	 *  and ssu.`name` LIKE CONCAT( '%', '管理', '%' )
	 *  and ssr.id = 56
	 * @return
	 */
	public String selectSqlPage(Map<String,String> map){
		StringBuffer buffer = new StringBuffer(" SELECT " +
				" ssu.id, " + "ssu.company_id, " + "ssu.office_id, " + "ssu.username, " + "ssu.`no`, " + "ssu.`name`, " + "ssu.email, " + "ssu.phone, " + "ssu.mobile, " + "ssu.user_type, " + "ssu.device_code, " + "ssu.login_ip, " + "ssu.login_date, " + "ssu.create_by, " + "ssu.create_date, " + "ssu.update_by, " + "ssu.update_date, " + "ssu.remarks, " + "ssu.del_flag, " + "ssu.`status`, " + "ssu.head_picture, " + " ssr.`name` role_name, " + " sso.`name` " +
				"office_name, " +" ssr.id role_id "+
				" FROM " + " sys_user ssu " +
				" LEFT JOIN sys_user_role sur ON ssu.id=sur.user_id " +
				" LEFT JOIN sys_role ssr ON ssr.id=sur.role_id " +
				" LEFT JOIN sys_office sso ON sso.id=ssu.office_id " +
				"WHERE " + " ssu.del_flag = '0' "+" GROUP BY ssu.id HAVING 1=1 ");
			if ( map.containsKey("phone")&&!StringUtils.isEmpty(map.get("phone")) ){

				buffer.append(" and ssu.phone LIKE CONCAT('%',#{phone},'%') ");
			}

			if ( map.containsKey("name")&&!StringUtils.isEmpty(map.get("name")) ){
				buffer.append(" and ssu.`name` LIKE CONCAT( '%', #{name}, '%') ");
			}
			if ( map.containsKey("roleid")&&!StringUtils.isEmpty(map.get("roleid")) ){
				buffer.append(" and ssr.id = #{roleid} ");
			}
		return buffer.toString();

	}
}
