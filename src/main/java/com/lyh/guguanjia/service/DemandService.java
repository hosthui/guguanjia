package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Demand;


public interface DemandService extends BaseService<Demand,Long> {

	PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize);

}
