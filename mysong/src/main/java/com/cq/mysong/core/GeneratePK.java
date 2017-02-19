package com.cq.mysong.core;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.cq.mysong.annotation.SubSystemAnno;
import com.cq.mysong.core.uuid.SysObjectType;
import com.cq.mysong.core.uuid.SysUuid;
import com.cq.mysong.core.uuid.UuidUtils;

/**
 *------------------☆重庆MySong☆------------------------
 * @author ：mySong		@Date : 2017-2-13 
 * @version ：  V1.0
 * @Description :   
 *	 自定义主键生成策略
 *------------------☆重庆MySong☆------------------------
 */

public class GeneratePK implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object obj)
			throws HibernateException {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister)session.getFactory().getClassMetadata(obj.getClass());
		Class claz = obj.getClass();
		if(claz!=null){
			String claz_str = claz.getName();
			SubSystemAnno ssa = (SubSystemAnno) claz.getAnnotation(SubSystemAnno.class);
			String objectType = UuidUtils.toHexString(ssa.entitySeq(), 8).toUpperCase();
			SysUuid uid = SysUuid.create(SysObjectType.create(objectType));
			return uid.toString();
		}
		return null;
	}

}
