package com.lyh.guguanjia.service;

import com.lyh.guguanjia.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends BaseService<SysUser,Long> {
	 List<SysUser> selectbyrole(int id);
	 List<SysUser> selectbyunrole(Map<String, String> map);
}
