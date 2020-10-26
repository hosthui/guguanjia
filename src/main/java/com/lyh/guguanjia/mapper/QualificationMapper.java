package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.Qualification;
import com.lyh.guguanjia.entity.QualificationCondition;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface QualificationMapper extends Mapper<Qualification> {
	@SelectProvider(type = QualificationSqlProvider.class,method = "selectSqlPage")
	List<Qualification> selectPage(QualificationCondition qualificationCondition);

}