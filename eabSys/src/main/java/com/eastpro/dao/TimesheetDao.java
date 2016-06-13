package com.eastpro.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.eastpro.entity.SysLookEntity;
import com.eastpro.entity.SysProjectEntity;
import com.eastpro.entity.SysTaskCodeEntity;
import com.eastpro.entity.SysTaskGrp;
import com.eastpro.entity.TimesheetEntity;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("serial")
@Repository
public class TimesheetDao extends BaseDaoImpl  {
	
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<TimesheetEntity> findAll(TimesheetEntity entity,Date startDate,Date endDate) {
      	StringBuffer  sql = new StringBuffer(200);
    	sql.delete(0, sql.length());
    	sql.append(" select STAFF_NAME ,A.SEQ AS SEQ ,A.CREATE_BY AS CREATE_BY,A.CREATE_DATE AS CREATE_DATE,C.PROJECT_NAME AS PROJECT,D.LOOKUP_VAL AS STAGE,B.TASK_NAME AS WR_NUMBER ,TASK_COMMENT  ,TASK_HOURS");
    	sql.append(",TASK_DATE,A.UPD_BY AS UPD_BY,A.UPD_DATE AS UPD_DATE from SYS_TIMESHEET A  left join SYS_TASK_CODE B ON A.WR_NUMBER=B.TASK_CODE " );
    	sql.append(" left join SYS_PROJECT C ON A.PROJECT=C.PROJECT_CODE ");
      	sql.append(" left join SYS_LOOKUP D ON A.STAGE=D.SEQ AND D.LOOKUP_KEY='TIME_SHEET' ");
    	sql.append(" WHERE 0=0 ");  
	    if(entity.getStaffName()!=null&&!"".equals(entity.getStaffName())){
           // criteria.add(Restrictions.eq("staffName", entity.getStaffName()));
	    	sql.append(" and STAFF_NAME= ?");
        }    
        if(entity.getTask_date()!=null){
        	sql.append(" and TASK_DATE= ?");
        }
        if(entity.getProject()!=null&&!"".equals(entity.getProject())){
        	sql.append(" and PROJECT= ?");
        }
        if(entity.getWrNumber()!=null&&!"".equals(entity.getWrNumber())){
        	sql.append(" and A.WR_NUMBER= ?");
        }
        if(entity.getSeq()!=null&&!"".equals(entity.getSeq())){
        	sql.append(" and A.SEQ= ? ");
        }
        if(startDate!=null){
        	sql.append(" and TASK_DATE >= ?");
        }
        if(endDate!=null){
        	sql.append(" and TASK_DATE <= ?");
        }
        sql.append(" ORDER BY TASK_DATE ");
        int praramNum=0;
        SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());  
        if(entity.getStaffName()!=null&&!"".equals(entity.getStaffName())){
            // criteria.add(Restrictions.eq("staffName", entity.getStaffName()));
        	query.setParameter(praramNum++, entity.getStaffName());
         }    
         if(entity.getTask_date()!=null){
        	 query.setParameter(praramNum++, entity.getTask_date());
         }
         if(entity.getProject()!=null&&!"".equals(entity.getProject())){
        	 query.setParameter(praramNum++, entity.getProject());
         }
         if(entity.getWrNumber()!=null&&!"".equals(entity.getWrNumber())){
        	 query.setParameter(praramNum++, entity.getWrNumber());
         }
         if(entity.getSeq()!=null&&!"".equals(entity.getSeq())){
        	 query.setParameter(praramNum++, entity.getSeq());
         }
         if(startDate!=null){
        	 query.setDate(praramNum++, startDate);        	
         }
         if(endDate!=null){
        	 query.setDate(praramNum++, endDate);
         }
	    query.addEntity(TimesheetEntity.class); 
	  
	    return query.list();  

      //  return (List<TimesheetEntity>) criteria.list();
    }
    
    @Transactional(rollbackFor={Exception.class})
     public String save(TimesheetEntity entity) {
    	   String returnSeq = entity.getSeq();
           if(entity.getSeq()==null||"".equals(entity.getSeq())){    	
    		  SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.seq) as timesheetSeq from SYS_TIMESHEET a WHERE STAFF_NAME= ? " );
    		  query.setParameter(0, entity.getStaffName());
    		  Object c=  query.addScalar( "timesheetSeq" , Hibernate.LONG).uniqueResult();
    		  long seq =0;
    		  if(c!=null){
    			  seq =  Long.parseLong(String.valueOf(c));
    		  }	
    		  
    		//  Long seq = (Long) getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.seq) as timesheetSeq from SYS_TIMESHEET a " ).addScalar( "timesheetSeq" , Hibernate.LONG).uniqueResult();
    		  seq++;    		
    		  entity.setSeq(String.valueOf(seq));
    		  entity.setCreateBy(entity.getStaffName());
    		  entity.setCreateDate(new Date());
    		  entity.setUpdBy(entity.getStaffName());
    		  entity.setUpdDate(new Date());
    		  returnSeq = String.valueOf(seq);
    	  }else{
        	  entity.setUpdBy(entity.getStaffName());
    		  entity.setUpdDate(new Date());
    	  }
    	   super.save(entity);    	
       return returnSeq ;   
    }
    
 
 
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysProjectEntity> findAllProjectList(String curProject){
   	 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SysProjectEntity.class); 
    // Criteria.add(Restrictions.between("logDate",startDate,endDate));
   	 if(curProject!=null&&!"".equals(curProject)){
   		 criteria.add(Restrictions.eq("projectCode",curProject));
   	 }
        criteria.addOrder(Order.asc("projectName"));
        return (List<SysProjectEntity>) criteria.list();
   }
    
    public List<SysTaskCodeEntity> findAllTaskCodeList(SysTaskCodeEntity entity,Date inDate){
    	StringBuffer  sql = new StringBuffer(200);
    	sql.delete(0, sql.length());
    	sql.append(" SELECT A.TASK_CODE AS TASK_CODE,A.TASK_NAME AS TASK_NAME,B.PROJECT_NAME as PROJECT_CODE,A.STATUS as STATUS,A.START_DATE AS START_DATE,A.END_DATE AS END_DATE");
    	sql.append(",A.CREATE_BY AS CREATE_BY,A.CREATE_DATE AS CREATE_DATE,A.UPD_BY AS UPD_BY,A.UPD_DATE AS UPD_DATE");
    	sql.append(" FROM SYS_TASK_CODE A left join SYS_PROJECT B ON  A.PROJECT_CODE =B.PROJECT_CODE  WHERE 1=1 ");  
	    if(entity.getProjectCode()!=null&&!"".equals(entity.getProjectCode())){
           // criteria.add(Restrictions.eq("staffName", entity.getStaffName()));
	    	sql.append(" and A.PROJECT_CODE= ?");
        } 
	    if(entity.getTaskCode()!=null&&!"".equals(entity.getTaskCode())){
        	sql.append(" and A.TASK_CODE= ? ");
        }      
	    if(inDate!=null){
	    	sql.append(" and A.START_DATE<= ? ");
	    }
	    if(inDate!=null){
	    	sql.append(" and A.END_DATE >= ? ");
	    }
        sql.append(" ORDER BY UPD_DATE ");
        int praramNum=0;
        SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());  
        if(entity.getProjectCode()!=null&&!"".equals(entity.getProjectCode())){
            // criteria.add(Restrictions.eq("staffName", entity.getStaffName()));
        	query.setParameter(praramNum++, entity.getProjectCode());
         }

        if(entity.getTaskCode()!=null&&!"".equals(entity.getTaskCode())){
        	 query.setParameter(praramNum++, entity.getTaskCode());
         } 
        if(inDate!=null){
        	 query.setParameter(praramNum++, inDate);
	    }
	    if(inDate!=null){
	    	 query.setParameter(praramNum++,inDate);
	    }
	    query.addEntity(SysTaskCodeEntity.class); 
	    return query.list();  
   }
    
    public List<SysTaskGrp> findAllTaskGrpList(SysTaskCodeEntity entity,Date inDate){  
    	List<SysTaskGrp> listGrp =null; 
    	String curProject =null;
    	if(entity.getProjectCode()!=null&&!"".equals(entity.getProjectCode())){
    		curProject = entity.getProjectCode();
    	}
        List<SysProjectEntity>  projectList = findAllProjectList(curProject);
    	if(projectList!=null&&projectList.size()>0){
    		for (SysProjectEntity e : projectList){
    			entity.setProjectCode(e.getProjectCode());
    			List<SysTaskCodeEntity> task = findAllTaskCodeList(entity,inDate);
    			if(task!=null&&task.size()>0){
    				if(listGrp==null){
    					listGrp= new ArrayList<SysTaskGrp>();
    				}
    				SysTaskGrp taskGrp = new SysTaskGrp();
    				taskGrp.setProjectCode(e.getProjectCode());
    				taskGrp.setProjectName(e.getProjectName());
    				taskGrp.setTasks(task);
    				listGrp.add(taskGrp);
    			}
    			
    		}
    		
    	}
    	entity.setProjectCode(curProject);
	    return listGrp;  

      //  return (List<TimesheetEntity>) criteria.list();
    
   }
    
    
    
    
    @Transactional(rollbackFor={Exception.class})
    public String saveProject(SysProjectEntity entity,String loginName) {
   	      String returnSeq = entity.getProjectCode();
          if(entity.getProjectCode()==null||"".equals(entity.getProjectCode())){    	
   		  SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.PROJECT_CODE) as taskSeq from SYS_PROJECT a  " );   		
   		  Object c=  query.addScalar( "taskSeq" , Hibernate.LONG).uniqueResult();
   		  long seq =0;
   		  if(c!=null){
   			  seq =  Long.parseLong(String.valueOf(c));
   		  }	   		  
   		//  Long seq = (Long) getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.seq) as timesheetSeq from SYS_TIMESHEET a " ).addScalar( "timesheetSeq" , Hibernate.LONG).uniqueResult();  		 
   		  seq++;    		
   		  entity.setProjectCode(String.valueOf(seq));
		  entity.setCreateBy(loginName);
		  entity.setCreateDate(new Date());		
		  returnSeq = String.valueOf(seq);
	  }
   	   super.save(entity);    	
      return returnSeq ;   
   }
    
    
    @Transactional(rollbackFor={Exception.class})
    public String saveTask(SysTaskCodeEntity entity,String loginName) {
   	      String returnSeq = entity.getTaskCode();    
          if(entity.getTaskCode()==null||"".equals(entity.getTaskCode())){    	
   		  SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.TASK_CODE) as taskSeq from SYS_TASK_CODE a  " );   		
   		  Object c=  query.addScalar( "taskSeq" , Hibernate.LONG).uniqueResult();
   		  long seq =0;
   		  if(c!=null){
   			  seq =  Long.parseLong(String.valueOf(c));
   		  }	   		  
   		//  Long seq = (Long) getSessionFactory().getCurrentSession().createSQLQuery( "select max(a.seq) as timesheetSeq from SYS_TIMESHEET a " ).addScalar( "timesheetSeq" , Hibernate.LONG).uniqueResult();  		 
   		  seq++;    		
   		  entity.setTaskCode(String.valueOf(seq));
		  entity.setCreateBy(loginName);
		  entity.setCreateDate(new Date());		
		  returnSeq = String.valueOf(seq);
	  }
   	   super.save(entity);    	
      return returnSeq ;   
   }
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public List<SysLookEntity> findLookList(String key){
   	 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SysLookEntity.class); 
    // Criteria.add(Restrictions.between("logDate",startDate,endDate));
   	 if(key!=null&&!"".equals(key)){
   		 criteria.add(Restrictions.eq("lookupKey",key));
   	 }
     criteria.addOrder(Order.asc("seq"));
      return (List<SysLookEntity>) criteria.list();
   }
    
}
