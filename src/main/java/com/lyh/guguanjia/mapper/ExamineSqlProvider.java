package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.ExamineCondition;
import org.springframework.util.StringUtils;

public class ExamineSqlProvider {

	public String selectSqlPage(ExamineCondition examineCondition){
		StringBuffer buffer = new StringBuffer(" SELECT " +
				" ex.*,su.username,so.`name` officename " +
				" FROM examine ex,sys_user su,sys_office so " +
				" WHERE " +
				" ex.del_flag = 0 " +
				" AND ex.examine_user_id = su.id " +
				" AND su.company_id = so.id ");

		if ( !StringUtils.isEmpty(examineCondition.getOfficeid()) ){
			buffer.append(" AND so.id = #{officeid} ");
		}
		if ( !StringUtils.isEmpty(examineCondition.getType()) ){
			buffer.append(" AND ex.type = #{type}  ");
		}
		if ( !StringUtils.isEmpty(examineCondition.getUsername()) ){
			buffer.append(" AND su.username LIKE CONCAT( '%', #{username}, '%' )  ");
		}
		return buffer.toString();
	}
}
