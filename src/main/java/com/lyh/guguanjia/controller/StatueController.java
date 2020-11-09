package com.lyh.guguanjia.controller;

import com.baidu.ueditor.ActionEnter;
import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.Statute;
import com.lyh.guguanjia.service.StatuteService;
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
@RequestMapping("manager/statute")
public class StatueController {

	@Autowired
	StatuteService statuteService;

	@Autowired
	FTPUtil ftpUtil;

	@Value("${staticpath}")
	String staticPath;

	@RequestMapping("index")
	public ModelAndView toindex(){
		return new ModelAndView("/statute/index.html");
	}


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


	@RequestMapping(value = "staUpdate",method = RequestMethod.PUT)
	public Result staupdate(@RequestBody Statute statute){
		statute.setUpdateDate(new Date());
		int i = statuteService.updateByPrimaryKeySelective(statute);
		Result result = new Result();
		if ( i>0 ){
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
			File file = new File("D:\\tst\\a\\" + originalFilename);

			try {
				upfile.transferTo(file);
			FTPClient ftp = ftpUtil.getFTPClient("192.168.1.125", 21, "user","user");
			ftpUtil.uploadFile(ftp, file.getAbsolutePath(), "/uploads");
			ftpUtil.closeFTP(ftp);
			exec = new JSONObject(resultMap("SUCCESS",originalFilename,upfile.getSize(),originalFilename,type,staticPath+originalFilename)).toString();
			} catch (IOException e) {
				e.printStackTrace();
				exec = new JSONObject(resultMap("FAIL",null,0,null,null,null)).toString();
			}
		}
		return exec;
	}
	//{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg",
	// "type": ".jpg","url": "/1535961757878095151.jpg"}

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
