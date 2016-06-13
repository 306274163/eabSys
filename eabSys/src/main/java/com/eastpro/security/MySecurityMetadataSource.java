package com.eastpro.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;


public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
	private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MySecurityMetadataSource() {
		logger.info("MySecurityMetadataSource---------------------------");
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		
		if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            logger.info("yadong.shao test");

            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            ConfigAttribute userCA =  new SecurityConfig("ROLE_USER");
            atts.add(userCA);
            resourceMap.put("/index.eab", atts);

        } 
		

        
		logger.info("===>run loadResourceDefine function");
		

		
	}

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		logger.info("-----> 获得资源:getAttributes()");

        String requestUrl = ((FilterInvocation) object).getRequestUrl();  

        if (resourceMap == null) {  
            loadResourceDefine();  
        }  

        //动态资源管理  
//        if(resourceMap.get(requestUrl) == null) {
//            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
//            List<SysResources> resource = sysResourcesService.searchSysResourcesByUrl(requestUrl);
//            if(resource == null) {
//                return null;
//            }
//            for (SysResources role : resource) {
//                List<SysAuthResources> sysAuthResources = role.getSysAuthResources();
//                for (SysAuthResources ar : sysAuthResources) {
//                    ConfigAttribute configAttribute = new SecurityConfig(ar.getAuthority_id());//获得权限
//                    configAttributes.add(configAttribute);
//                    resourceMap.put(role.getResource_string(), configAttributes);
//                }
//            }
//        }


          
        return resourceMap.get(requestUrl);
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}