package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper<SysUser> {
	@Select("SELECT sus.id,sus.`name` FROM sys_user sus,sys_user_role sur WHERE sus.del_flag='0' AND sur.role_id=#{id} AND sus.id=sur.user_id")
	 List<SysUser> selectByRole(int id);
	@Select("SELECT * FROM sys_user sus WHERE sus.office_id=#{map.officeId} " +
			"and sus.del_flag='0' " +
			"and not " +
			"EXISTS(select 1 FROM sys_user_role sur WHERE sur.user_id=sus.id " +
			"and sur.role_id=#{map.roleId})")
	List<SysUser> selectByunRole(@Param("map") Map<String, String> map);

	@SelectProvider(type = SysUserSqlProvider.class,method = "selectSqlPage")
	List<SysUser> selectPage(Map<String,String> map);

	@Delete("DELETE from sys_user_role WHERE user_id=#{uid}")
	int deleteByUid(Long uid);

	@Insert("INSERT INTO sys_user_role(role_id, user_id, create_by, " +
			"create_date, update_by, update_date, del_flag) VALUES (#{rid}, " +
			"#{uid}, NULL, now(), NULL, now(), '0')")
	int insertRoleUser(@Param("rid") Long rid,@Param("uid") Long uid);
}