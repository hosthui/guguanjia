package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysResource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysResourceMapper extends Mapper<SysResource> {

	
	@Select("select ssr.id FROM sys_resource ssr,sys_role_resource srr WHERE srr.role_id=#{rid} and ssr.type='0' and srr.resource_id=ssr.id")
	List<Integer> selectnbyrid(int rid);

	@Select("select sso.id FROM sys_office sso,sys_role_office sro WHERE sro.role_id=#{rid} and sro.office_id=sso.id")
	List<Integer> officeSelectnbyrid(int rid);

	@Select("SELECT sr.* FROM sys_user_role sur,sys_role_resource srr,sys_resource sr " + 
			"WHERE sur.user_id=#{uid}  " +
			"and sr.del_flag='0' " + 
			"and sr.type='0' " + 
			"and sr.common='0' " + 
			"and srr.role_id=sur.role_id " + 
			"and srr.resource_id=sr.id " + 
			"ORDER BY sr.sort")
	List<SysResource> resourcesbyUserid(Long uid);

}