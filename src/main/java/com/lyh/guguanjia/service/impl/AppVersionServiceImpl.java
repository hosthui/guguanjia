package com.lyh.guguanjia.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.service.AppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion,Long>  implements AppVersionService{
 	@Override
	public PageInfo<AppVersion> selectPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		AppVersion appVersion = new AppVersion();
		appVersion.setDelFlag("0");
//		List<AppVersion> appVersions = mapper.selectAll();
		List<AppVersion> appVersions = mapper.select(appVersion);
		PageInfo<AppVersion> appVersionPageInfo = new PageInfo<>(appVersions);
		return appVersionPageInfo;
	}
}
