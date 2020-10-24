package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Demand;
import com.lyh.guguanjia.service.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DemandServiceImpl extends BaseServiceImpl<Demand,Long> implements DemandService {


	@Override
	public PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Demand demand = new Demand();
		demand.setDelFlag("0");
		List<Demand> select = mapper.select(demand);
		PageInfo<Demand> pageinfo=new PageInfo<>(select);
		return pageinfo;
	}
}
