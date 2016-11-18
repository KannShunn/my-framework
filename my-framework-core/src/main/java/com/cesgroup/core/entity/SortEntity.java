package com.cesgroup.core.entity;

/**
 * 
 * 排序接口, 需要排序的实体类实现该接口即可, 然后调用BaseService的sort方法即可
 * <p>描述:排序接口, 需要排序的实体类实现该接口即可</p>
 */
public interface SortEntity {

	public void setShowOrder(Long showOrder);
	
	public Long getShowOrder();
}
