package com.lyh.guguanjia.controller;


import com.baomidou.kaptcha.Kaptcha;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("main")
@Controller
public class MainController {

	@Autowired
	private Kaptcha kaptcha;


	@RequestMapping("sidebar")
	public String sidebar(){
		return "/common/sidebar.html";
	}
	@RequestMapping("navbar")
	public String navbar(){
		return "/common/navbar.html";
	}

	/**
	 * 用户登录
	 *
	 */
	@RequestMapping("login")
	@ResponseBody
	public Result loginIn(String username, String password, String code,
	                      HttpSession session){
		String message=null;
		if ( kaptcha.validate(code) ){
			SysUser loginUser = new SysUser();
			loginUser.setUsername(username);
			loginUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+username));
		}

		return new Result();
	}
}
