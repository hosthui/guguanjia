package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysLog;
import com.lyh.guguanjia.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("manager/syslog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping
    public ModelAndView toLog(){
        return new ModelAndView("/log/log.html");
    }
    @RequestMapping("logAll/{pageNum}/{pageSize}")
    public Result logall(@PathVariable int pageNum, @PathVariable int pageSize
		    , SysLog sysLog){
        return new Result(sysLogService.selectPage(pageNum,pageSize,sysLog));
    }

    @RequestMapping("todetail")
	public ModelAndView todetail(){
    	return new ModelAndView("/log/log-detail.html");
    }
}
