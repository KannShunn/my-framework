package com.cesgroup.core.entity;

/**
 * 
 * 尾部实体类
 * <p>描述:尾部实体类, 用于各个实体类最后的一些创建人,创建时间等处理. 有这些字段的就实现该接口</p>
 */
public interface TailEntity {

	
	/** 获得创建人id */
	public String getCreateUserId();
	
	/** 设置创建人id */
	public void setCreateUserId(String createUserId);
	
	/** 获得创建人名称 */
	public String getCreateUserName();
	
	/** 设置创建人名称 */
	public void setCreateUserName(String createUserName);
	
	/** 获得创建时间 */
	public String getCreateTime();
	
	/** 设置创建时间 */
	public void setCreateTime(String createTime);
	
	/** 获得更新人id */
	public String getUpdateUserId();
	
	/** 设置更新人id */
	public void setUpdateUserId(String updateUserId);
	
	/** 获得更新人名称 */
	public String getUpdateUserName();
	
	/** 设置更新人名称 */
	public void setUpdateUserName(String updateUserName);
	
	/** 获得更新时间 */
	public String getUpdateTime();
	
	/** 设置更新时间 */
	public void setUpdateTime(String updateTime);
}
