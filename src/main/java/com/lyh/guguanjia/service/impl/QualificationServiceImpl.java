package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Qualification;
import com.lyh.guguanjia.entity.QualificationCondition;
import com.lyh.guguanjia.mapper.QualificationMapper;
import com.lyh.guguanjia.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class QualificationServiceImpl extends BaseServiceImpl<Qualification,
		Long> implements QualificationService {

	@Autowired
	QualificationMapper qualificationMapper;

	@Override
	public PageInfo<Qualification> selectPage(Integer pageNum, Integer PageSize,
	                                          QualificationCondition qualificationCondition) {
		PageHelper.startPage(pageNum,PageSize);
		List<Qualification> qualifications = qualificationMapper.selectPage(qualificationCondition);
		PageInfo<Qualification> qualificationPageInfo = new PageInfo<>(qualifications);

		return qualificationPageInfo;
	}
}
