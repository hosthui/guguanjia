package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysRole;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {

	@SelectProvider(type = SysRoleSqlProvider.class,method = "selectSqlPage")
	List<SysRole> selectpage(Map<String,String> sysRoleCondition);
}