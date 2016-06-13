package com.eastpro.dao;

import com.eastpro.entity.SysUser;

public interface SysUserDAO extends IBaseDao{
    public SysUser findUserByName(String name);
}
