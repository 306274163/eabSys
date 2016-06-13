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
@Table(name="SYS_LOOKUP")
public class SysLookEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id//代表主键
	@Column(name="SEQ", nullable = false, length = 50)//映射字段
	private String seq;

	@Column(name="LOOKUP_KEY",length = 20)
	private String lookupKey;
	
	@Column(name="LOOKUP_VAL",length = 500)
	private String lookupVal;

	
	
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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getLookupKey() {
		return lookupKey;
	}

	public void setLookupKey(String lookupKey) {
		this.lookupKey = lookupKey;
	}

	public String getLookupVal() {
		return lookupVal;
	}

	public void setLookupVal(String lookupVal) {
		this.lookupVal = lookupVal;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
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
