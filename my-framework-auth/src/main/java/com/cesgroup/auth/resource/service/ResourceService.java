package com.cesgroup.auth.resource.service;

import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.core.service.BaseService;

import java.util.List;
import java.util.Map;

public interface ResourceService extends BaseService<Resource>{

	/**
	 * 本方法的作用是加载资源的左侧树
	 * @param isShowAuthSystem  //true 查询所有系统下的资源 / false 查询所有系统下的资源!!!除了系统管理平台
	 * @return
	 */
	public List<Resource> getAllResourceTree(boolean isShowAuthSystem, Map<String,List<Resource>> resourceOfRole, String unitId);
	
	/**
	 * 本方法的作用是通过资源实体中的parentId字段 来获取资源的所有子资源
	 * @param pId
	 * @return
	 */
	public List<Resource> getResourceByPId(String pId);

	/**
	 * 
	 * 获取角色所对应的资源
	 * <p>描述:获取角色所对应的资源, 单位系统管理员获得私有(本单位)的资源+公共的资源, 超级系统管理员获得公共的资源。返回格式是： 格式是: {"privateRes" : List<Resource>, "publicRes" : List<Resource>}</p>
	 * @param roleId 角色id
	 * @param unitId 单位id
	 * @return 返回的资源包含两部分, 一部分是私有(本单位)的资源, 一部分是公共的资源.
	 *
	 */
	public Map<String,List<Resource>> getResourcesOfRole(String roleId, String unitId);

	/**
	 *
	 * 获取角色所对应的资源
	 * <p>描述:获取角色所对应的资源, 单位系统管理员获得私有(本单位)的资源+公共的资源, 超级系统管理员获得公共的资源。返回格式是： 格式是: {"privateRes" : List<Resource>, "publicRes" : List<Resource>}</p>
	 * @param resourceIds 待移动的资源id
	 * @param newPResourceId 移动到的父节点id
	 * @return 返回的资源包含两部分, 一部分是私有(本单位)的资源, 一部分是公共的资源.
	 *
	 */
	public void moveResource(String resourceIds, String newPResourceId);

    /**
     * 根据id获取下面所有的子资源,包括孙子资源
     * @param allResources
     * @param id
     * @return
     */
    public List<Resource> getAllChildResourceById(List<Resource> allResources, String id);

	/**
	 * 获取默认资源
	 * @return
	 */
	List<Resource> getDefaultResources();
}
