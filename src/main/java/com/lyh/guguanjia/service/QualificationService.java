package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Qualification;
import com.lyh.guguanjia.entity.QualificationCondition;

public interface QualificationService extends BaseService<Qualification,Long> {

	 PageInfo<Qualification> selectPage(Integer pageNum, Integer PageSize, QualificationCondition qualificationCondition);
}
