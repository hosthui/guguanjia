package com.lyh.guguanjia.interceptor;

import com.lyh.guguanjia.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
	    Map<String,Object> usermsg = (Map<String, Object>)session.getAttribute("usermsg");
	   SysUser loginuser= (SysUser)usermsg.get("loginuser");
	    if (loginuser==null) {
            response.sendRedirect(request.getContextPath()+"/notlogin.html");
            return false;
        }
        return true;
    }
}
