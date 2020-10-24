package com.lyh.guguanjia.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("main")
@Controller
public class MainController {

	@RequestMapping("sidebar")
	public String sidebar(){
		return "/common/sidebar.html";
	}
	@RequestMapping("navbar")
	public String navbar(){
		return "/common/navbar.html";
	}
}
