package com.lyh.guguanjia.mapper;

import com.lyh.guguanjia.entity.WorkOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@org.apache.ibatis.annotations.Mapper
public interface WorkOrderMapper extends Mapper<WorkOrder> {

	@SelectProvider(type = WorkOrderSqlProvider.class,method = "selectSqlPage")
	List<WorkOrder> selectpage(Map<String, String> workCondition);

	
	@Select(" SELECT  " +
			" wo.id,  " + 
			" wo.`code`,  " +
			" wo.create_user_id,  " + 
			" wo.transport_user_id,  " + 
			" wo.recipient_user_id,  " + 
			" wo.`status`,  " + 
			" wo.create_date,  " + 
			" wo.update_date,  " + 
			" wo.del_flag,  " + 
			" wo.create_by,  " + 
			" sc.`name` create_user_name,  " + 
			" st.`name` transport_user_name,  " + 
			" sr.`name` recipient_user_name,  " + 
			" coff.`name` create_office_name,  " + 
			" toff.`name` transport_office_name,  " + 
			" roff.`name` recipient_office_name,  " + 
			" sc.phone create_user_phone,  " + 
			" st.phone transport_user_phone,  " +
			" sr.phone recipient_user_phone  " +
			" FROM  " + " work_order wo  " +
			" LEFT JOIN sys_user sc ON wo.create_user_id = sc.id  " +
			" LEFT JOIN sys_user st ON wo.transport_user_id = st.id  " +
			" LEFT JOIN sys_user sr ON wo.recipient_user_id = sr.id  " +
			" LEFT JOIN sys_office coff ON sc.company_id = coff.id  " +
			" LEFT JOIN sys_office toff ON st.company_id = toff.id  " +
			" LEFT JOIN sys_office roff ON sr.company_id = roff.id  " +
			" WHERE wo.del_flag=0 and wo.id=#{id}")
		Map<String,Object> selectbyId(Integer id);


	@Select(" SELECT de.*,wt.`name` waste_type_name,wt.`code` " +
			" waste_type_code,wa.`code` waste_code FROM detail de,waste_type " +
			" wt,waste wa " +
			" WHERE de.del_flag=0 " +
			" and de.work_order_id=#{id} " +
			" and de.waste_type_id=wt.id " +
			" and de.waste_id=wa.id")
	List<Map<String,Object>> selectdetailbyId(Integer id);



	@Select(" SELECT tr.*,su.`name`,su.phone FROM transfer tr,sys_user su " + 
			" WHERE tr.del_flag=0 " +
			" and tr.oprate_user_id=su.id " +
			" and tr.work_order_id=4 ORDER BY tr.create_date DESC")
	List<Map<String,Object>> selecttransferbyId(Integer id);
}