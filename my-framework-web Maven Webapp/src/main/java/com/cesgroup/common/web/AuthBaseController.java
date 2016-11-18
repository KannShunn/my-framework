package com.cesgroup.common.web;

import org.apache.shiro.SecurityUtils;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.TailEntity;
import com.cesgroup.core.service.BaseService;
import com.cesgroup.core.utils.DateUtil;
import com.cesgroup.core.web.BaseController;
import com.cesgroup.shiro.ShiroUser;

public abstract class AuthBaseController<T extends BaseEntity<String>,Service extends BaseService<T>> extends BaseController<T, Service> {


	protected ShiroUser getCurrentUser()
	{
		ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return currentUser;
	}
	
	
	/** 设置创建人,创建时间等尾部信息 */
	protected void setCreateUser(T entity){
		if(entity instanceof TailEntity){
			TailEntity tailEntity = (TailEntity) entity;
			ShiroUser user = getCurrentUser();
			tailEntity.setCreateUserId(user.getId());
			tailEntity.setCreateUserName(user.getName());
			tailEntity.setCreateTime(DateUtil.getCurrentDateTime());
		}
	}
	
	/** 设置修改人,修改时间等尾部信息 */
	protected void setUpdateUser(T entity){
		if(entity instanceof TailEntity){
			TailEntity tailEntity = (TailEntity) entity;
			ShiroUser user = getCurrentUser();
			tailEntity.setUpdateUserId(user.getId());
			tailEntity.setUpdateUserName(user.getName());
			tailEntity.setUpdateTime(DateUtil.getCurrentDateTime());
		}
	}
}
