package com.lyh.guguanjia.interceptor;

import com.lyh.guguanjia.entity.SysResource;
import com.lyh.guguanjia.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Component
public class ResourceInterceptor implements HandlerInterceptor {


	@Autowired
	SysResourceService sysResourceService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		List<SysResource> allresorce = sysResourceService.allresorce();
		String requestURI = request.getRequestURI();
		requestURI=requestURI.replace(request.getContextPath()+"/","");

		boolean need=false;
		for ( SysResource sysResource : allresorce ) {
			if ( sysResource.getUrl().equals(requestURI) ){
				need=true;
				break;
			}
		}
		//usermsg
		if ( need ){
			Map<String,Object> usermsg = (Map<String, Object>)request.getSession().getAttribute(
					"usermsg");
			List<SysResource> userResources = (List<SysResource>)usermsg.get("userResources");
			for ( SysResource userResource : userResources ) {
				if ( userResource.getUrl().equals(requestURI) ){
					return true;
				}
			}
			response.sendRedirect(request.getContextPath()+"/notauth.html");
			return false;
		}


		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

}
