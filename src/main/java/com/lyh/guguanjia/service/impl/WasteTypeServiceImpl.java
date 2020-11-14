package com.lyh.guguanjia.service.impl;


import com.lyh.guguanjia.entity.WasteType;
import com.lyh.guguanjia.service.WasteTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WasteTypeServiceImpl extends BaseServiceImpl<WasteType,Long> implements WasteTypeService {

}
