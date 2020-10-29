package com.lyh.guguanjia.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Statute;
import com.lyh.guguanjia.mapper.StatuteMapper;
import com.lyh.guguanjia.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StatuteServiceImpl extends BaseServiceImpl<Statute,Long> implements StatuteService {

	@Autowired
	StatuteMapper statuteMapper;

	@Qualifier
	public PageInfo<Statute> selectpage(int pageNum, int pageSize,
	                                    Statute statuteCondition){
		PageHelper.startPage(pageNum,pageSize);
		List<Statute> select = statuteMapper.select(statuteCondition);
		return new PageInfo<>(select);
	}

}
