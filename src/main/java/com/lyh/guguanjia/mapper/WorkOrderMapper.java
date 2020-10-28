package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.WorkOrder;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface WorkOrderMapper extends Mapper<WorkOrder> {

	@SelectProvider(type = WorkOrderSqlProvider.class,method = "selectSqlPage")
	List<WorkOrder> selectpage(Map<String,String> workCondition);
}