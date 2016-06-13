package com.eastpro.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class MyAccessDecisionManager implements AccessDecisionManager
{
	private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,InsufficientAuthenticationException{

		
        if (configAttributes == null) {  
            return;  
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();  
        while (ite.hasNext()) {  
            ConfigAttribute ca = ite.next();

            String needRole = ((SecurityConfig) ca).getAttribute();

            for (GrantedAuthority ga : authentication.getAuthorities()) {
            	logger.info("URL有的权限::::"+ga.getAuthority());
                if (needRole.equals(ga.getAuthority())) {
                	logger.info("===>有访问权限,返回");
                    return;  
                }  
            }  
        }  
        throw new AccessDeniedException("没有访问权限.");
	}

	@Override
	public boolean supports(ConfigAttribute attribute)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz)
	{
		// TODO Auto-generated method stub
		return true;
	}

}
