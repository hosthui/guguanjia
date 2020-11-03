package com.lyh.guguanjia.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysAnalysisListener;
import com.lyh.guguanjia.entity.SysArea;
import com.lyh.guguanjia.mapper.SysAreaMapper;
import com.lyh.guguanjia.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea,Long> implements SysAreaService {
	@Autowired
	SysAreaMapper sysAreaMapper;


	@Qualifier
	public PageInfo<SysArea> selectpage(int pageNum, int pageSize, Map<String
			,String> areacondition){
		PageHelper.startPage(pageNum,pageSize);
		List<SysArea> sysAreas = sysAreaMapper.selectpage(areacondition);
		return new PageInfo<>(sysAreas);
	}

	@Qualifier
	public List<SysArea> selectall(){
		return sysAreaMapper.selectpage(new HashMap<>());
	}

	@Override
	public int updateByPrimaryKeySelective(SysArea sysArea) {
			sysAreaMapper.updateByPrimaryKeySelective(sysArea);
		if ( !StringUtils.isEmpty(sysArea.getParentOldIds()) ){
			if ( !sysArea.getParentOldIds().equals(sysArea.getParentIds()) ){
				sysAreaMapper.updatebyparentids(sysArea.getId(),
						sysArea.getParentIds(),sysArea.getParentOldIds());
				return 2;
			}
		}
		return 1;
	}

	@Override
	public void download(OutputStream op){
		SysArea sysArea = new SysArea();
		sysArea.setDelFlag("0");
		EasyExcel.write(op,SysArea.class).sheet("区域信息").doWrite(sysAreaMapper.select(sysArea));
	}

	@Override
	public void upload(InputStream inputStream){
		EasyExcel.read(inputStream,SysArea.class,
				new SysAnalysisListener(sysAreaMapper)).sheet().doRead();
	}
}
