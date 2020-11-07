package com.lyh.guguanjia.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@Qualifier
	public int insertBatch(Long roleId,Long[] dxIds){
		return sysRoleMapper.insertBatch(roleId,dxIds);
	}
	@Qualifier
	public int delBatch(Long roleId,Long[] yxIds){
		return sysRoleMapper.delBatch(roleId,yxIds);
	}

	@Qualifier
	public int doUpdate(Map<String,Object> params){
		ObjectMapper objectMapper = new ObjectMapper();
		SysRole sysRole=null;
		try {
			String s= objectMapper.writeValueAsString(params.get("role"));
			sysRole = objectMapper.readValue(s, SysRole.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);

		if ( params.containsKey("_resources")&&params.containsKey("resources") ){
			List<Integer> _resources = (List<Integer>)params.get("_resources");
			List<Integer> resources = (List<Integer>)params.get("resources");
			if ( !(_resources.size()==resources.size()&&_resources.containsAll(resources)) ){
				sysRoleMapper.delBatchByResource(sysRole.getId());
				sysRoleMapper.insertBatchByResource(sysRole.getId(),resources);
			}
		}
		if ( params.containsKey("_offices")&&params.containsKey("offices")){
			List<Integer> _offices = (List<Integer>)params.get("_offices");
			List<Integer> offices = (List<Integer>)params.get("offices");
			if ( !(_offices.size()==offices.size()&&_offices.containsAll(offices)) ){
				sysRoleMapper.delBatchByOffice(sysRole.getId());
				if ( offices.size()!=0 ){
					sysRoleMapper.insertBatchByOffice(sysRole.getId(),offices);
				}
			}
		}
		return 1;
	}
}
