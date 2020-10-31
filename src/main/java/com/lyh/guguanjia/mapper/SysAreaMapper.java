package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysArea;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysAreaMapper extends Mapper<SysArea> {

	@SelectProvider(type = SysAreaSqlProvider.class,method = "selectSqlPage")
	 List<SysArea> selectpage(Map<String,String> areacondition);
}