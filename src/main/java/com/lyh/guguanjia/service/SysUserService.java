package com.lyh.guguanjia.service;

import com.lyh.guguanjia.entity.SysUser;

import java.util.List;

public interface SysUserService extends BaseService<SysUser,Long> {
	 List<SysUser> selectbyrole(int id);
}
