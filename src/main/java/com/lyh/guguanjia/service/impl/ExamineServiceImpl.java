package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Examine;
import com.lyh.guguanjia.entity.ExamineCondition;
import com.lyh.guguanjia.mapper.ExamineMapper;
import com.lyh.guguanjia.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ExamineServiceImpl extends BaseServiceImpl<Examine,Long> implements ExamineService {


	@Autowired
	ExamineMapper examineMapper;

	@Qualifier
	public PageInfo<Examine> selectpage(int pageNum, int pageSize,
	                                    ExamineCondition examineCondition){
		PageHelper.startPage(pageNum,pageSize);
		List<Examine> examines = examineMapper.selectPage(examineCondition);
		return  new PageInfo<>(examines);

	}

}
