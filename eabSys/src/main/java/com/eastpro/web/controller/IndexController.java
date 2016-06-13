/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.eastpro.web.controller;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastpro.entity.SysLookEntity;
import com.eastpro.entity.SysProjectEntity;
import com.eastpro.entity.SysTaskCodeEntity;
import com.eastpro.entity.SysTaskGrp;
import com.eastpro.entity.TimesheetEntity;
import com.eastpro.service.TimesheetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private TimesheetService timesheetService;
	
	@RequestMapping(value = "/index.eab")
	public String toIndexPage(HttpServletRequest request, HttpServletResponse response,TimesheetEntity timesheet, Model model) {
		String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");	
		Date dt=new Date();    
		SimpleDateFormat matter1=new SimpleDateFormat("MM/dd/yyyy");	
		if(loginName!=null&&!"".equals(loginName))
		{
			timesheet.setStaffName(loginName);
		}
		String isSearch = request.getParameter("isSearch");
		if(!"Y".equals(isSearch)){				
			timesheet.setTask_date(new Date(matter1.format(dt)));		
			//model.addAttribute("firstGo","Y");
		}	
		List<TimesheetEntity> listTime= timesheetService.findAll(timesheet);			
		List<SysProjectEntity> sysProject = timesheetService.findAllProjectList();
		SysTaskCodeEntity taskEntity = new SysTaskCodeEntity ();
		List<SysTaskGrp>  sysTaskGrpList = timesheetService.findAllTaskGrpList(taskEntity);		
			
		List<SysTaskGrp>  pupTaskGrpList = timesheetService.findAllTaskGrpList(taskEntity,new Date(matter1.format(dt)));
		List<SysLookEntity> lookStage = timesheetService.findLookList("TIME_SHEET");
		model.addAttribute("defaultDate",matter1.format(dt));
		model.addAttribute("timesheet",timesheet);
		model.addAttribute("listTime",listTime);		
		model.addAttribute("sysProject",sysProject);
		model.addAttribute("lookStage",lookStage);
		model.addAttribute("sysTaskGrp",sysTaskGrpList);
		model.addAttribute("pupTaskGrpList",pupTaskGrpList);	
        return "timesheet/timesheetList";
	}
	/**
	 * 
	 * @param fail
	 * @param map
	 * @return
	 */	
    @RequestMapping(value = "/loginFailure.eab", method = RequestMethod.GET)
    public String loginFailure(@RequestParam(value = "fail", required = false) String fail, Map<String, Object> map) {
        if("authFailure".equals(fail))
            map.put("msg", "Login failure, please login again !");
        else if("sessionTimeout".equals(fail))
            map.put("msg", "Session timeout, please login again !");
        else if("sessionMaximum".equals(fail))
            map.put("msg", "Account has been in other places to visit !");
        return "main/error";
    }

    @RequestMapping(value = "/sessionTimeout.eab", method = RequestMethod.GET)
    public String sessionTimeout(Map<String, Object> map) {
        map.put("msg", "Session timeout, please login again !");
        return "main/error";
    }
	
	
	@RequestMapping(value = "/addTimesheet.eab", method = RequestMethod.POST)
    @ResponseBody
      public Map<String, String> addTimesheet(TimesheetEntity timesheet, Model model) {	
		Map<String, String> modelMap = new HashMap<String, String>();   
		String seq = timesheetService.save(timesheet);
		if(seq!=null){
			modelMap.put("result", "1");
			modelMap.put("seq", seq);
		}else{
			modelMap.put("result", "0");
		}		
	    return modelMap;
	    }
	
	@RequestMapping(value = "/delTimesheet.eab", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, String> delTimesheet(@RequestBody List<TimesheetEntity> listTime,HttpServletRequest request, HttpServletResponse response, Model model) {
		//String loginName = (String)request.getSession().getAttribute("SESSION_LOGINNAME");
		Map<String, String> modelMap = new HashMap<String, String>();  
		if(listTime!=null&&listTime.size()>0){
			for(int i=0;i<listTime.size();i++){
			 timesheetService.delete(listTime.get(i));
			}			
			modelMap.put("result", "1");		
		}else{
			modelMap.put("result", "0");		
		}
		return  modelMap;
	
	}
	
	@RequestMapping(value = "/changeWrDrodDownList.eab", method = RequestMethod.POST)
    @ResponseBody
      public Map<String, Object> changeWrDrodDownList(SysTaskCodeEntity task,HttpServletRequest request, Model model) {		
		Map<String, Object> modelMap = new HashMap<String, Object>();   
	    Date inDate = task.getStartDate();	  
	    task.setStartDate(null);
		List<SysTaskGrp>  pupTaskGrpList = timesheetService.findAllTaskGrpList(task,inDate);	
	    modelMap.put("result", "1");
	    modelMap.put("pupTaskGrpList", pupTaskGrpList);
		 return modelMap;
	  }
}
