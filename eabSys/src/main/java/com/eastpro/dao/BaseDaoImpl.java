package com.eastpro.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class BaseDaoImpl implements IBaseDao, Serializable  
{     
    private static final long serialVersionUID = -2207456613819809706L;  

    private SessionFactory sessionFactory;  
      
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
      
    @Transactional 
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
      
    @Override 
    public void save(Object entity) {  
        getSessionFactory().getCurrentSession().save(entity);  
    }  
 
    @Override 
    public void update(Object entity) {  
        getSessionFactory().getCurrentSession().update(entity);  
    }  
      
    @Override 
    public void delete(Object entity) {  
        getSessionFactory().getCurrentSession().delete(entity);  
    }  
 
}