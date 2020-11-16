package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Examine;
import com.lyh.guguanjia.entity.ExamineCondition;

public interface ExamineService extends BaseService<Examine,Long> {

	PageInfo<Examine> selectpage(int pageNum, int pageSize, ExamineCondition examineCondition);
}
