package com.eastpro.service;

import com.eastpro.dao.SysUserDAO;
import com.eastpro.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    public SysUser findUserByName(String name) {
        return sysUserDAO.findUserByName(name);
    }
}
