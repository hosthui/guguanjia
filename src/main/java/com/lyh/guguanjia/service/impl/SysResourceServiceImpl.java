package com.lyh.guguanjia.service.impl;


import com.lyh.guguanjia.entity.SysResource;
import com.lyh.guguanjia.mapper.SysResourceMapper;
import com.lyh.guguanjia.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource,Long> implements SysResourceService {


	@Autowired
	SysResourceMapper sysResourceMapper;

	@Qualifier
	public List<Integer> selectnbyrid(int rid){
		return sysResourceMapper.selectnbyrid(rid);
	}

	@Qualifier
	public List<Integer> officeSelectnbyrid(int rid){
		return sysResourceMapper.officeSelectnbyrid(rid);
	}


}
