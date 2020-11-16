package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysLog;

public interface SysLogService extends BaseService<SysLog,Long> {

	PageInfo<SysLog> selectPage(int pageNum, int pageSize, SysLog sysLog);
}
