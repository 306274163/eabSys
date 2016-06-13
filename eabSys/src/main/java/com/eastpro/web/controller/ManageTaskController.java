/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.eastpro.web.controller;




import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastpro.entity.SearchTimesheetEntity;
import com.eastpro.entity.SysProjectEntity;
import com.eastpro.entity.SysTaskCodeEntity;
import com.eastpro.entity.SysTaskGrp;
import com.eastpro.service.TimesheetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class ManageTaskController {
    private static final Logger logger = LoggerFactory.getLogger(ManageTaskController.class);
	@Autowired
	private TimesheetService timesheetService;
	
	@RequestMapping(value = "/manageTask.eab")
	public String toManageTask(HttpServletRequest request, HttpServletResponse response,SysTaskCodeEntity task, Model model) {
		//String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");		
		List<SysTaskGrp>  sysTaskGrpList = timesheetService.findAllTaskGrpList(task);		
		List<SysProjectEntity>  projectList = timesheetService.findAllProjectList();		
		model.addAttribute("task",task);	
		model.addAttribute("grpList",sysTaskGrpList);	
		model.addAttribute("proList",projectList);
        return "timesheet/manageTimesheetList";
	}
	
	@RequestMapping(value = "/addProject.eab", method = RequestMethod.POST)
    @ResponseBody
      public Map<String, Object> addProject(SysProjectEntity proEntity,HttpServletRequest request, Model model) {	
		String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");	
		Map<String, Object> modelMap = new HashMap<String, Object>();   
		proEntity.setUpdBy(loginName);
		proEntity.setUpdDate(new Date());			
		String seq = timesheetService.saveProject(proEntity,loginName);
		if(seq!=null){
			modelMap.put("result", "1");
			List<SysProjectEntity>  sysGrpList = timesheetService.findAllProjectList();		
			modelMap.put("grp", sysGrpList);	
		}else{
			modelMap.put("result", "0");
		}	
	    return modelMap;
	  }
	
	@RequestMapping(value = "/addTaskCode.eab", method = RequestMethod.POST)
    @ResponseBody
      public Map<String, String> addTaskCode(SysTaskCodeEntity task,HttpServletRequest request, Model model) {
		String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");
		Map<String, String> modelMap = new HashMap<String, String>();   
		task.setUpdBy(loginName);
		task.setUpdDate(new Date());		
		String seq = timesheetService.saveTask(task,loginName);
		if(seq!=null){
			modelMap.put("result", "1");
			SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy"); 
			modelMap.put("lastUpDate", formatter.format(task.getUpdDate()));
			modelMap.put("seq", seq);
		}else{
			modelMap.put("result", "0");
		}		
	    return modelMap;
	  }
	
	@RequestMapping(value = "/delTask.eab", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, String> delTimesheet(@RequestBody List<SysTaskCodeEntity> taskList,HttpServletRequest request, HttpServletResponse response, Model model) {
		//String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");
		Map<String, String> modelMap = new HashMap<String, String>();  
		if(taskList!=null&&taskList.size()>0){
			for(int i=0;i<taskList.size();i++){
			 timesheetService.delete(taskList.get(i));
			}			
			modelMap.put("result", "1");		
		}else{
			modelMap.put("result", "0");		
		}
		return  modelMap;
	
	}
	
	
	@RequestMapping(value = "/dowloadTime.eab", method = RequestMethod.POST)
    @ResponseBody
    public  String  dowloadTime(HttpServletRequest request, HttpServletResponse response,SearchTimesheetEntity searchEntity)  { 
		 try {
			timesheetService.getTimesheetExcel(response,searchEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
	
	  return null;  
	 }
}
