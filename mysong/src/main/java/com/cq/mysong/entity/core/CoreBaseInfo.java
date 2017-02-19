package com.cq.mysong.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.cq.mysong.entity.system.UserInfo;


/**
 *------------------☆重庆MySong☆------------------------
 * @author ：mySong		@Date : 2017-2-10 
 * @version ：  V1.0
 * @Description :   
 *	 基础数据模型
 *------------------☆重庆MySong☆------------------------
 */
@MappedSuperclass
public class CoreBaseInfo extends CoreInfo{
	private String name;
	private String number;
	private Date createDate;
	private UserInfo createUser;
	private Date lastUpdateDate;
	private UserInfo lastUpdateUser;
	private String remark;
	@Column(name="fcreateDate",nullable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@OneToOne
	@JoinColumn(name = "fcreateUser")
	public UserInfo getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}
	@Column(name="flastUpdateDate",nullable=false)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	@OneToOne
	@JoinColumn(name = "flastUpdateUser")
	public UserInfo getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(UserInfo lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	@Column(name="fname",length=200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="fnumber",length=100)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Column(name="fremark",length=500)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
