package com.cq.mysong.entity.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/**
 *------------------☆重庆MySong☆------------------------
 * @author ：mySong		@Date : 2017-2-10 
 * @version ：  V1.0
 * @Description :   
 *	 核心数据模型
 *------------------☆重庆MySong☆------------------------
 */
@MappedSuperclass
public class CoreInfo implements Serializable{
	private int id;
	
	@Id
	@Column(name="fid",length=50)
	@GenericGenerator(name = "generatePK", strategy = "com.cq.mysong.core.GeneratePK")
	@GeneratedValue(generator ="generatePK")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
