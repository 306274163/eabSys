package com.eastpro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="SYS_ALLUSER")
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id//代表主键
	@Column(name="PERSON_ID", nullable = false, length = 32)//映射字段
	private String personId;

	@Column(name="COMP_CODE",length = 20)
	private String compCode;

	@Column(name="USER_TYPE",length = 40)
	private String userType;

	@Column(name="DEALER_GROUP",length = 100)
	private String dealerGroup;

	@Column(name="UNIT",length = 100)
	private String unit;
	
	@Column(name="DISTRICT")
	private String district;

	@Column(name="SC_CODE")
	private String scCode;

	@Column(name="DEPT_CODE",length = 20)
	private String deptCode;

	@Column(name="USER_NAME",length = 10)
	private String userName;

	@Column(name="USER_NAME_CHI",length = 30)
	private String userNameChi;

    @Column(name="SUPERVISOR",length = 30)
    private String supervisor;

    @Column(name="PASSWORD",length = 300)
    private String password;

    @Column(name="LAST_CHG_PASSWD",length = 300)
    private String lastChgPassword;

    @Column(name="TEMPLATE_CODE",length = 300)
    private String templateCode;

    @Column(name="LAST_LOGIN",length = 300)
    private String lastLogin;

    @Column(name="MUST_CHG_PASSWD",length = 300)
    private String mustChgPassword;

    @Column(name="FAIL_COUNT",length = 300)
    private String failCount;

    @Column(name="EMAIL_ADDR",length = 300)
    private String eamilAddr;

    @Column(name="POST",length = 300)
    private String post;

    @Column(name="STATUS",length = 300)
    private String status;

    @Column(name="LST_UPD_DATE",length = 300)
    private String lastUpdDate;

    @Column(name="PHONE_NO",length = 300)
    private String phoneNo;

    @Column(name="BROKER_CODE",length = 300)
    private String brokerCode;

    @Column(name="POSITION",length = 300)
    private String position;

    @Column(name="AGENT_CODE",length = 300)
    private String agentCode;

    @Column(name="AGENT_CODE_DISP",length = 300)
    private String agentCodeDisp;

    @Column(name="LEVEL_CODE",length = 300)
    private String levelCode;

    @Column(name="TITLE",length = 300)
    private String title;

    @Column(name="SEQ_NUM",length = 300)
    private String seqNum;

    @Column(name="SYS_LANG",length = 300)
    private String sysLang;

    @Column(name="TERM_DATE",length = 300)
    private String termDate;

    @Column(name="CREATE_DATE",length = 300)
    private String createDate;

    @Column(name="LEAD_EMAIL_FLAG",length = 300)
    private String leadEmailFlag;

    @Column(name="EMAIL_ALERT_IND",length = 300)
    private String emailAlertInd;

    @Column(name="IS_MANAGER",length = 300)
    private String isManager;

    @Column(name="CITY",length = 300)
    private String city;

    @Column(name="MIGRATION_IND",length = 300)
    private String migrationInd;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDealerGroup() {
        return dealerGroup;
    }

    public void setDealerGroup(String dealerGroup) {
        this.dealerGroup = dealerGroup;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getScCode() {
        return scCode;
    }

    public void setScCode(String scCode) {
        this.scCode = scCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameChi() {
        return userNameChi;
    }

    public void setUserNameChi(String userNameChi) {
        this.userNameChi = userNameChi;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastChgPassword() {
        return lastChgPassword;
    }

    public void setLastChgPassword(String lastChgPassword) {
        this.lastChgPassword = lastChgPassword;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMustChgPassword() {
        return mustChgPassword;
    }

    public void setMustChgPassword(String mustChgPassword) {
        this.mustChgPassword = mustChgPassword;
    }

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public String getEamilAddr() {
        return eamilAddr;
    }

    public void setEamilAddr(String eamilAddr) {
        this.eamilAddr = eamilAddr;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(String lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentCodeDisp() {
        return agentCodeDisp;
    }

    public void setAgentCodeDisp(String agentCodeDisp) {
        this.agentCodeDisp = agentCodeDisp;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getSysLang() {
        return sysLang;
    }

    public void setSysLang(String sysLang) {
        this.sysLang = sysLang;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLeadEmailFlag() {
        return leadEmailFlag;
    }

    public void setLeadEmailFlag(String leadEmailFlag) {
        this.leadEmailFlag = leadEmailFlag;
    }

    public String getEmailAlertInd() {
        return emailAlertInd;
    }

    public void setEmailAlertInd(String emailAlertInd) {
        this.emailAlertInd = emailAlertInd;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMigrationInd() {
        return migrationInd;
    }

    public void setMigrationInd(String migrationInd) {
        this.migrationInd = migrationInd;
    }
}
