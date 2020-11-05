package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.service.SysRoleService;
import com.lyh.guguanjia.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
	@RequestMapping("selectDxuser")
	public Result selectdxuser(@RequestParam Map<String,String> map){
		return new Result(sysUserService.selectbyunrole(map));
	}
	@RequestMapping("insertBatch")
	public Result insertbatch( Long roleId, Long[] dxIds){
		return new Result(true,"添加成功",sysRoleService.insertBatch(roleId,dxIds));
	}
	@RequestMapping("delBatch")
	public Result delbatch( Long roleId, Long[] yxIds){
		return new Result(true,"删除成功",sysRoleService.delBatch(roleId,yxIds));
	}
}
