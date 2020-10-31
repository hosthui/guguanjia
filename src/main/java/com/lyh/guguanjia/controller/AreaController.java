package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("toselect")
	public ModelAndView toselect(){
		return new ModelAndView("/area/select.html");
	}

}
