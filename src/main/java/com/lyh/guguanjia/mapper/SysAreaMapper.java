package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysArea;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysAreaMapper extends Mapper<SysArea> {

	@SelectProvider(type = SysAreaSqlProvider.class,method = "selectSqlPage")
	 List<SysArea> selectpage(Map<String, String> areacondition);


	@Update("UPDATE sys_area set parent_ids=REPLACE(parent_ids,#{parentoldids},#{parentids}) WHERE FIND_IN_SET(#{areaid},parent_ids)")
	int updatebyparentids(@Param("areaid") Long areaid, @Param("parentids") String parentids, @Param("parentoldids") String parentoldids);

	@InsertProvider(type = SysAreaSqlProvider.class,method = "insertSqlBatch")
	int insertBatch(@Param("area") List<SysArea> area);
}