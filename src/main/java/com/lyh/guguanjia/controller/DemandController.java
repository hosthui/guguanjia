package com.lyh.guguanjia.controller;


import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.Demand;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("manager/demand")
public class DemandController {

	@Autowired
	DemandService service;

	@RequestMapping("index")
	public ModelAndView toindex(){
		return new ModelAndView("/demand/index.html");
	}


	@RequestMapping("selectAll")
	public PageInfo<Demand> selectall(@RequestParam Integer pageNum,
	                                  @RequestParam Integer pageSize){
		return service.selectPage(pageNum,pageSize);
	}

	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/demand/update.html");
	}

	@RequestMapping("update")
	public Result update(@RequestBody Demand demand){
		return new Result(service.updateByPrimaryKeySelective(demand));
	}

	@RequestMapping("todetails")
	public ModelAndView todetails(){
		return new ModelAndView("/demand/detail.html");
	}
}
