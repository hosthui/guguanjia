package com.lyh.guguanjia.service;

import com.lyh.guguanjia.entity.SysResource;

import java.util.List;

public interface SysResourceService extends BaseService<SysResource,Long> {
	List<Integer> selectnbyrid(int rid);
	List<Integer> officeSelectnbyrid(int rid);
}
