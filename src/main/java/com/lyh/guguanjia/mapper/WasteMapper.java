package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.Waste;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface WasteMapper extends Mapper<Waste> {

	@Select("select was.*,wast.`code` waste_type_code FROM office_waste ofw," +
			"waste was,waste_type wast " + "WHERE ofw.waste_id=was.id " +
			"and was.del_flag='0' " + "and was.parent_id=wast.id " + "and ofw" +
			".office_id=#{officeId}")
	 List<Waste> offwbyid(Long officeId);

}