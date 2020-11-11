package com.lyh.guguanjia.aspect;


import com.lyh.guguanjia.entity.SysLog;
import com.lyh.guguanjia.entity.SysUser;
import com.lyh.guguanjia.service.SysLogService;
import com.lyh.guguanjia.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
	
	@Autowired
	SysLogService sysLogService;

	@Autowired
	HttpServletRequest request;


	@Pointcut("within(com.lyh.guguanjia.service.impl.*Impl)")
	public void pointcut(){}

	@AfterReturning(pointcut = "pointcut()",returning = "obj")
	public Object afterReturning(JoinPoint joinpoint, Object obj){
		String className = joinpoint.getTarget().getClass().getSimpleName();
		if(!className.equals("SysLogServiceImpl")) {
			insert(null, joinpoint);
		}
		return obj;
	}


	@AfterThrowing(pointcut = "pointcut()",throwing = "e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		insert(e,joinPoint);
	}

	public void insert(Exception e, JoinPoint joinpoint){
		SysLog sysLog = new SysLog();
		sysLog.setType(e==null?"1":"2");
		if ( request!=null ){
			HttpSession session = request.getSession();
			Map<String,Object> usermsg = (Map<String, Object>)session.getAttribute("usermsg");
			if ( usermsg!=null ) {
				SysUser loginuser = (SysUser)usermsg.get("loginuser");
				if ( loginuser != null ) {
					sysLog.setCreateBy(loginuser.getName());
				}
			}
			sysLog.setCreateDate(new Date());
			sysLog.setRemoteAddr(IPUtils.getClientAddress(request));
			sysLog.setUserAgent(request.getHeader("User-Agent"));
			sysLog.setRequestUri(request.getRequestURI());
			sysLog.setMethod(request.getMethod());
			
		}
		//[参数1，类型:SysArea，值:{"updateBy":"2,超级管理员","id":1,"parentId":0,"icon":"fa fa-institution","parentIds":"0,","oldParentIds":"0,","name":"中国","remarks":"","code":"100000","type":"1","updateDate":1425191037467}]
		Object[] args = joinpoint.getArgs();
		if ( args!=null&&args.length>0 ){
			StringBuffer buffer = new StringBuffer("[参数");
			for ( int i = 0; i < args.length; i++ ) {
				buffer.append(i+1)
						.append("，类型:");
						if(args!=null){
							buffer.append(args[i].getClass().getSimpleName());
						}else {
							buffer.append("null");
						}
						buffer.append("，值:")
						.append(args[i])
						.append("],");

			}
			buffer.deleteCharAt(buffer.length()-1);
			sysLog.setParams(buffer.toString());
		}
		sysLog.setException(e==null?"":e.toString());
		sysLog.setDescription("记录日志。。。。");

		sysLogService.insertSelective(sysLog);
	}


}
