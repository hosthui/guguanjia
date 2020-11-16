package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysLog;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysLogMapper extends Mapper<SysLog> {


	@SelectProvider(type = SysLogSqlProvider.class,method = "selectSqlPage")
	List<SysLog> selectPage(SysLog sysLog);
}