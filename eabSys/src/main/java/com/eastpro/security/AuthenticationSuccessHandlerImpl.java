package com.eastpro.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 登录认证成功后会调用指定AuthenticationSuccessHandler的onAuthenticationSuccess方法
 * http default-target-url 配置不會起作用了
 * 這裏可以調用DB操作,獲得menu ,用戶信息等
 * reated by Administrator on 15-8-3.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()  
    			    .getAuthentication()  
    			    .getPrincipal();      	
        request.getSession().setAttribute("SESSION_LOGINNAME", userDetails.getUsername());      
        response.sendRedirect(request.getContextPath()+"/index.eab");
    }
}
