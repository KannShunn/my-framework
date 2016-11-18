package com.cesgroup.auth.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.failedlog.service.LoginFailedLogService;
import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.service.OrgService;
import com.cesgroup.auth.org.vo.OrgUserTreeVo;
import com.cesgroup.auth.resource.dao.ResourceDao;
import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.user.dao.OrgUserDao;
import com.cesgroup.auth.user.dao.RoleUserDao;
import com.cesgroup.auth.user.dao.UserDao;
import com.cesgroup.auth.user.entity.OrgUser;
import com.cesgroup.auth.user.entity.RoleUser;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserCUDListener;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.auth.user.vo.UserGridVo;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;
import com.cesgroup.core.utils.ArrayUtil;
import com.cesgroup.core.utils.Collections3;
import com.cesgroup.core.utils.DateUtil;
import com.cesgroup.core.utils.JDBCUtil;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.utils.StrUtil;
import com.cesgroup.core.utils.Util;
import com.cesgroup.core.vo.PageVo;

/**
 * 用户服务
 * 
 * @author niklaus
 *
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User,UserDao> implements UserService
{

	// 依赖注入开始
	@Autowired
	public OrgService orgService;
	@Autowired
	public OrgUserDao orgUserDao;
	@Autowired
	public RoleUserDao roleUserDao;
	@Autowired(required = false)
	public UserCUDListener userCUDListener;

	@Override
	@Autowired
	public void setDao(UserDao userDao)
	{
		super.dao = userDao;
	}
	// 依赖注入结束

	//增删改事件监听开始
	@Override
	public void onCreateStart(User entity) {
		if (userCUDListener != null) {
			userCUDListener.onCreateStart(entity);
		}
	}

	@Override
	public User onCreateSuccess(User entity) {
		if(userCUDListener != null){
			return userCUDListener.onCreateSuccess(entity);
		}
		return entity;
	}

	@Override
	public void onCreateError(Exception e, User entity) {
		if(userCUDListener != null){
			userCUDListener.onCreateError(e,entity);
		}
	}

	@Override
	public void onCreateComplete(User entity) {
		if(userCUDListener != null){
			userCUDListener.onCreateComplete(entity);
		}
	}

	@Override
	public void onUpdateStart(User entity) {
		if(userCUDListener != null){
			userCUDListener.onUpdateStart(entity);
		}
	}

	@Override
	public User onUpdateSuccess(User entity) {
		if(userCUDListener != null){
			return userCUDListener.onUpdateSuccess(entity);
		}
		return entity;
	}

	@Override
	public void onUpdateError(Exception e, User entity) {
		if(userCUDListener != null){
			userCUDListener.onUpdateError(e,entity);
		}
	}

	@Override
	public void onUpdateComplete(User entity) {
		if(userCUDListener != null){
			userCUDListener.onUpdateComplete(entity);
		}
	}

	@Override
	public void onDeleteStart(String ids) {
		if(userCUDListener != null){
			userCUDListener.onDeleteStart(ids);
		}
	}

	@Override
	public void onDeleteSuccess(String ids) {
		if(userCUDListener != null){
			userCUDListener.onDeleteSuccess(ids);
		}
	}

	@Override
	public void onDeleteError(Exception e, String ids) {
		if(userCUDListener != null){
			userCUDListener.onDeleteError(e,ids);
		}
	}

	@Override
	public void onDeleteComplete(String ids) {
		if(userCUDListener != null){
			userCUDListener.onDeleteComplete(ids);
		}
	}
	//增删改事件监听结束

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public User getUserByLoginName(String loginName)
	{
		User result = null;
		if (StringUtils.isNotBlank(loginName))
		{
			return getDao().getByLoginName(loginName);
			
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Org> getLeftOrgSyncTree(String unitId) {
		if(Constants.User.SUPER_UNITID.equals(unitId)){ //超级管理员只显示各个单位节点
			List<Org> allUnits = orgService.getAllUnit(); //获得所有的单位
			Org root = orgService.getOneById(Constants.Org.TOP); //为他们添加一个根结点
			allUnits.add(root);
			return allUnits;
		}else{ //单位系统管理员显示该单位的组织树
			return orgService.getLeftOrgSyncTree(unitId);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PageVo getUsersByOrgId(String orgId, String currentUnitId, int pageNumber, int pageSize, String sorts, List<SearchFilter> sfList, boolean isClickRootNode, boolean isSuperadmin) {
		
		StringBuilder jpql = null;
		
		StringBuilder select = new StringBuilder("select new com.cesgroup.auth.user.vo.UserGridVo(u.id,u.loginName,u.name,u.flagAction,u.email,u.mobile,u.jobNo,u.unitId,u.urlPath,ou.userType,ou.organizeId,o.name) ");
		
		if(isSuperadmin) //超级系统管理员
		{
			if (isClickRootNode) //点击根结点, 显示所有的单位系统管理员用户
			{
				jpql = select.append("from User u,OrgUser ou,Org o where o.id = ou.organizeId and u.id = ou.userId and u.isAdmin = '"+Constants.User.IS_ADMIN+"' and u.status = '"+Constants.User.ONJOB+"' and u.id <> '"+Constants.User.SUPERADMIN_ID+"' ");
				
			}
			else //点击组织节点, 显示该组织下的单位管理员用户
			{
				jpql = select.append("from User u,OrgUser ou,Org o where o.id = ou.organizeId and u.id = ou.userId and u.isAdmin = '"+Constants.User.IS_ADMIN+"' and u.status = '"+Constants.User.ONJOB+"' and u.unitId = ?1 ");
			}
		}
		else //单位系统管理员
		{
			if (isClickRootNode) //点击单位节点, 显示该单位所有的业务用户,并且是专职的
			{
				jpql = select.append("from User u,OrgUser ou,Org o where o.id = ou.organizeId and u.id = ou.userId and u.unitId = ?1 and u.isAdmin = '"+Constants.User.NOT_ADMIN+"' and u.status = '"+Constants.User.ONJOB+"' and ou.userType = '"+Constants.User.FULLTIME+"'");
			}
			else //点击组织节点, 显示该组织下的直接业务用户
			{
				jpql = select.append("from User u,OrgUser ou,Org o where o.id = ou.organizeId and ou.organizeId = ?1 and u.id = ou.userId and u.isAdmin = '"+Constants.User.NOT_ADMIN+"' and u.status = '"+Constants.User.ONJOB+"' ");
			}
		}
	
		Object[] params = null;
		if(!(isSuperadmin && isClickRootNode)){
			params = new Object[1];
			params[0] = orgId;
		}
		PageVo result = JDBCUtil.queryForJPQL(entityManager, pageSize, pageNumber, jpql, "u", UserGridVo.class, sfList, sorts, params);
		
		//查找每个用户的身上的角色, 显示在列表上 (这步比较耗性能, 根据实际性能, 可将这块停用)
		List<UserGridVo> users = (List<UserGridVo>) result.getData();
		String jpql2 = "select r.name from Role r, RoleUser ru where r.id = ru.roleId and ru.userId = ?1 and ru.unitId = ?2";
		for (UserGridVo userGridVo : users) {
			Query query = entityManager.createQuery(jpql2);
			query.setParameter(1, userGridVo.getId());
			query.setParameter(2, currentUnitId);
			
			List<String> roleNames = query.getResultList();
			
			userGridVo.setRoleNames(ArrayUtil.toString(roleNames));
		}
		
		result.setData(users);
		return result;
	}

	@Override
	public User create(User user, String orgId, String currentUnitId, String roleId) {
		/*
		 * 新增用户
		 * 1. 将用户对象保存至数据库, 获得用户id
		 * 	1.1 如果用户的密码为空, 则使用初始化000000后MD5加密的密码; 如果不为空, 则密码进行MD5加密
		 * 2. 将用户绑定组织
		 * 3. 为用户绑定一个默认角色
		 */
		
		if(user != null && orgId != null){
			
			
			//未锁定
			user.setFlagAction(Constants.User.UNLOCK);
			//状态为在职
			user.setStatus(Constants.User.ONJOB);
			if(StringUtils.isEmpty(user.getPassword())){ //为用户密码加密
				user.setPassword(Util.MD5Encrypt(Constants.User.DEFAULT_PASSWORD));
			}else{
				user.setPassword(Util.MD5Encrypt(user.getPassword()));
			}
			
			user = super.create(user);
			
			//为用户绑定组织
			OrgUser orgUser = new OrgUser();
			orgUser.setUserId(user.getId());
			orgUser.setOrganizeId(orgId);
			//orgUser.setShowOrder(user.getId());
			orgUser.setUserType(Constants.User.FULLTIME);
			orgUser.setUnitId(user.getUnitId());
			
			orgUserDao.save(orgUser);
			
			
			List<RoleUser> roleUsers = new ArrayList<RoleUser>();
			//为用户绑定默认角色
			RoleUser defaultRoleUser = new RoleUser();
			
			defaultRoleUser.setRoleId(Constants.Role.DEFAULT_ROLE);
			defaultRoleUser.setUserId(user.getId());
			defaultRoleUser.setUnitId(currentUnitId);
			defaultRoleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_NO);
			roleUsers.add(defaultRoleUser);
			
			if(roleId != null){
				RoleUser roleUser = new RoleUser();
				
				roleUser.setRoleId(roleId);
				roleUser.setUserId(user.getId());
				roleUser.setUnitId(currentUnitId);
				roleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_NO);
				roleUsers.add(roleUser);
			}
			
			roleUserDao.save(roleUsers);
			
			return user;
		}
		
		return null;
	}

	@Override
	public void delete(String userIds) {
		if(StringUtils.isNotEmpty(userIds)){
			try {
				onDeleteStart(userIds);
				String[] userIdArray = userIds.split(",");
				for (String userId : userIdArray) {
					User entity = getOneById(userId);
					if(Constants.Common.YES.equals(entity.getIsSystem())){
						throw new RuntimeException("不能删除系统内置数据");
					}
                    getDao().deleteByUserId(userId,DateUtil.getCurrentStringDate());
                    orgUserDao.deleteByUserId(userId);
                    roleUserDao.deleteByUserId(userId);
                }
				onDeleteSuccess(userIds);
			} catch (Exception e) {
				onDeleteError(e,userIds);
				throw new RuntimeException(e);
			} finally {
				onDeleteComplete(userIds);
			}
		}
	}
	
	
	@Override
	public void initPassword(String userIds) {
		String[] userIdsArray = userIds.split(",");
		for (String userId : userIdsArray) {
			this.changePassword(Constants.User.DEFAULT_PASSWORD, userId);
		}
	}
	
	@Override
	public void changePassword(String password , String id) {
		if(StringUtils.isEmpty(password)){
			password = Constants.User.DEFAULT_PASSWORD;
		}
		String encryptionPassword = Util.MD5Encrypt(password);
		User user = this.getOneById(id);
		
		user.setPassword(encryptionPassword);
		user.setLastmodifypsd(new Timestamp(System.currentTimeMillis()));
		
		super.update(user);
	}

	@Override
	public void accreditRole(String userId, String addRoleIds,String deleteRoleIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd) {
		
		if(userId != null && unitId != null){

			try {
				if(StringUtils.isNotEmpty(deleteRoleIds)){
                    String[] roleIdArray = deleteRoleIds.split(",");
                    for (String roleId : roleIdArray) {
                        roleUserDao.deleteByUserIdAndRoleIdAndUnitId(userId,roleId,unitId);
                    }
                }
				if(StringUtils.isNotEmpty(addRoleIds)){
                    String[] roleIdArray = addRoleIds.split(",");
                    RoleUser roleUser = null;
                    for (String roleId : roleIdArray) {
                        roleUser = new RoleUser();

                        roleUser.setUserId(userId);
                        roleUser.setUnitId(unitId);
                        roleUser.setRoleId(roleId);

                        if(isTempAccredit){
                            roleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_YES);
                            roleUser.setTempAccreditDateStart(tempAccreditDateStart);
                            roleUser.setTempAccreditDateEnd(tempAccreditDateEnd);
                        }else{
                            roleUser.setIsTempAccredit(Constants.User.IS_TEMP_ACCREDIT_NO);
                        }
                        roleUserDao.save(roleUser);
                    }
                }

				if(userCUDListener != null){
                    userCUDListener.onAccreditRoleSuccess(userId,addRoleIds,deleteRoleIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
                }
			} catch (Exception e) {
				if(userCUDListener != null){
					userCUDListener.onAccreditRoleError(e,userId,addRoleIds,deleteRoleIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
				}
			} finally {
				if(userCUDListener != null){
					userCUDListener.onAccreditRoleComplete(userId,addRoleIds,deleteRoleIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
				}
			}
		}
		
	}

	@Override
	public void changeOrg(String userId, String newOrgId, String oldOrgId, String unitId) {
		//查询目标部门下有没有该用户的兼职, 如果有, 则删除该兼职
		List<User> users = getUsersByOrgId(newOrgId, Constants.User.PARTTIME);
		if(users.size() > 0){
			orgUserDao.deleteByUserIdAndOrgIdAndUserType(userId, newOrgId, Constants.User.PARTTIME);
		}
		orgUserDao.changeUserOrg(userId, newOrgId, oldOrgId, unitId);
	}

	@Override
	public void partTimeJob(String userId, String orgIds, String unitId) {
		
		for(String orgId : orgIds.split(",")){
			//兼职
			OrgUser orgUser = new OrgUser();
			orgUser.setUserId(userId);
			orgUser.setOrganizeId(orgId);
			orgUser.setUserType(Constants.User.PARTTIME);
			orgUser.setUnitId(unitId);
			orgUserDao.save(orgUser);
		}
	}

	@Override
	public void removePartTimeJob(String userIds, String orgIds, String unitIds, String currentUnitId) {
		if(StringUtils.isNotEmpty(userIds) && StringUtils.isNotEmpty(orgIds) && StringUtils.isNotEmpty(unitIds) && currentUnitId != null){
			String[] userIdArray = userIds.split(",");
			String[] orgIdArray = orgIds.split(",");
			String[] unitIdArray = unitIds.split(",");
			
			for (int i = 0; i < userIdArray.length; i++) {
				String userId = userIdArray[i];
				String orgId = orgIdArray[i];
				String unitId = unitIdArray[i];
				
				orgUserDao.deleteByUserIdAndOrgId(userId,orgId);
				if(isCrossUnit(currentUnitId, unitId)){ //如果跨单位撤销兼职
					int count = orgUserDao.getCountByUserIdAndUnitId(userId,currentUnitId); //查看该单位下还有没有该用户存在, 如果没有, 需要将该用户在该单位下的角色清除
					if(count == 0){
						roleUserDao.deleteByUserIdAndUnitId(userId, currentUnitId);
					}
				}
			}
			
		}
	}
	
	/**
	 * 
	 * 是否是跨单位
	 *
	 */
	private boolean isCrossUnit(String unitIdA,String unitIdB){
		if(!unitIdA.equals(unitIdB)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void renewOldToNew(String userId, String orgId) {
		
		User user = this.getOneById(userId);
		if(user == null){
			throw new RuntimeException("恢复失败, 参数不正确。");
		}
		if(isExistLoginName(user.getLoginName(),userId)){
			throw new RuntimeException("恢复失败, 登录名["+user.getLoginName()+"]被占用, 请修改登录名再启用。 ");
		}
		if(isExistJobNo(user.getJobNo(),userId)){
			throw new RuntimeException("恢复失败, 工号["+user.getJobNo()+"]被占用, 请修改工号再启用。 ");
		}
		
		
		//改变用户的状态为在职
		user.setOnJobDate(DateUtil.getCurrentStringDate());
		user.setIsSecondOnJob(Constants.Common.YES);
		user.setStatus(Constants.User.ONJOB);
		this.update(user);
		
		//将用户恢复到指定的部门下
		OrgUser orgUser = new OrgUser();
		orgUser.setUserId(userId);
		orgUser.setOrganizeId(orgId);
		orgUser.setUnitId(user.getUnitId());
		orgUser.setUserType(Constants.User.FULLTIME);
		//orgUser.setShowOrder(userId);
		
		orgUserDao.save(orgUser);
		
	}

	/**
	 * 
	 * 在在职用户中警号是否有重复
	 *
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private boolean isExistJobNo(String jobNo,String userId) {
		Query query = entityManager.createQuery("select count(u.id) from User u where upper(u.jobNo) = upper(?1) and u.id <> ?2 ");
		query.setParameter(1, jobNo);
		query.setParameter(2, userId);

		Object obj = query.getSingleResult();
		if(Integer.parseInt(String.valueOf(obj)) > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 登录名是否有重复
	 *
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private boolean isExistLoginName(String loginName,String userId) {
		Query query = entityManager.createQuery("select count(u.id) from User u where upper(u.loginName) = upper(?1) and u.id <> ?2 ");
		query.setParameter(1, loginName);
		query.setParameter(2, userId);

		Object obj = query.getSingleResult();
		if(Integer.parseInt(String.valueOf(obj)) > 0){
			return true;
		}
		return false;
	}

	@Override
	public User updateOldUser(User user) {
		
		User oldUser = getOneById(user.getId());
		
		oldUser.setLoginName(user.getLoginName());
		oldUser.setJobNo(user.getJobNo());
		
		return update(oldUser);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<User> getUsersOfRole(String roleId) {
		return getDao().getUsersOfRole(roleId);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<OrgUserTreeVo> getAllOrgUserTree(String unitId, String roleId) {
		
		List<OrgUserTreeVo> result = new ArrayList<OrgUserTreeVo>();

		//获取该单位下所有的用户
		List<UserGridVo> allUsers = getByUnitId(unitId);
		//将用户和组织构建一个关系, 关系形式为一个组织id对应该组织下所有的用户
		Map<String,List<UserGridVo>> orgIdUserMap = new HashMap<String, List<UserGridVo>>();
		for (UserGridVo user : allUsers) {
			String orgId = user.getOrgId();
			List<UserGridVo> list = null;
			if(orgIdUserMap.containsKey(orgId)){
				list = orgIdUserMap.get(orgId);
			}else{
				list = new ArrayList<UserGridVo>();
			}
			list.add(user);
			orgIdUserMap.put(orgId, list);
		}
		
		//获取该角色id对应的用户
		List<User> usersOfRole = null;
		if(roleId != null){
			usersOfRole = getUsersOfRole(roleId);
		}
		
		//获得该单位下所有的组织, 然后对其进行转换
		List<Org> allOrgs = orgService.getByUnitId(unitId);
        for(Org org : allOrgs){
            result.add(convertEntityToOrgUserTreeVo(org,false));
            List<UserGridVo> list = orgIdUserMap.get(org.getId());
            if(null!=list){
            	User user = null;
            	for(UserGridVo userGridVo : list){
            		Boolean isChecked = null;
            		user = new User();
            		user.setId(userGridVo.getId());
            		//判断用户是否拥有该角色、当拥有该角色，则在树节点上勾选
            		if(Collections3.isNotEmpty(usersOfRole)){
            			if(usersOfRole.contains(user)){
            				isChecked = true;
            			}else{
            				isChecked = false;
            			}
            		}
            		result.add(this.convertEntityToOrgUserTreeVo(userGridVo,isChecked));
            	}
            }
        }
		return result;
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UserGridVo> getByUnitId(String unitId) {
		String jpql = "select new com.cesgroup.auth.user.vo.UserGridVo(u.id,u.loginName,u.name,u.unitId,u.urlPath,ou.userType,ou.organizeId) from User u,OrgUser ou where u.id = ou.userId and u.isAdmin = '"+Constants.User.NOT_ADMIN+"' and u.status = '"+Constants.User.ONJOB+"' and ou.unitId = ?1";
		TypedQuery<UserGridVo> query = entityManager.createQuery(jpql, UserGridVo.class);
		query.setParameter(1, unitId);

		return query.getResultList();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private OrgUserTreeVo convertEntityToOrgUserTreeVo(Object object,Boolean checked) {
		OrgUserTreeVo vo = new OrgUserTreeVo();
		if(object instanceof Org){
			Org org = (Org)object;
			
			vo.setId(org.getId());
			vo.setpId(org.getpId());
			vo.setName(org.getName());
			vo.setNocheck(true);
			vo.setType(Constants.TreeNode.ORG_NODE);
            if(Util.notNull(checked)){
            	vo.setChecked(checked);
            }
		}else if(object instanceof UserGridVo){
			UserGridVo user = (UserGridVo)object;
            
			vo.setId(user.getId());
			vo.setpId(user.getOrgId());
			vo.setName(user.getName());
			vo.setType(Constants.TreeNode.USER_NODE);
            if(Util.notNull(checked)){
            	vo.setChecked(checked);
            }
        }
		
		return vo;
	}

	@Override
	public List<User> getUsersByOrgId(String orgId, String userType) {
		
		StringBuilder jpql = new StringBuilder("select u from User u,OrgUser ou where u.id = ou.userId and u.isAdmin = "+Constants.User.NOT_ADMIN+" and u.status = '"+Constants.User.ONJOB+"' and u.id > 1 and ou.organizeId = ?1");
		if(StrUtil.isNotEmpty(userType)){
			jpql.append(" and ou.userType = "+userType);
		}
		TypedQuery<User> query = entityManager.createQuery(jpql.toString(), User.class);
		query.setParameter(1, orgId);
		
		
		return query.getResultList();
	}

	@Override
	public Map<String, String> isOffJobUser(String loginName, String unitId) {
		StringBuilder jpql = new StringBuilder("select new com.cesgroup.auth.user.vo.UserGridVo(u.id,u.name, u.unitId ,o.name) from User u,Org o where u.unitId = o.id and u.isAdmin = "+Constants.User.NOT_ADMIN+" and u.status = '"+Constants.User.OFFJOB+"' and upper(u.loginName) = upper(?1)");
		
		TypedQuery<UserGridVo> query = entityManager.createQuery(jpql.toString(),UserGridVo.class);
		query.setParameter(1, loginName);
		
		List<UserGridVo> list = query.getResultList();
		
		Map<String,String> result = new HashMap<String, String>();
		if(list.size() == 0){
			result.put("0", "");
		}else{
			UserGridVo user = list.get(0);
			String message = null;
			if(unitId.equals(user.getUnitId())){
				message = "该登录名:[" + loginName + "]在您的单位下有离职用户[" + user.getName() + "]存在, 是否立即恢复? (数据使用的是历史数据)";
				result.put("1", message);
				result.put("oldUserId", user.getId()+"");
			}else{
				message = "该登录名:[" + loginName + "]在其他单位["+user.getUnitName()+"]下有离职用户[" + user.getName() + "]存在, 请修改登录名再保存。";
				result.put("2", message);
				result.put("oldUserId", user.getId()+"");
			}
			
		}
		return result;
	}

	@Override
	public void resumeUser(User user, String orgId) {
		User oldUser = this.getOneById(user.getId());
		oldUser.setOnJobDate(DateUtil.getCurrentStringDate());
		oldUser.setIsSecondOnJob(Constants.Common.YES);
		oldUser.setStatus(Constants.User.ONJOB);
		this.update(oldUser);
		
		//为用户绑定组织
		OrgUser orgUser = new OrgUser();
		orgUser.setUserId(user.getId());
		orgUser.setOrganizeId(orgId);
		//orgUser.setShowOrder(user.getId());
		orgUser.setUserType(Constants.User.FULLTIME);
		orgUser.setUnitId(oldUser.getUnitId());
		
		orgUserDao.save(orgUser);
	}

	@Override
	public List<Resource> getResourcesByUserId(String userId, String unitId) {
		List<Resource> result = getDao(ResourceDao.class).getResourcesByUserId(userId, unitId, DateUtil.getCurrentStringDate());
		return result;
	}

	@Override
	public void unlockUser(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {
				User user = getOneById(id);
				user.setFlagAction(Constants.User.UNLOCK);

				update(user);

				getService(LoginFailedLogService.class).deleteByLoginName(user.getLoginName());
			}
		}
	}

	@Override
	public void lockUser(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String id : idsArray) {
				User user = getOneById(id);
				user.setFlagAction(Constants.User.LOCKED);

				update(user);
			}
		}
	}
}
