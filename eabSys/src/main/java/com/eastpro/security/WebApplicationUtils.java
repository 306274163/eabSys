package com.eastpro.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class WebApplicationUtils implements ApplicationContextAware,ServletContextAware{
	
	public static ApplicationContext applicationContext = null;
	public static ServletContext servletContext = null;


	public void setApplicationContext(ApplicationContext actx) throws BeansException {
		if(WebApplicationUtils.applicationContext==null){
			WebApplicationUtils.applicationContext = actx;
		}
		
	}

	public void setServletContext(ServletContext sctx) {
		if(WebApplicationUtils.servletContext==null){
			WebApplicationUtils.servletContext=sctx;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	
}