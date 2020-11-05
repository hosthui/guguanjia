package com.lyh.guguanjia.service.impl;

import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.mapper.SysUserMapper;
import com.lyh.guguanjia.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Long> implements SysUserService {


	@Autowired
	SysUserMapper sysUserMapper;
	@Qualifier
	public List<SysUser> selectbyrole(int id){
		return sysUserMapper.selectByRole(id);
}

	@Qualifier
	public List<SysUser> selectbyunrole(Map<String,String> map){
		return  sysUserMapper.selectByunRole(map);
}
}
