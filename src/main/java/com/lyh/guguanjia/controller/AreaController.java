package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysArea;
import com.lyh.guguanjia.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("manager/area")
public class AreaController {


	@Autowired
	SysAreaService sysAreaService;

	@RequestMapping("areaPage/{pageNum}/{pageSize}")
	public Result areapage(@PathVariable int pageNum,
	                       @PathVariable int pageSize, @RequestParam Map<String,String> params){
		return new Result(sysAreaService.selectpage(pageNum,pageSize,params));
	}

	@RequestMapping("areaAll")
	public Result areaall(){
		return new Result(sysAreaService.selectAll());
	}
	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/area/save.html");
	}


	@RequestMapping(value = "doupdate",method = RequestMethod.PUT)
	public Result doupdate(@RequestBody SysArea sysArea){
		Date date = new Date();
		int i = sysAreaService.updateByPrimaryKeySelective(sysArea);
		Result result = new Result();
		if ( i>0 ){
			result.setSuccess(true);
		}
		return result;
	}


	@RequestMapping("toselect")
	public ModelAndView toselect(){
		return new ModelAndView("/area/select.html");
	}

	@RequestMapping("tomodules")
	public ModelAndView tomodules(){
		return new ModelAndView("/modules/font-awesome.html");
	}

}
