package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysRole;
import com.lyh.guguanjia.mapper.SysRoleMapper;
import com.lyh.guguanjia.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,Long> implements SysRoleService {

	@Autowired
	SysRoleMapper sysRoleMapper;

	@Qualifier
	public PageInfo<SysRole> selectPage(int pageNum,int pageSize,
	                                    Map<String,String> sysRoleCondition){
		PageHelper.startPage(pageNum,pageSize);
		List<SysRole> selectpage = sysRoleMapper.selectpage(sysRoleCondition);
		return new PageInfo<>(selectpage);
	}

}
