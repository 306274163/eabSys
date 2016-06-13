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
@Table(name="SYS_PROJECT")
public class SysProjectEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id//代表主键
	@Column(name="PROJECT_CODE", nullable = false, length = 50)//映射字段
	private String projectCode;

	@Column(name="PROJECT_NAME",length = 50)
	private String projectName;

	@Column(name="STATUS",length = 1)
	private String projectStatus;
	
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

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
