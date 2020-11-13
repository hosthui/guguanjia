package com.lyh.guguanjia.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

	@RequestMapping
	public ModelAndView toOffice(){
		return new ModelAndView("/office/office.html");
	}
}
