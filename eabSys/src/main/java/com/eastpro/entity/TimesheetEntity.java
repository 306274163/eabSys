package com.eastpro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="SYS_TIMESHEET")
public class TimesheetEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id//代表主键
	@Column(name="SEQ", nullable = false, length = 32)//映射字段
	private String seq;
     
	@Column(name="TASK_DATE",length = 20)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date task_date;

	@Column(name="PROJECT",length = 50)
	private String project;

	@Column(name="WR_NUMBER",length = 50)
	private String wrNumber;

	@Id//
	@Column(name="STAFF_NAME",length = 50)
	private String staffName;
	
	@Column(name="TASK_HOURS",length = 10)
	private String taskHours;	
	
	@Column(name="STAGE")
	private String stage;

	@Column(name="TASK_COMMENT")
	private String taskComment;

	@Column(name="UPD_DATE",length = 20)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date updDate;

	@Column(name="UPD_BY",length = 10)
	private String updBy;

	@Column(name="CREATE_DATE",length = 30)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;

    @Column(name="CREATE_BY",length = 30)
    private String createBy;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	

	public Date getTask_date() {
		return task_date;
	}


	public void setTask_date(Date task_date) {
		this.task_date = task_date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWrNumber() {
		return wrNumber;
	}

	public void setWrNumber(String wrNumber) {
		this.wrNumber = wrNumber;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getTaskHours() {
		return taskHours;
	}

	public void setTaskHours(String taskHours) {
		this.taskHours = taskHours;
	}

   
}
