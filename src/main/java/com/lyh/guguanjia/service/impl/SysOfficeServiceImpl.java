package com.lyh.guguanjia.service.impl;

import com.lyh.guguanjia.entity.SysOffice;
import com.lyh.guguanjia.service.SysOfficeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice,Long> implements SysOfficeService {



	@Cacheable(cacheNames = "sysofficecache",key = "'com.lyh.guguanjia" +
			".service.impl.SysOfficeServiceImpl:select:'+#sysOffice.delFlag")
	@Override
	public List<SysOffice> select(SysOffice sysOffice){
		return super.select(sysOffice);
	}
}