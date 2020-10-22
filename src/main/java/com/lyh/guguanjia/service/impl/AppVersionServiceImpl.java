package com.lyh.guguanjia.service.impl;



import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.service.AppVersionService;
import com.lyh.guguanjia.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion,Long>  implements AppVersionService{

}
