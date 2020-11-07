package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysRole;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {

	@SelectProvider(type = SysRoleSqlProvider.class,method = "selectSqlPage")
	List<SysRole> selectpage(Map<String,String> sysRoleCondition);

	@InsertProvider(type=SysRoleSqlProvider.class,method = "insertSql")
	int insertBatch(@Param("roleId") Long roleId,@Param("dxIds") Long[] dxIds);

	@DeleteProvider(type=SysRoleSqlProvider.class,method = "delSql")
	int delBatch(@Param("roleId") Long roleId,@Param("yxIds") Long[] yxIds);

	@Delete("DELETE FROM sys_role_office WHERE role_id=#{roleId}")
	int delBatchByOffice(@Param("roleId") Long roleId);
	@InsertProvider(type=SysRoleSqlProvider.class,method = "insertSqlbyoffice")
	int insertBatchByOffice(@Param("roleId") Long roleId,
	                        @Param("oid") List<Integer> oid);


	@Delete("DELETE FROM sys_role_resource WHERE role_id=#{roleId}")
	int delBatchByResource(@Param("roleId") Long roleId);
	@InsertProvider(type =SysRoleSqlProvider.class,method = "insertBatchByResource")
	int insertBatchByResource(@Param("roleId") Long roleId,
	                          @Param("rid") List<Integer> rid);


}

