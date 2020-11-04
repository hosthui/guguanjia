package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.SysRoleService;
import com.lyh.guguanjia.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("manager/role")
public class SysRoleController {

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysUserService sysUserService;

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectall(@PathVariable int pageNum,
	                        @PathVariable int pageSize,
	                        @RequestParam Map<String,String> sysRoleCondition){
		return new Result(sysRoleService.selectPage(pageNum,pageSize,
				sysRoleCondition));
	}

	@RequestMapping("toallocation")
	public ModelAndView toallocation(){
		return new ModelAndView("/role/role-user.html");
	}

	@RequestMapping("selectYxuser")
	public Result selectyxuser(@RequestParam int id){
		return new Result(sysUserService.selectbyrole(id));
	}
}
