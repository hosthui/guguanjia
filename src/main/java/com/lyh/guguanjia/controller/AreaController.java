package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.SysArea;
import com.lyh.guguanjia.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("manager/area")
public class AreaController {


	@Autowired
	SysAreaService sysAreaService;


	@RequestMapping("")
	public ModelAndView toindex(){
		return new ModelAndView("/area/area.html");
	}

	@RequestMapping("areaPage/{pageNum}/{pageSize}")
	public Result areapage(@PathVariable int pageNum,
	                       @PathVariable int pageSize, @RequestParam Map<String,String> params){
		return new Result(sysAreaService.selectpage(pageNum,pageSize,params));
	}

	@RequestMapping("areaAll")
	public Result areaall(){
		return new Result(sysAreaService.selectall());
	}
	@RequestMapping("toupdate")
	public ModelAndView toupdate(){
		return new ModelAndView("/area/save.html");
	}


	@RequestMapping(value = "doupdate",method = RequestMethod.PUT)
	public Result doupdate(@RequestBody SysArea sysArea){
		Date date = new Date();
		int i = sysAreaService.updateByPrimaryKeySelective(sysArea);
		Result result = new Result();
		if ( i>0 ){
			result.setSuccess(true);
			result.setMsg("操作成功");
		}
		return result;
	}


	@RequestMapping("download")
	public void download(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Disposition",
				"attachment;filename="+new String("区域信息.xlsx".getBytes(),
						"iso8859-1"));
		sysAreaService.download(response.getOutputStream());

	}


	@RequestMapping(value = "upload",method = RequestMethod.POST)
	public Result upload(MultipartFile file) throws IOException {
			sysAreaService.upload(file.getInputStream());
		Result result = new Result(null);
		result.setMsg("导入成功");
		return result;
	}

	@RequestMapping("toselect")
	public ModelAndView toselect(){
		return new ModelAndView("/area/select.html");
	}

	@RequestMapping("tomodules")
	public ModelAndView tomodules(){
		return new ModelAndView("/modules/font-awesome.html");
	}

}
