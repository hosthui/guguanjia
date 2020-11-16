package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.AppVersion;


public interface AppVersionService extends BaseService<AppVersion,Long> {

PageInfo<AppVersion> selectPage(int pageNum, int pageSize);

}
