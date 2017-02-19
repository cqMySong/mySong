package com.cq.mysong.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cq.mysong.entity.core.CoreBaseInfo;


@Entity
@Table(name="t_pm_user")
public class UserInfo extends CoreBaseInfo{
	private String pwd;
	@Column(name="fpwd",length=100)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
