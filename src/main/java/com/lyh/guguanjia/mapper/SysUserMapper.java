package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper<SysUser> {
	@Select("SELECT sus.id,sus.`name` FROM sys_user sus,sys_user_role sur WHERE sus.del_flag='0' AND sur.role_id=#{id} AND sus.id=sur.user_id")
	 List<SysUser> selectByRole(int id);
}