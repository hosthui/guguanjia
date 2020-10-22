package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager/app")
public class AppVersionController {

	@Autowired
	AppVersionService service;

	@RequestMapping("index")
	public List<AppVersion> selectAll(){
		return service.selectAll();
	}

}
