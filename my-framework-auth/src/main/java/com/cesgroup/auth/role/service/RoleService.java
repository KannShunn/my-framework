package com.cesgroup.auth.role.service;

import java.util.List;

import com.cesgroup.auth.role.vo.RoleUserGridVo;
import org.springframework.data.domain.Page;

import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.core.service.BaseService;

public interface RoleService extends BaseService<Role> {

	/**
	 * 根据角色分类id获取下面的角色 (不分页, 不区分公共角色和私有角色)
	 * 
	 * <p>描述:</p>
	 * @param roleClassificationId
	 * @return
	 *
	 */
	public List<Role> getByRoleClassificationId(String roleClassificationId);

	/**
	 * 排序
	 * 
	 * <p>描述:</p>
	 * @param sortBeforeIDs
	 * @param sortAfterIDs
	 * @param beforeSortPublicFirstId
	 * @param unitId
	 * @return
	 *
	 */
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId, String unitId);

	/**
	 * 角色授予资源
	 * 
	 * <p>描述:</p>
	 * @param roleId
	 * @param addResourceIds 添加的资源ids
	 * @param deleteResourceIds 删除的资源ids
	 * @param unitId
	 * @return
	 *
	 */
	public boolean grantResource(String roleId, String addResourceIds,String deleteResourceIds, String unitId);
	
	/**
	 * 根据角色分类id和单位id获取下面的角色
	 * 
	 * <p>描述:</p>
	 * @param roleClassificationId
	 * @param unitId
	 * @return
	 *
	 */
	public List<Role> getByRoleClassificationIdAndUnitId(String roleClassificationId, String unitId);

	/**
	 * 根据unitId获取下面的角色
	 * 
	 * @param unitId
	 * @return
	 */
	public List<Role> getRoleByUnitId(String unitId);
	
	/**
	 * 根据userId获取下面的角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> getRoleByUserId(String userId);

	/**
	 * 根据userId获取下面的角色
	 *
	 * @param userId
	 * @return
	 */
	public List<RoleUserGridVo> getRoleUserVoByUnitIdAndUserId(String unitId, String userId);

	/**
	 * 根据单位id和用户id获取该用户在这家单位拥有的角色
	 * 
	 * @param unitId
	 * @return
	 */	
	public List<Role> getRoleByUnitIdAndUserId(String unitId,String userId);

	
	/**
	 * 授予用户
	 * 
	 * <p>描述:</p>
	 * @param roleId 角色id
	 * @param userIds 该角色授予的用户id
	 * @param unitId 当前登录人的单位id
	 * @return
	 *
	 */
	public boolean grantUser(String roleId, String addUserIds,String deleteUserIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);
	
}
