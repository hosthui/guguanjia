package com.lyh.guguanjia.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lyh.guguanjia.mapper.SysAreaMapper;

import java.util.ArrayList;
import java.util.List;


public class SysAnalysisListener extends AnalysisEventListener<SysArea> {


	private List<SysArea> areas=new ArrayList();

	private SysAreaMapper sysAreaMapper;


	public SysAnalysisListener(SysAreaMapper sysAreaMapper) {
		this.sysAreaMapper = sysAreaMapper;
	}

	public SysAnalysisListener() {
	}

	@Override
	public void invoke(SysArea sysArea, AnalysisContext analysisContext) {
		sysArea.setDelFlag("0");
		areas.add(sysArea);
		if ( areas.size()==10 ){
			sysAreaMapper.insertBatch(areas);
			areas.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		if ( areas.size()>0 ){
			sysAreaMapper.insertBatch(areas);
			areas.clear();
		}
	}
}
