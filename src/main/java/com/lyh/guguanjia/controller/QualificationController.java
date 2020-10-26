package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Qualification;
import com.lyh.guguanjia.entity.QualificationCondition;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.mapper.SysUserMapper;
import com.lyh.guguanjia.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

	@Autowired
	QualificationService service;
	@Autowired
	SysUserMapper userMapper;
	@Value("${staticpath}")
	String syspath;
	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectAll(@PathVariable int pageNum,@PathVariable int pageSize, QualificationCondition condition){
		return new Result(service.selectPage(pageNum,pageSize, condition));
	}

	@RequestMapping("toUpdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/qualification/update.html");
	}

	@RequestMapping("selectOne/{id}")
	public Result selectone(@PathVariable long id){
		Qualification qualification = service.selectByPrimaryKey(id);
		SysUser sysUser = userMapper.selectByPrimaryKey(qualification.getUploadUserId());
		qualification.setAddress(syspath+sysUser.getCompanyId()+"/"+qualification.getAddress());
		return new Result(qualification);
	}
}
