package com.cesgroup.core.entity;

import java.io.Serializable;

/**
 * 
 * 基础实体类
 * <p>描述:基础实体类, 实体类中必须包含id和name, 所有实体类都需实现该接口</p>
 */
public interface BaseEntity<PK extends Serializable> extends Serializable
{
	/** 获得id */
	public PK getId();
	
	/** 设置id */
	public void setId(PK id);
	
	/** 获得名称 */
	public String getName();
	
	/** 设置名称 */
	public void setName(String name);
	
}
