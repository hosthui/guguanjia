package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.WorkOrder;

import java.util.Map;

public interface WorkOrderService extends BaseService<WorkOrder,Long>{

	PageInfo<WorkOrder> selectpage(int pageNum, int pageSize,
	                    Map<String,String> workCondition);

	Map<String,Object> selectdetail(Integer id);

}
