package com.eastpro.entity;

import java.util.List;

import javax.persistence.Entity;


@Entity
public class SysTaskGrp {		
	private String projectCode;
	private String projectName;
	private List<SysTaskCodeEntity> tasks;
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<SysTaskCodeEntity> getTasks() {
		return tasks;
	}
	public void setTasks(List<SysTaskCodeEntity> tasks) {
		this.tasks = tasks;
	}
	
	
   
	
}
