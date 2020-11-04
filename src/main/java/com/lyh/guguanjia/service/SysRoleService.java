package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysRole;

import java.util.Map;

public interface SysRoleService extends BaseService<SysRole,Long>{
	PageInfo<SysRole> selectPage(int pageNum, int pageSize,
	                             Map<String,String> sysRoleCondition);
}
