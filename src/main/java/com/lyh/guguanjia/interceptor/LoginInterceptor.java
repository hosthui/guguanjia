package com.lyh.guguanjia.interceptor;

import com.lyh.guguanjia.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        SysUser loginuser = (SysUser) session.getAttribute("loginuser");
        if (loginuser==null) {
            response.sendRedirect(request.getContextPath()+"/notlogin.html");
            return false;
        }
        return true;
    }
}
