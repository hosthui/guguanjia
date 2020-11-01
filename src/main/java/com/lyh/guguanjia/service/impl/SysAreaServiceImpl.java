package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysArea;
import com.lyh.guguanjia.mapper.SysAreaMapper;
import com.lyh.guguanjia.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea,Long> implements SysAreaService {
	@Autowired
	SysAreaMapper sysAreaMapper;
	@Qualifier
	public PageInfo<SysArea> selectpage(int pageNum, int pageSize, Map<String
			,String> areacondition){
		PageHelper.startPage(pageNum,pageSize);
		List<SysArea> sysAreas = sysAreaMapper.selectpage(areacondition);
		return new PageInfo<>(sysAreas);
	}

	@Qualifier
	public List<SysArea> selectall(){
		return sysAreaMapper.selectpage(new HashMap<>());
	}
	
}
