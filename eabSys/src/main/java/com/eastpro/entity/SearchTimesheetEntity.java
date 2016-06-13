package com.eastpro.entity;

import java.util.Date;


import javax.persistence.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class SearchTimesheetEntity  {	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dlTsStart;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dlTsEnd;	

	private String dlProjec;
	private String dlWrNumber;	
	private String dlStaffName;
	

	public Date getDlTsStart() {
		return dlTsStart;
	}


	public void setDlTsStart(Date dlTsStart) {
		this.dlTsStart = dlTsStart;
	}


	public Date getDlTsEnd() {
		return dlTsEnd;
	}


	public void setDlTsEnd(Date dlTsEnd) {
		this.dlTsEnd = dlTsEnd;
	}


	public String getDlProjec() {
		return dlProjec;
	}


	public void setDlProjec(String dlProjec) {
		this.dlProjec = dlProjec;
	}


	public String getDlWrNumber() {
		return dlWrNumber;
	}


	public void setDlWrNumber(String dlWrNumber) {
		this.dlWrNumber = dlWrNumber;
	}


	public String getDlStaffName() {
		return dlStaffName;
	}


	public void setDlStaffName(String dlStaffName) {
		this.dlStaffName = dlStaffName;
	}


	
	
	
	
	
}
