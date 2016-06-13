package com.eastpro.dao;

import com.eastpro.exception.ApplyException;

public interface IBaseDao
{  
	/**  
     * save  
     *   
     * @param  entity  
     */ 
    public void save(Object entity) throws ApplyException;
      
    /**  
     * update  
     *   
     * @param  entity  
     */ 
    public void update(Object entity) throws ApplyException;  
      
    /**  
     * delete  
     *   
     * @param  entity  
     */ 
    public void delete(Object entity) throws ApplyException; 
}