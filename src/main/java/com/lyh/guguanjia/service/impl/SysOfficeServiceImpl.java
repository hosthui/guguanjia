package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysOffice;
import com.lyh.guguanjia.mapper.SysOfficeMapper;
import com.lyh.guguanjia.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice,Long> implements SysOfficeService {



	@Autowired
	SysOfficeMapper sysOfficeMapper;


	@Qualifier
	public PageInfo<SysOffice> selectpage(int pageNum,int pageSize,
	                                      String conName){

		PageHelper.startPage(pageNum,pageSize);
		List<SysOffice> selectpage = sysOfficeMapper.selectpage(conName);
		return new PageInfo<>(selectpage);

	}


	@Cacheable(cacheNames = "sysofficecache",key = "'com.lyh.guguanjia" +
			".service.impl.SysOfficeServiceImpl:select:'+#sysOffice.delFlag")
	@Override
	public List<SysOffice> select(SysOffice sysOffice){
		return super.select(sysOffice);
	}
}