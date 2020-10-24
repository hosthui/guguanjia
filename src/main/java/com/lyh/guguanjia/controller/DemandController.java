package com.lyh.guguanjia.controller;


import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Demand;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("manager/demand")
public class DemandController {

	@Autowired
	DemandService service;

	@RequestMapping("selectAll")
	public PageInfo<Demand> selectall(@RequestParam Integer pageNum,
	                                  @RequestParam Integer pageSize){
		return service.selectPage(pageNum,pageSize);
	}

	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/demand/update.html");
	}
}
