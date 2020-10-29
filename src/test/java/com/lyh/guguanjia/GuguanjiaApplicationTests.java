package com.lyh.guguanjia;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.*;
import com.lyh.guguanjia.mapper.AppVersionMapper;
import com.lyh.guguanjia.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GuguanjiaApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	AppVersionMapper mapper;
	
	@Autowired
	AppVersionService service;

	@Autowired
	QualificationService qualificationService;

	@Autowired
	ExamineService examineService;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	StatuteService statuteService;

	@Test
	void contextLoads() throws SQLException {
		System.out.println(dataSource);
		System.out.println(dataSource.getConnection());

	}

	@Test
	public void testmapper01(){
		AppVersion appVersion = mapper.selectByPrimaryKey(1L);
		System.out.println(appVersion.getDownPath());

	}

	@Test
	public void testservice01(){
		AppVersion appVersion = service.selectByPrimaryKey(1L);
		System.out.println(appVersion);
	}

	@Test
	public void testservice02(){
		QualificationCondition qualificationCondition = new QualificationCondition();
		PageInfo<Qualification> qualificationPageInfo = qualificationService.selectPage(1, 3, qualificationCondition);
		List<Qualification> list = qualificationPageInfo.getList();
		for ( Qualification qualification : list ) {
			System.out.println(qualification);
		}
	}
	@Test
	public void testservice03(){
		ExamineCondition examineCondition = new ExamineCondition();
		examineCondition.setOfficeid("56");
		PageInfo<Examine> s = examineService.selectpage(1, 3, examineCondition);
		for ( Examine examine : s.getList() ) {
			System.out.println(examine);
		}
	}
	@Test
	public void testservice04(){
		Map<String,String> hashMap = new HashMap();
		hashMap.put("officeid","55");
		PageInfo<WorkOrder> selectpage = workOrderService.selectpage(1, 3, hashMap);
		for ( WorkOrder workOrder : selectpage.getList() ) {
			System.out.println(workOrder);
		}
	}
	@Test
	public void testservice05(){
		Map<String, Object> selectdetail = workOrderService.selectdetail(5);
		selectdetail.forEach((key,value)->{
			System.out.println(key+"  "+value);
		});

	}
	@Test
	public void testservice06(){
		Statute statute = new Statute();
		statute.setType(1);
		PageInfo<Statute> selectpage = statuteService.selectpage(1, 3, statute);
		for ( Statute statute1 : selectpage.getList() ) {

			System.out.println(statute1);
		}
	}

}
