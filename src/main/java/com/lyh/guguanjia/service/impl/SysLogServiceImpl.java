package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysLog;
import com.lyh.guguanjia.mapper.SysLogMapper;
import com.lyh.guguanjia.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog,Long> implements SysLogService {
	@Autowired
	SysLogMapper sysLogMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public int insertSelective(SysLog appVersion) {
		return super.insertSelective(appVersion);
	}

	@Override
	public PageInfo<SysLog> selectPage(int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<SysLog> sysLogs = sysLogMapper.selectAll();
		return new PageInfo<>(sysLogs);
	}

}
