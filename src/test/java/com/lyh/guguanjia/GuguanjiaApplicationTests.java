package com.lyh.guguanjia;

import com.lyh.guguanjia.entity.AppVersion;
import com.lyh.guguanjia.mapper.AppVersionMapper;
import com.lyh.guguanjia.service.AppVersionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class GuguanjiaApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	AppVersionMapper mapper;
	
	@Autowired
	AppVersionService service;

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

}
