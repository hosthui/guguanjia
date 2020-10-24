package com.lyh.guguanjia.controller;


import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


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

	@RequestMapping(value = "update",method = RequestMethod.PUT)
	public Result update(@RequestBody AppVersion app){
		return  new Result(service.updateByPrimaryKeySelective(app));
	}

	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public Result add(@RequestBody  AppVersion app){
		Date date = new Date();
		app.setCreateDate(date);
		app.setUpdateDate(date);
		app.setDelFlag("0");
		app.setCreateBy("sys");
		return new Result(service.insertSelective(app));
	}
}
