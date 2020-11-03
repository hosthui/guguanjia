package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;
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


	public String insertSqlBatch(@Param("area") List<SysArea> area){
		return new SQL(){{
			INSERT_INTO("`sys_area`");
			INTO_COLUMNS("`parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`");
//			for ( SysArea  sysArea: area ) {
//				StringBuffer buffer = new StringBuffer();
//				buffer.append(" #{parentId},#{parentIds},#{code},#{name},#{type},#{createBy},#{createDate},#{updateBy},#{updateDate},#{remarks},#{delFlag},#{icon}");
//				INTO_VALUES("");
//				ADD_ROW();//动态添加()
//			}
			for ( int i = 0; i < area.size(); i++ ) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" #{area["+i+"].parentId},#{area["+i+"].parentIds},#{area["+i+"].code}," +
						"#{area["+i+"].name},#{area["+i+"].type},#{area["+i+"].createBy},#{area["+i+"].createDate},#{area["+i+"].updateBy},#{area["+i+"].updateDate},#{area["+i+"].remarks},#{area["+i+"].delFlag},#{area["+i+"].icon}");
				INTO_VALUES(buffer.toString());
				ADD_ROW();//动态添加()
			}
		}}.toString();
	}
}
