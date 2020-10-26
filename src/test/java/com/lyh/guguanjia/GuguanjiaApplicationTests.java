package com.lyh.guguanjia;

import com.github.pagehelper.PageInfo;
import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.entity.Qualification;
import com.lyh.guguanjia.entity.QualificationCondition;
import com.lyh.guguanjia.mapper.AppVersionMapper;
import com.lyh.guguanjia.service.AppVersionService;
import com.lyh.guguanjia.service.QualificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

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

}
