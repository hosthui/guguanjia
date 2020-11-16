package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysArea;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface SysAreaService extends BaseService<SysArea,Long> {

	PageInfo<SysArea> selectpage(int pageNum, int pageSize, Map<String, String> areacondition);

	List<SysArea> selectall();

	int updateByPrimaryKeySelective(SysArea sysArea);


	void download(OutputStream op);

	void upload(InputStream inputStream);
}
