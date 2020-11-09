package com.lyh.guguanjia.controller;


import com.baomidou.kaptcha.Kaptcha;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysResource;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.service.SysResourceService;
import com.lyh.guguanjia.service.SysUserService;
import com.lyh.guguanjia.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("main")
@Controller
public class MainController {

	@Autowired
	private Kaptcha kaptcha;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysResourceService sysResourceService;


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
	                      HttpSession session) {
		String message = null;
		Map<String,Object> userMsg = new HashMap();
		if ( kaptcha.validate(code) ) {
			SysUser loginUser = new SysUser();
			loginUser.setUsername(username);
			loginUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password) + username));
			loginUser = sysUserService.selectOne(loginUser);
			if (loginUser!=null){
				List<SysResource> sysResources = sysResourceService.ResourcesbyUserid(loginUser.getId());
				loginUser.setPassword(null);
				userMsg.put("loginuser",loginUser);
				userMsg.put("userResources",sysResources);
				session.setAttribute("usermsg",userMsg);
				return  new Result(true,"登录成功",userMsg);
			}else {
				message = "用户名或密码错误";
			}
		}
		return new Result(false,message,null);
	}
	@RequestMapping("loginout")
	@ResponseBody
	public Result loginOut(HttpSession session){
		session.invalidate();
		return new Result(false,"注销成功",null);
	}
	@RequestMapping("loginin")
	public String loginIn(){
		return "/index.html";
	}
}
