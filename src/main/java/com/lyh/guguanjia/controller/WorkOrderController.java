package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysOffice;
import com.lyh.guguanjia.service.SysOfficeService;
import com.lyh.guguanjia.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("manager/work/admin")
public class WorkOrderController {



	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	SysOfficeService sysOfficeService;


	@RequestMapping("index")
	public ModelAndView toindex(){
		return new ModelAndView("/work/admin/index.html");
	}

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectall(@PathVariable int pageNum,
	                        @PathVariable int pageSize,
	                        @RequestParam Map<String,String> workCondition){
		return new Result(workOrderService.selectpage(pageNum,pageSize,workCondition));
	}


	@RequestMapping("allOffice")
	public Result alloffice(){
		SysOffice sysOffice = new SysOffice();
		sysOffice.setDelFlag("0");
		return new Result(sysOfficeService.select(sysOffice));
	}

	@RequestMapping("todetail")
	public ModelAndView toDetail(){
		return new ModelAndView("/work/detail.html");
	}
	@RequestMapping("alldetail/{id}")
	public Map<String,Object> allDetail(@PathVariable int id){
		return workOrderService.selectdetail(id);
	}

}
