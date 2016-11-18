package com.cesgroup.auth.unitchange.service.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.unitchange.service.UnitChangeService;
import com.cesgroup.auth.user.dao.OrgUserDao;
import com.cesgroup.auth.user.dao.RoleUserDao;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.NonEntityServiceImpl;
import com.cesgroup.core.utils.Util;

@Service
@Transactional
public class UnitChangeServiceImpl extends NonEntityServiceImpl implements UnitChangeService{

	//依赖注入开始
	@Autowired
	private OrgUserDao orgUserDao;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleUserDao roleUserDao;

	//依赖注入结束

	
	@Override
	public void changeOrg(String userId, String newOrgId, String newUnitId, String oldOrgId, String oldUnitId) {
		if(Util.notNull(userId,newOrgId,newUnitId,oldOrgId,oldUnitId)){
			//没有跨单位, 则走普通的改变组织流程
			if(newUnitId.equals(oldUnitId)){
				userService.changeOrg(userId, newOrgId, oldOrgId,newUnitId);
			}
			else{//跨单位改变组织: 1.需要改变用户的直属单位id; 2.需要改变用户的组织id以及组织用户的单位id 3. 需要清空该用户在该单位下授予的所有角色  4. 如果目标部门已存在该用户的兼职, 则删掉该兼职
				List<User> users = userService.getUsersByOrgId(newOrgId, Constants.User.PARTTIME);
				if(users.size() > 0){
					orgUserDao.deleteByUserIdAndOrgIdAndUserType(userId, newOrgId, Constants.User.PARTTIME);
				}
				
				orgUserDao.changeUserOrg(userId, newOrgId, oldOrgId, newUnitId);
				
				User user = userService.getOneById(userId);
				user.setUnitId(newUnitId);
				userService.update(user);
				
				
				roleUserDao.deleteByUserIdAndUnitId(userId, oldUnitId);
			}
		}else{
			throw new RuntimeException(MessageFormat.format("参数不正确,userId:{0},newOrgId:{1},newUnitId:{2},oldOrgId:{3},oldUnitId:{4}", userId,newOrgId,newUnitId, oldOrgId, oldUnitId));
			
		}
	}

	@Override
	public void partTimeJob(String userId, String orgIds, String unitIds) {
		if(Util.notNull(userId,orgIds,unitIds)){
			String[] orgIdsArray = orgIds.split(",");
			String[] unitIdsArray = unitIds.split(",");
			for (int i=0; i<orgIdsArray.length; i++) {
				userService.partTimeJob(userId, orgIdsArray[i], unitIdsArray[i]);
			}
		}else{
			throw new RuntimeException(MessageFormat.format("参数不正确,userId:{0},orgIds:{1},unitIds:{2}", userId,orgIds,unitIds));
			
		}
	}

}
