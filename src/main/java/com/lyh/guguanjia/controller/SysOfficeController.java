package com.lyh.guguanjia.controller;


import com.baidu.ueditor.ActionEnter;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.Statute;
import com.lyh.guguanjia.entity.Waste;
import com.lyh.guguanjia.service.SysOfficeService;
import com.lyh.guguanjia.service.WasteService;
import com.lyh.guguanjia.service.WasteTypeService;
import com.lyh.guguanjia.util.FTPUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

	@Autowired
	SysOfficeService sysOfficeService;

	@Autowired
	WasteService wasteService;

	@Autowired
	WasteTypeService wasteTypeService;

	@Autowired
	FTPUtil ftpUtil;

	@Value("${staticpath}")
	String staticPath;

	@Value("${uploadpath}")
	String uploadPath;

	@Value("${ftphost}")
	String ftpHost;

	@Value("${ftpport}")
	int ftpPort;

	@Value("${ftpusername}")
	String ftpUserName;

	@Value("${ftppassword}")
	String ftpPassword;

	@Value("${ftppath}")
	String ftpPath;
	@RequestMapping
	public ModelAndView toOffice(){
		return new ModelAndView("/office/office.html");
	}

	@RequestMapping("toselect")
	public ModelAndView toSelect(){
		return new ModelAndView("/office/select.html");
	}

	@RequestMapping("officePage/{pageNum}/{pageSize}")
	public Result officepage(@PathVariable int pageNum,
	                         @PathVariable int pageSize,String conName){
		return  new Result(sysOfficeService.selectpage(pageNum,pageSize,conName));
	}

	@RequestMapping("toupdate")
	public ModelAndView toUpdate(){
		return new ModelAndView("/office/save.html");
	}

	@RequestMapping("allWasteType")
	public Result allwastetype(){
		return new Result(wasteTypeService.selectAll());
	}
	@RequestMapping("allWaste")
	public Result allwaste(Waste waste){
		return new Result(wasteService.select(waste));
	}

	@RequestMapping("companyw")
	public Result companyW(Long officeId){
		return new Result(wasteService.offwbyid(officeId));
	}

	@RequestMapping(value = "doupdate",method = RequestMethod.PUT)
	public Result doUpdate(@RequestBody Map<String,Object> upmsg){
		int i = sysOfficeService.officeupdate(upmsg);
		Result result = new Result();
		if ( i==1 ){
			result.setSuccess(true);
		}
		return result;
	}

	@RequestMapping("doUeditor")
	public String doueditor(String action, HttpServletRequest request,
							MultipartFile upfile){
		String exec=null;
		if ( "config".equals(action) ){
			exec= new ActionEnter(request, request.getServletContext().getRealPath("/")).exec();
		}else if ( "uploadimage".equals(action) ){
			String originalFilename = upfile.getOriginalFilename();
			String type = originalFilename.substring(originalFilename.lastIndexOf("."));
			File file = new File(uploadPath + originalFilename);
			try {
				upfile.transferTo(file);
				FTPClient ftp = ftpUtil.getFTPClient(ftpHost, ftpPort,
						ftpUserName,ftpPassword);
				ftpUtil.uploadFile(ftp, file.getAbsolutePath(), ftpPath);
				ftpUtil.closeFTP(ftp);
				exec = new JSONObject(resultMap("SUCCESS",originalFilename,upfile.getSize(),originalFilename,type,staticPath+originalFilename)).toString();
			} catch (IOException e) {
				e.printStackTrace();
				exec = new JSONObject(resultMap("FAIL",null,0,null,null,null)).toString();
			}
		}
		return exec;
	}
	private Map<String,Object> resultMap(String state, String original, long size, String title, String type, String url){
		Map<String ,Object> result = new HashMap<>();
		result.put("state",state);
		result.put("original",original);
		result.put("size",size);
		result.put("title",title);
		result.put("type",type);
		result.put("url", url);
		return result;
	}
}
