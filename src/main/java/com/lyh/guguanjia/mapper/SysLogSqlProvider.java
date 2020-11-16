package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysLog;
import org.springframework.util.StringUtils;

public class SysLogSqlProvider {

	public String selectSqlPage(SysLog sysLog){
		StringBuffer buffer = new StringBuffer(" select * FROM sys_log where 1=1 ");
		if ( !StringUtils.isEmpty(sysLog.getType()) ){
			buffer.append(" and type=#{type} ");
		}
		if ( !StringUtils.isEmpty(sysLog.getDescription())) {
			buffer.append(" and description LIKE CONCAT('%',#{description},'%') ");
		}
		return buffer.toString();
	}
}
