package com.lyh.guguanjia.service;

import com.lyh.guguanjia.entity.Waste;

import java.util.List;

public interface WasteService extends BaseService<Waste,Long> {
	List<Waste> offwbyid(Long officeId);
}
