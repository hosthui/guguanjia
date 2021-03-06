package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.ExamineCondition;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysOffice;
import com.lyh.guguanjia.service.ExamineService;
import com.lyh.guguanjia.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("manager/examine")
public class ExamineController {

	@Autowired
	ExamineService examineService;

	@Autowired
	SysOfficeService service;

	@RequestMapping("index")
	public ModelAndView toindex(){
		return new ModelAndView("/examine/index.html");
	}

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectall(@PathVariable int pageNum,
	                        @PathVariable int pageSize, ExamineCondition examineCondition){
		return new Result(examineService.selectpage(pageNum,pageSize,examineCondition));
	}
	@RequestMapping("allOffice")
	public Result alloffice(){
		SysOffice sysOffice = new SysOffice();
		sysOffice.setDelFlag("0");
		List<SysOffice> select = service.select(sysOffice);
		return new Result(select);
	}
}
