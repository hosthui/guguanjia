package com.lyh.guguanjia.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.SysOffice;
import com.lyh.guguanjia.entity.SysRole;
import com.lyh.guguanjia.mapper.SysOfficeMapper;
import com.lyh.guguanjia.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice,Long> implements SysOfficeService {



	@Autowired
	SysOfficeMapper sysOfficeMapper;


	@Qualifier
	public PageInfo<SysOffice> selectpage(int pageNum,int pageSize,
	                                      String conName){

		PageHelper.startPage(pageNum,pageSize);
		List<SysOffice> selectpage = sysOfficeMapper.selectpage(conName);
		return new PageInfo<>(selectpage);

	}

	@Qualifier
	public int officeupdate(Map<String,Object> map){
		ObjectMapper objectMapper = new ObjectMapper();
		SysOffice sysOffice = null;
		if (map.containsKey("company")&&!StringUtils.isEmpty(map.get("company"))){
			try {
				String s= objectMapper.writeValueAsString(map.get("company"));
				sysOffice = objectMapper.readValue(s, SysOffice.class);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		sysOfficeMapper.updateByPrimaryKeySelective(sysOffice);
		if (map.containsKey("oldcomwastes")&&map.containsKey("comwastes")){
			if (!StringUtils.isEmpty(map.get("oldcomwastes"))&&!StringUtils.isEmpty(map.get("comwastes"))){
				List<Integer> oldcomwasteslist = (List<Integer>) map.get("oldcomwastes");
				List<Integer> comwasteslist = (List<Integer>) map.get("comwastes");
				if (oldcomwasteslist.size()==comwasteslist.size()&&comwasteslist.containsAll(oldcomwasteslist)){
					return 2;
				}
				sysOfficeMapper.delofficewastebyid(sysOffice.getId());
				sysOfficeMapper.insertbatch(sysOffice.getId(),comwasteslist);


			}
		}
		return 1;
	}

	@Cacheable(cacheNames = "sysofficecache",key = "'com.lyh.guguanjia" +
			".service.impl.SysOfficeServiceImpl:select:'+#sysOffice.delFlag")
	@Override
	public List<SysOffice> select(SysOffice sysOffice){
		return super.select(sysOffice);
	}
}