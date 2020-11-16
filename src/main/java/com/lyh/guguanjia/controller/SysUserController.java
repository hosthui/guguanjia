package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.service.SysUserService;
import com.lyh.guguanjia.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("manager/sysuser")
public class SysUserController {


	@Autowired
	SysUserService sysUserService;

	@RequestMapping
	public ModelAndView touser(){
		return new ModelAndView("/user/user.html");
	}

	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/user/user-save.html");
	}
	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectAll(@PathVariable int pageNum,
	                        @PathVariable int pageSize,
	                        @RequestParam Map<String,String> map){

		return new Result(sysUserService.selectPage(pageNum,pageSize,map));
	}
	@RequestMapping("doupdate")
	public Result doUpdate(@RequestBody  SysUser sysUser){
		return new Result(sysUserService.userUp(sysUser));
	}
	@RequestMapping("insert")
	public Result useradd(@RequestBody SysUser sysUser){
		sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(sysUser.getPassword()) + sysUser.getUsername()));
		sysUser.setCompanyId(sysUser.getOfficeId());
		return new Result(sysUserService.userAdd(sysUser));
	}

}
