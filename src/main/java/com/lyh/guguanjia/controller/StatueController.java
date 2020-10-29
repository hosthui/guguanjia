package com.lyh.guguanjia.controller;

import com.baidu.ueditor.ActionEnter;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.Statute;
import com.lyh.guguanjia.service.StatuteService;
import com.lyh.guguanjia.util.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("manager/statute")
public class StatueController {

	@Autowired
	StatuteService statuteService;

	@Autowired
	FTPUtil ftpUtil;

	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/statute/update.html");
	}

	@RequestMapping("selectAll/{pageNum}/{pageSize}")
	public Result selectall(@PathVariable int pageNum,
	                        @PathVariable int pageSize,
	                        Statute statuteCondition){
		return new Result(statuteService.selectpage(pageNum,pageSize,statuteCondition));
	}
	@RequestMapping("doUeditor")
	public String doueditor(String action, HttpServletRequest request,
	                        MultipartFile upfile){
		String exec=null;
		if ( "config".equals(action) ){
			exec= new ActionEnter(request, request.getServletContext().getRealPath("/")).exec();
		}else if ( "uploadimage".equals(action) ){
			String originalFilename = upfile.getOriginalFilename();

		}
		return exec;
	}

}
