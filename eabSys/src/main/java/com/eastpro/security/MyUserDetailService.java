package com.eastpro.security;


import com.eastpro.entity.SysUser;
import com.eastpro.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class MyUserDetailService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    public SysUserService sysUserService;

    @Override  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.info("根据username加载用户信息");

        SysUser sysUser = sysUserService.findUserByName(username);//从DB中获得user对象
        if(sysUser!=null){
        	String password = sysUser.getPassword();//获得用户密码
        	
        	//设置登陆用户权限
        	Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(sysUser);
        	

            boolean enables = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            User user = new User(username, password, enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
            return user;
            
        }else{
        	throw new UsernameNotFoundException(username  +   "  不存在 " );
        }
    }
    // 取得用户的权限资源
    private Set<GrantedAuthority> obtionGrantedAuthorities(SysUser sysUser) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

        authSet.add(new SimpleGrantedAuthority("ROLE_LOGIN"));
        authSet.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authSet;
    }
}