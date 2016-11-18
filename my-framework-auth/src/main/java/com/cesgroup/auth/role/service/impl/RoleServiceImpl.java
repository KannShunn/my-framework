package com.cesgroup.auth.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.resource.dao.RoleResDao;
import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.resource.entity.RoleRes;
import com.cesgroup.auth.resource.service.ResourceService;
import com.cesgroup.auth.role.dao.RoleDao;
import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.auth.role.service.RoleCUDListener;
import com.cesgroup.auth.role.service.RoleService;
import com.cesgroup.auth.role.vo.RoleUserGridVo;
import com.cesgroup.auth.user.dao.RoleUserDao;
import com.cesgroup.auth.user.entity.RoleUser;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role,RoleDao> implements RoleService {

	//依赖注入开始
	@Autowired
	public RoleResDao roleResDao;
	@Autowired
	public UserService userService;
	@Autowired
	public RoleUserDao roleUserDao;
	@Autowired(required = false)
	public RoleCUDListener roleCUDListener;


	@Override
	@Autowired
	public void setDao(RoleDao roleDao)
	{
		super.dao = roleDao;
	}

	//依赖注入结束


	/**
	 * 创建角色时默认为其绑定默认资源
	 * @param entity
	 * @return
     */
	@Override
	public Role create(Role entity) {
		Role role = super.create(entity);
		//为角色绑定默认资源
		List<Resource> defaultResources = getService(ResourceService.class).getDefaultResources();

		RoleRes roleRes = null;
		for (Resource defaultResource : defaultResources) {
			roleRes = new RoleRes();
			roleRes.setRoleId(role.getId());
			roleRes.setResourceId(defaultResource.getId());
			roleRes.setUnitId(role.getUnitId());
			roleResDao.save(roleRes);
		}
		return role;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Role> getByRoleClassificationId(String roleClassificationId){
		return getDao().getByRoleClassificationId(roleClassificationId);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Role> getByRoleClassificationIdAndUnitId(String roleClassificationId, String unitId)
	{
		List<String> unitIds = new ArrayList<String>();
		if(unitId == null){
			unitIds.add(Constants.User.SUPER_UNITID);
		} else {
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);
		}
		return getDao().getRoleByRoleClassificationIdAndUnitId(roleClassificationId, unitIds);
	}

	@Override
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId, String unitId) {
		String[] afterIDs = sortAfterIDs.split(",");
		String[] beforeIDs = sortBeforeIDs.split(",");
		if (afterIDs.length == 0 && beforeIDs.length == 0)
			return false;
		Role role = null;
		Long orderNo = 100L;
		role = this.getOneById(beforeIDs[0]); //原列表第一个数据, 取它的排序号, 接下来的排序号以此为基准往上递增
		if(role.getShowOrder()!=null){
			orderNo = role.getShowOrder();
		}

		List<Role> perList = new ArrayList<Role>();
		if(Constants.User.SUPER_UNITID.equals(unitId)){ //超级系统管理员维护公用角色, 公用角色的排序号是以100递增

			for (int i = 0; i < afterIDs.length; i++) {
				role = this.getOneById(afterIDs[i]);
				role.setShowOrder(orderNo);
				perList.add(role);
				//				roleDao.save(role);
				orderNo+=100;
			}
		} else { //单位系统管理员维护私有角色, 私有角色的排序号是以1递增
			Role afterSortFirstRole = this.getOneById(afterIDs[0]);
			if(beforeIDs[0].equals(beforeSortPublicFirstId) && !Constants.User.SUPER_UNITID.equals(afterSortFirstRole.getUnitId())){ //特殊情况,原列表上第一条数据是公用角色, 用户拖动私有角色至第一条公用角色之上
				Role beforeSoreFirstPublicRole = this.getOneById(beforeSortPublicFirstId);
				afterSortFirstRole.setShowOrder(beforeSoreFirstPublicRole.getShowOrder()-30); //减10是为了让这个特殊的角色排序顺序往下降一些,以免让后面来的私有角色的排序号不超出公用排序号
				perList.add(afterSortFirstRole);
				//				roleDao.save(afterSortFirstRole);
			}else{
				for (int i = 0; i < afterIDs.length; i++) {
					role = this.getOneById(afterIDs[i]);
					if(Constants.User.SUPER_UNITID.equals(role.getUnitId())){ //公用角色不排序
						orderNo = role.getShowOrder();
						orderNo++;
						continue;
					}
					role.setShowOrder(orderNo);
					perList.add(role);
					//					roleDao.save(role);
					orderNo++;
				}
			}
		}
		updateBatch(perList);
		return true;
	}

	@Override
	public boolean grantResource(String roleId, String addResourceIds,String deleteResourceIds, String unitId) {
		if(roleId == null){
			return false;
		}
		try {
			if(unitId == null){
                unitId = Constants.User.SUPER_UNITID;
            }
			if(StringUtils.isNotEmpty(deleteResourceIds)){
				String[] resourceIdsArray = deleteResourceIds.split(",");
				for (String resourceId : resourceIdsArray) {
					roleResDao.deleteByRoleIdAndResourceIdAndUnitId(roleId,resourceId,unitId);
				}
			}
			if(StringUtils.isNotEmpty(addResourceIds)){
                String[] resourceIdsArray = addResourceIds.split(",");

                for (String resourceId : resourceIdsArray) {
                    RoleRes roleRes = new RoleRes();

                    roleRes.setRoleId(roleId);
                    roleRes.setResourceId(resourceId);
                    roleRes.setUnitId(unitId);

                    roleResDao.save(roleRes);
                }

            }

			if (roleCUDListener != null) {
				roleCUDListener.onGrantResourceSuccess(roleId,addResourceIds,deleteResourceIds,unitId);
			}
		} catch (Exception e) {
			if (roleCUDListener != null) {
				roleCUDListener.onGrantResourceError(e,roleId,addResourceIds,deleteResourceIds,unitId);
			}
			throw new RuntimeException(e);
		} finally {
			if (roleCUDListener != null) {
				roleCUDListener.onGrantResourceComplete(roleId,addResourceIds,deleteResourceIds,unitId);
			}
		}
		return true;
	}


	@Override
	public void delete(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String roleId : idsArray) {
				Role entity = getOneById(roleId);
				if(Constants.Common.YES.equals(entity.getIsSystem())){
					throw new RuntimeException("不能删除系统内置数据");
				}
				//1. 删除角色和资源的关系
				//2. 删除角色和用户的关系
				//3. 删除角色本身
				roleResDao.deleteByRoleId(roleId);
				roleUserDao.deleteByRoleId(roleId);
				getDao().delete(roleId);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Role> getRoleByUnitId(String unitId)
	{
		List<String> unitIds = new ArrayList<String>();
		if(unitId == null){
			unitIds.add(Constants.User.SUPER_UNITID);
		} else {
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);
		}
		return getDao().getRoleByUnitId( unitIds);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Role> getRoleByUnitIdAndUserId(String unitId, String userId) {
		return getDao().getRoleByUnitIdAndUserId( unitId,userId);
	}

	@Override
	public boolean grantUser(String roleId, String addUserIds,String deleteUserIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd) {
		if(roleId != null && unitId != null){
			try {
				if(StringUtils.isNotEmpty(deleteUserIds)){
					String[] userIdArray = deleteUserIds.split(",");
					for (String userId : userIdArray) {
						roleUserDao.deleteByUserIdAndRoleIdAndUnitId(userId,roleId,unitId);
					}
				}
				if(StringUtils.isNotEmpty(addUserIds)){
                    String[] userIdArray = addUserIds.split(",");
                    RoleUser roleUser = null;
                    for (String userId : userIdArray) {
                        roleUser = new RoleUser();

                        roleUser.setUserId(userId);
                        roleUser.setUnitId(unitId);
                        roleUser.setRoleId(roleId);

						if (isTempAccredit) {
							roleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_YES);
							roleUser.setTempAccreditDateStart(tempAccreditDateStart);
							roleUser.setTempAccreditDateEnd(tempAccreditDateEnd);
						} else {
							roleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_NO);
						}
						roleUserDao.save(roleUser);
                    }
                }

				if (roleCUDListener != null) {
					roleCUDListener.onGrantUserSuccess(roleId,addUserIds,deleteUserIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
				}
			} catch (Exception e) {
				if (roleCUDListener != null) {
					roleCUDListener.onGrantUserError(e,roleId,addUserIds,deleteUserIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
				}
				throw new RuntimeException(e);
			} finally {
				if (roleCUDListener != null) {
					roleCUDListener.onGrantUserComplete(roleId,addUserIds,deleteUserIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
				}
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Role> getRoleByUserId(String userId) {
		return getDao().getRoleByUserId(userId);
	}

	@Override
	public List<RoleUserGridVo> getRoleUserVoByUnitIdAndUserId(String unitId, String userId) {
		if(unitId != null && userId != null){
			String jpql = null;
			if(Constants.User.SUPER_UNITID.equals(unitId)){
				jpql = "select new com.cesgroup.auth.role.vo.RoleUserGridVo(ru.id,ru.roleId,ru.userId,ru.unitId,'-1',r.name,r.roleKey,'',ru.isTempAccredit,ru.tempAccreditDateStart,ru.tempAccreditDateEnd) from RoleUser ru,Role r where ru.roleId = r.id and ru.userId = ?1 and ru.unitId = ?2";
			}else{
				jpql = "select new com.cesgroup.auth.role.vo.RoleUserGridVo(ru.id,ru.roleId,ru.userId,ru.unitId,rc.id,r.name,r.roleKey,rc.name,ru.isTempAccredit,ru.tempAccreditDateStart,ru.tempAccreditDateEnd) from RoleUser ru,Role r,RoleClassification rc where ru.roleId = r.id and r.roleClassificationId = rc.id and ru.userId = ?1 and ru.unitId = ?2";
			}
			TypedQuery<RoleUserGridVo> query = entityManager.createQuery(jpql, RoleUserGridVo.class);

			query.setParameter(1, userId);
			query.setParameter(2, unitId);

			return query.getResultList();
		}
		return null;
	}

}
