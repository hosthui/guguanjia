package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysArea;

import java.util.Map;

public interface SysAreaService extends BaseService<SysArea,Long> {

	PageInfo<SysArea> selectpage(int pageNum,int pageSize,
	                             Map<String,String> areacondition);
}
