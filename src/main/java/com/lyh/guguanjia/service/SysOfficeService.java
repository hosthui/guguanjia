package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysOffice;


public interface SysOfficeService extends BaseService<SysOffice,Long> {
	PageInfo<SysOffice> selectpage(int pageNum, int pageSize,
	                               String conName);

}
