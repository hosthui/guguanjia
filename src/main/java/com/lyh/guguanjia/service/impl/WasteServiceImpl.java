package com.lyh.guguanjia.service.impl;

import com.lyh.guguanjia.entity.Waste;
import com.lyh.guguanjia.mapper.WasteMapper;
import com.lyh.guguanjia.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class WasteServiceImpl extends BaseServiceImpl<Waste,Long> implements WasteService {

	@Autowired
	WasteMapper wasteMapper;


	@Qualifier
	public List<Waste> offwbyid(Long officeId){
		return wasteMapper.offwbyid(officeId);
	}

}
