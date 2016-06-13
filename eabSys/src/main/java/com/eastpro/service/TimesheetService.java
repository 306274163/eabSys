package com.eastpro.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eastpro.dao.TimesheetDao;
import com.eastpro.entity.SearchTimesheetEntity;
import com.eastpro.entity.SysLookEntity;
import com.eastpro.entity.SysProjectEntity;
import com.eastpro.entity.SysTaskCodeEntity;
import com.eastpro.entity.SysTaskGrp;
import com.eastpro.entity.TimesheetEntity;
import com.eastpro.util.ExportExcel;
@Service
public class TimesheetService{
	@Autowired
    private TimesheetDao timesheetDao;
    public List<TimesheetEntity> findAll(TimesheetEntity entity){
    	return timesheetDao.findAll(entity,null,null);
    }
    public String save(TimesheetEntity entity){
        	return timesheetDao.save(entity);
    }
    @Transactional(rollbackFor={Exception.class})
    public void delete(TimesheetEntity entity){
    	 timesheetDao.delete(entity);
}   
  
    public List<SysProjectEntity> findAllProjectList(){
    	return timesheetDao.findAllProjectList(null);
    }   
    
    /**
     * add task Grp
     * @param entity
     * @param loginName
     * @return
     */
    public String saveProject(SysProjectEntity entity,String loginName){
    	return timesheetDao.saveProject(entity, loginName);
}
    /**
     * add task code
     * @param entity
     * @param loginName
     * @return
     */
    public String saveTask(SysTaskCodeEntity entity,String loginName){
    	return timesheetDao.saveTask(entity, loginName);
}
    //search  task Code List for task grp 
    public List<SysTaskGrp> findAllTaskGrpList( SysTaskCodeEntity task,Date indate){
    	return timesheetDao.findAllTaskGrpList(task,indate);
    } 
  //search  task Code List for task grp 
    public List<SysTaskGrp> findAllTaskGrpList( SysTaskCodeEntity task){
    	return timesheetDao.findAllTaskGrpList(task,null);
    } 
    //delete  task Code 
    @Transactional(rollbackFor={Exception.class})
    public void delete(SysTaskCodeEntity entity){
    	 timesheetDao.delete(entity);
}   
    
    public  void getTimesheetExcel(HttpServletResponse response, SearchTimesheetEntity entity) throws IOException{
    	TimesheetEntity tiemEntity = new TimesheetEntity();
    	tiemEntity.setProject(entity.getDlProjec());
    	tiemEntity.setWrNumber(entity.getDlWrNumber());
    	tiemEntity.setStaffName(entity.getDlStaffName());    
    	List<TimesheetEntity> timesheetList =  timesheetDao.findAll(tiemEntity,entity.getDlTsStart(),entity.getDlTsEnd());    	
    	 //title 
    	String title ="TimeSheet";
    	// file name
	    	String fileName = "timesheet_"+System.currentTimeMillis()+".xlsx";
	    	
	    	//运行时间和查询
	    	List<String> subTitle =new ArrayList<String>();		    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM:ss");  	    	
	    	subTitle.add("Run Time : " +sdf.format(new Date()));	    	
	    	if(entity.getDlTsStart()!=null||entity.getDlTsEnd()!=null){
	    		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy"); 
	    		String start ="-";
	    		String end = "-";
	    		if(entity.getDlTsStart()!=null){
	    			start =sdf1.format(entity.getDlTsStart());
	    		}
	    		if(entity.getDlTsEnd()!=null){
	    			end= sdf1.format(entity.getDlTsEnd());
	    		}
    		   sdf1.format(entity.getDlTsStart()); 
    		   subTitle.add("Task Range : " +start  +" to " +end);
		   
	    	}
	    	if(entity.getDlProjec()!=null&&!"".equals(entity.getDlProjec())){
	    		subTitle.add("Project : "  +timesheetDao.findAllProjectList(entity.getDlProjec()).get(0).getProjectName());
	    	}
	    	if(entity.getDlWrNumber()!=null&&!"".equals(entity.getDlWrNumber())){
	    		SysTaskCodeEntity taskEntity = new SysTaskCodeEntity();
	    		taskEntity.setTaskCode(entity.getDlWrNumber());
	    		taskEntity.setProjectCode(entity.getDlProjec());	    		
	    		subTitle.add("WR# : "  +timesheetDao.findAllTaskCodeList(taskEntity,null).get(0).getTaskName());
	    	}
	    	if(entity.getDlStaffName()!=null&&!"".equals(entity.getDlStaffName())){
	    		  subTitle.add("Staff Name : "  +entity.getDlStaffName());
	    	}
	    	// add head
	    	List<String> headerList =new ArrayList<String>();
			headerList.add("DATE");
			headerList.add("PROJECT");
			headerList.add("WR#");
			headerList.add("STAFF NAME");
			headerList.add("HOURS");
			headerList.add("STAGE");
			headerList.add("COMMENT");		
	    	
	    	List<List<Map<String,Object>>> list =null;
	    	//add date
			if(timesheetList!=null&&timesheetList.size()>0){	    		
			    	list =new ArrayList<List<Map<String,Object>>>();			    	
			    	for (TimesheetEntity e : timesheetList){
			    		List<Map<String,Object>> rowList= new ArrayList<Map<String,Object>>();	    		
			    		
			    		Map<String,Object> data1 = new HashMap<String,Object>();
			    		data1.put("val", e.getTask_date());
			    		rowList.add(data1);
			    		
			    		Map<String,Object> data2 = new HashMap<String,Object>();
			    		data2.put("val", e.getProject());
			    		rowList.add(data2);
			    		
			    		
			    		Map<String,Object> data3 = new HashMap<String,Object>();
			    		data3.put("val", e.getWrNumber());
			    		rowList.add(data3);
			    		
			    		Map<String,Object> data4 = new HashMap<String,Object>();
			    		data4.put("val", e.getStaffName());
			    		rowList.add(data4);
			    		
			    		Map<String,Object> data5 = new HashMap<String,Object>();
			    		data5.put("val", e.getTaskHours());
			    		rowList.add(data5);
			    		
			    		Map<String,Object> data6 = new HashMap<String,Object>();
			    		data6.put("val", e.getStage());
			    		rowList.add(data6);
			    		
			    		Map<String,Object> data7 = new HashMap<String,Object>();
			    		data7.put("val", e.getTaskComment());
			    		rowList.add(data7);  
			    		 		
			    		list.add(rowList);	    		
			    	}			    	
			    	
		    	}
			new ExportExcel(title,subTitle, headerList).setDataList(list).write(response, fileName).dispose();  
    }
    public List<SysLookEntity> findLookList(String key){
    	return timesheetDao.findLookList(key);
    }   
    
   
}
