package com.cesgroup.auth.resource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.resource.dao.ResourceDao;
import com.cesgroup.auth.resource.dao.RoleResDao;
import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.resource.service.ResourceService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;
@Service
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource,ResourceDao> implements ResourceService{

	@Autowired
	public RoleResDao roleResDao;

	@Override
	@Autowired
	public void setDao(ResourceDao resourceDao)
	{
		super.dao = resourceDao;
	}
	
	///////////////////////////////////////////////////////////

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Resource> getResourceByPId(String pId) {
		return getDao().getByPId(pId);
	}


	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Resource> getAllResourceTree(boolean isShowAuthSystem, Map<String,List<Resource>> resourceOfRole, String unitId) {
		List<Resource> result = new ArrayList<Resource>();
		if(isShowAuthSystem){
			//此处采用getService(beanClazz)的方式是为了如果注入sysService了,此时SysService里也注入了resourceService, 就会发生bean循环引用的问题.
			result = super.getAll("showOrder_ASC"); // 这句话将所有的系统查出来
		}else{
			result = getDao().getAllNoAuth();// 这句话将所有的系统查出来除了系统管理平台
		}

		this.checkResoureOfRole(result, resourceOfRole, unitId);
		return result;
	}



	/**
	 * 为角色拥有的资源打勾
	 *
	 */
	private void checkResoureOfRole(List<Resource> allResources, Map<String,List<Resource>> resourceOfRole, String unitId){
		if(resourceOfRole !=null){
			if(Constants.User.SUPER_UNITID.equals(unitId)){ //超级系统管理员, 只check公共的资源
				List<Resource> publicRes = resourceOfRole.get("publicRes");
				for (Resource res : allResources) {
					if(publicRes.contains(res)){
						res.setChecked(true);
					}
				}
			} else {
				List<Resource> publicRes = resourceOfRole.get("publicRes");
				List<Resource> privateRes = resourceOfRole.get("privateRes");
				for (Resource res : allResources) {
					if(publicRes.contains(res)){
						res.setChecked(true);
						res.setChkDisabled(true);
					}
					if(privateRes.contains(res)){
						res.setChecked(true);
					}
				}
			}
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String,List<Resource>>  getResourcesOfRole(String roleId, String unitId){
		
		Map<String, List<Resource>> result = new HashMap<String, List<Resource>>();
		List<Resource> publicRes = null;
		List<Resource> privateRes = null;
		if(Constants.User.SUPER_UNITID.equals(unitId)){ //单位id为-1则说明是超级系统管理员, 则此时显示角色的公共资源
			publicRes = getDao().getResourcesOfRole(roleId, Constants.User.SUPER_UNITID);
		} else { //单位管理员, 此时显示角色的公共资源+私有资源
			publicRes = getDao().getResourcesOfRole(roleId, Constants.User.SUPER_UNITID);
			privateRes = getDao().getResourcesOfRole(roleId, unitId);
		}
		
		result.put("publicRes", publicRes);
		result.put("privateRes", privateRes);
		
		return result;
	}

	@Override
	public void moveResource(String resourceIds, String newPResourceId) {
		if(StringUtils.isNotEmpty(resourceIds) && newPResourceId != null){
			String[] resourceIdsArray = resourceIds.split(",");

			for (String resourceId : resourceIdsArray) {
				getDao().updatePId(resourceId,newPResourceId);
			}
		}
	}


	@Override
	public void delete(String ids) {
		
		List<Resource> allChildResources = null;
		String[] idArray = ids.split(",");
		for (String resourceId : idArray) {
			Resource resource = getOneById(resourceId);
			if(Constants.Common.YES.equals(resource.getIsSystem())){
				throw new RuntimeException("不能删除系统内置数据");
			}
			if(resource == null){
				continue;
			}
			if(Constants.Common.YES.equals(resource.getIsSystem())){
				throw new RuntimeException("不能删除系统内置资源");
			}

			allChildResources = new ArrayList<Resource>();
			allChildResources = this.getAllChildResourceById(allChildResources, resourceId);
			
			//1. 删角色和资源的关系
			//2. 删资源本身
			for (Resource childRes : allChildResources) {
				roleResDao.deleteByResourceId(childRes.getId());
			}
			roleResDao.deleteByResourceId(resource.getId());
			
			allChildResources.add(resource);
			getDao().delete(allChildResources);
		}
		
	}
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Resource> getAllChildResourceById(List<Resource> allResources, String id){
		List<Resource> childResources = this.getResourceByPId(id);
		
		for (Resource res : childResources) {
			allResources.add(res);
			allResources = this.getAllChildResourceById(allResources, res.getId());
		}
		return allResources;
	}

	@Override
	public List<Resource> getDefaultResources() {
		return getDao().getDefaultResources();
	}
}
