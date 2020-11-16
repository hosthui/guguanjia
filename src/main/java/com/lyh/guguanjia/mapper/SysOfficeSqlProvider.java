package com.lyh.guguanjia.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

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

	public String insertBatch(@Param("oid") Long oid, @Param("wids") List<Integer> wids){
//INSERT INTO office_waste (waste_id,office_id,del_flag,create_date,update_date,create_by)
// VALUES ('2', '5', '0', now(), now(), NULL);
		StringBuffer stringBuffer = new StringBuffer(" INSERT INTO office_waste (waste_id,office_id,del_flag,create_date,update_date,create_by) VALUES ");
		for (int i = 0; i < wids.size(); i++) {
			stringBuffer.append("('")
					.append(wids.get(i)+"','")
					.append(oid+"',")
					.append("'0',")
					.append("now(),")
					.append("now(),")
					.append("NULL),");
		}
		stringBuffer.deleteCharAt(stringBuffer.length()-1);
		System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
	}
}
