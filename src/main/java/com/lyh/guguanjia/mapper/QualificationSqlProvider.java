package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.QualificationCondition;
import org.springframework.util.StringUtils;

public class QualificationSqlProvider {

	public String selectSqlPage(QualificationCondition qualificationCondition){
		StringBuffer buffer = new StringBuffer("SELECT qu.*,uu.username upload_username,cu.username check_username from qualification qu " +
				" left JOIN sys_user uu " +
				 " on qu.upload_user_id=uu.id " +
				 " left JOIN sys_user cu " +
				 " on qu.check_user_id=cu.id " +
				  " WHERE qu.del_flag=0 ");
		if ( !StringUtils.isEmpty(qualificationCondition.getCheck()) ){
			buffer.append(" and  qu.check=#{check}");
		}
		if ( !StringUtils.isEmpty(qualificationCondition.getType()) ){
			buffer.append(" and  qu.type=#{type}");
		}
		if ( !StringUtils.isEmpty(qualificationCondition.getStartTime()) ){
			buffer.append("   and qu.create_date>=#{startTime}");
		}
		if ( !StringUtils.isEmpty(qualificationCondition.getEndTime()) ){
			buffer.append("   and qu.create_date<=#{endTime}");
		}
		return buffer.toString();
	}
}
