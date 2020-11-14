package com.lyh.guguanjia.controller;


import com.lyh.guguanjia.entity.Result;
import com.lyh.guguanjia.entity.Waste;
import com.lyh.guguanjia.service.SysOfficeService;
import com.lyh.guguanjia.service.WasteService;
import com.lyh.guguanjia.service.WasteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

	@Autowired
	SysOfficeService sysOfficeService;

	@Autowired
	WasteService wasteService;

	@Autowired
	WasteTypeService wasteTypeService;

	@RequestMapping
	public ModelAndView toOffice(){
		return new ModelAndView("/office/office.html");
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

}
