package com.lyh.guguanjia.service.impl;

import com.lyh.guguanjia.entity.SysLog;
import com.lyh.guguanjia.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog,Long> implements SysLogService {

	@Override
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public int insertSelective(SysLog appVersion) {
		return super.insertSelective(appVersion);
	}
}
