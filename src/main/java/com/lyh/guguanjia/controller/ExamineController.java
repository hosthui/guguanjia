package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.ExamineCondition;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.mapper.SysOfficeMapper;
import com.lyh.guguanjia.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager/examine")
public class ExamineController {

	@Autowired
	ExamineService examineService;

	@Autowired
	SysOfficeMapper mapper;

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectall(@PathVariable int pageNum,
	                        @PathVariable int pageSize, ExamineCondition examineCondition){
		return new Result(examineService.selectpage(pageNum,pageSize,examineCondition));
	}
	@RequestMapping("allOffice")
	public Result alloffice(){
		return new Result(mapper.selectAll());
	}



}
