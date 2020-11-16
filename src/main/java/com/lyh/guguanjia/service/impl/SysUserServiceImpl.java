package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.mapper.SysUserMapper;
import com.lyh.guguanjia.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Long> implements SysUserService {


	@Autowired
	SysUserMapper sysUserMapper;
	@Override
	public List<SysUser> selectbyrole(int id){
		return sysUserMapper.selectByRole(id);
}

	@Override
	public List<SysUser> selectbyunrole(Map<String,String> map){
		return  sysUserMapper.selectByunRole(map);
}
	@Override
	public PageInfo<SysUser> selectPage(int pageNum, int pageSize, Map<String, String> map){
	PageHelper.startPage(pageNum,pageSize);
	List<SysUser> sysUsers = sysUserMapper.selectPage(map);
	return new PageInfo<>(sysUsers);
}
	@Override
	public int  userUp(SysUser sysUser){
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		sysUserMapper.deleteByUid(sysUser.getId());
		sysUserMapper.insertRoleUser(sysUser.getRoleId(),sysUser.getId());
		return 1;
	}

	@Override
	public int userAdd(SysUser sysUser){
		sysUser.setCreateDate(new Date());
		sysUser.setUpdateDate(new Date());
		sysUser.setDelFlag("0");
		sysUserMapper.insertSelective(sysUser);
		System.out.println(sysUser.getId());
		sysUserMapper.insertRoleUser(sysUser.getRoleId(),sysUser.getId());
		return 1;
	}

}
