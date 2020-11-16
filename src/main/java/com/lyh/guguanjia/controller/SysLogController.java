package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("manager/log")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping
    public ModelAndView toLog(){
        return new ModelAndView("/log/log.html");
    }
    @RequestMapping("logAll")
    public Result logall(){
        return new Result(sysLogService.selectAll());
    }
}
