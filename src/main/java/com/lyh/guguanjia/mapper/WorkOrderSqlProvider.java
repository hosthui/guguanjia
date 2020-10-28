package com.lyh.guguanjia.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class WorkOrderSqlProvider {


	public String selectSqlPage(Map<String,String> workCondition){
		return new SQL(){{
			SELECT("wo.*," +
					"sc.`name` create_user_name," +
					"st.`name` transport_user_name," +
					"sr.`name` recipient_user_name," +
					"coff.`name` create_office_name");
			FROM("work_order wo");
			LEFT_OUTER_JOIN("sys_user sc ON wo.create_user_id = sc.id " +
					" LEFT JOIN sys_user st ON wo.transport_user_id = st.id " +
					" LEFT JOIN sys_user sr ON wo.recipient_user_id = sr.id " +
					" LEFT JOIN sys_office coff ON sc.company_id = coff.id " +
					" LEFT JOIN sys_office toff ON st.company_id = toff.id " +
					" LEFT JOIN sys_office roff ON sr.company_id = roff.id ");
			WHERE("wo.del_flag='0'");
			if ( workCondition.containsKey("officeid")&&!StringUtils.isEmpty(workCondition.get("officeid")) ){
				WHERE("(coff.id=#{officeid} or toff.id=#{officeid} or roff.id=#{officeid} )");
			}
			if ( workCondition.containsKey("status")&&!StringUtils.isEmpty(workCondition.get("status")) ){
				WHERE("wo.status=#{status}");
			}
			if ( workCondition.containsKey("startTime")&&!StringUtils.isEmpty(workCondition.get("startTime")) ){
				WHERE("wo.createDate>=#{startTime}");
			}
			if ( workCondition.containsKey("endTime")&&!StringUtils.isEmpty(workCondition.get("endTime")) ){
				WHERE("wo.createDate<=#{endTime}");
			}
		}}.toString();

	}

}
