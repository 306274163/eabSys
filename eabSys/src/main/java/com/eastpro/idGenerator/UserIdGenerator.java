package com.eastpro.idGenerator;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class UserIdGenerator implements IdentifierGenerator {
	private static final Logger logger = LoggerFactory.getLogger(UserIdGenerator.class);
  public Serializable generate(SessionImplementor si, Object entity) {
//	  SysUser myEntity = (SysUser)entity;
//	  long currentTimeMillis = System.currentTimeMillis();
//	  String userPrimaryId = myEntity.getId();
//	  if(userPrimaryId==null || "".equals(userPrimaryId)){
//		  userPrimaryId = String.valueOf(currentTimeMillis);
//	  }
//	  logger.info("the new user primary id is ["+userPrimaryId+"]");
//	  return userPrimaryId;
      return "";
  }
}