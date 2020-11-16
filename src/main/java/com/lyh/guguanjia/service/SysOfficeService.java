package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysOffice;

import java.util.Map;


public interface SysOfficeService extends BaseService<SysOffice,Long> {
	PageInfo<SysOffice> selectpage(int pageNum, int pageSize, String conName);
	int officeupdate(Map<String, Object> map);
}
