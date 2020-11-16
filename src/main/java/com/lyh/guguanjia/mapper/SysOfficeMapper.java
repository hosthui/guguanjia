package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.SysOffice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysOfficeMapper extends Mapper<SysOffice> {


	@SelectProvider(type = SysOfficeSqlProvider.class,method = "selectSqlPage")
	 List<SysOffice> selectpage(String conName);

	@Delete("delete from office_waste WHERE office_id =#{oid}")
	int delofficewastebyid(Long oid);

	@InsertProvider(type = SysOfficeSqlProvider.class,method = "insertBatch")
	int insertbatch(@Param("oid") Long oid, @Param("wids") List<Integer> wids);

}