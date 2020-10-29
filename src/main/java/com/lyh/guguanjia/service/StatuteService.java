package com.lyh.guguanjia.service;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Statute;

public interface StatuteService extends  BaseService<Statute,Long> {
	 PageInfo<Statute> selectpage(int pageNum, int pageSize,
	                                    Statute statuteCondition);
}
