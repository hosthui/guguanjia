package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.Examine;
import com.lyh.guguanjia.entity.ExamineCondition;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface ExamineMapper extends Mapper<Examine> {

	@SelectProvider(type = ExamineSqlProvider.class,method = "selectSqlPage")
	 List<Examine> selectPage(ExamineCondition examineCondition);
}