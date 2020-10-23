package com.lyh.guguanjia.controller;


import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/manager/app")
public class AppVersionController {

	@Autowired
	AppVersionService service;

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public PageInfo<AppVersion> selectAll(@PathVariable int pageNum,
	                          @PathVariable int pageSize){
		return service.selectPage(pageNum,pageSize);
	}

	@RequestMapping("toupdate")
	public ModelAndView toUpdate(){
		return new ModelAndView("/html/app/update.html");
	}

}
