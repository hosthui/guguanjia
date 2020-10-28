package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.WorkOrder;
import com.lyh.guguanjia.mapper.WorkOrderMapper;
import com.lyh.guguanjia.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class WorkOrderServiceImpl extends BaseServiceImpl<WorkOrder,Long> implements WorkOrderService {


	@Autowired
	WorkOrderMapper workOrderMapper;


	@Override
	public PageInfo<WorkOrder> selectpage(int pageNum, int pageSize, Map<String, String> workCondition) {
		PageHelper.startPage(pageNum,pageSize);
		List<WorkOrder> selectpage = workOrderMapper.selectpage(workCondition);
		return new PageInfo<>(selectpage);
	}

	@Override
	public Map<String, Object> selectdetail(Integer id) {
		Map<String, Object> workOrderMap = workOrderMapper.selectbyId(id);
		List<Map<String, Object>> selectdetailbyId = workOrderMapper.selectdetailbyId(id);
		List<Map<String, Object>> selecttransferbyId = workOrderMapper.selecttransferbyId(id);
		workOrderMap.put("detail",selectdetailbyId);
		workOrderMap.put("transfer",selecttransferbyId);
		return workOrderMap;
	}
}
