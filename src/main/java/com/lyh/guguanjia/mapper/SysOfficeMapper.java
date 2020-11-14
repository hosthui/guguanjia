package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysOffice;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysOfficeMapper extends Mapper<SysOffice> {


	@SelectProvider(type = SysOfficeSqlProvider.class,method = "selectSqlPage")
	 List<SysOffice> selectpage(String conName);

}