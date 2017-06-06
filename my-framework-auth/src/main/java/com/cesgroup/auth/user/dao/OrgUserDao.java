package com.cesgroup.auth.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.user.entity.OrgUser;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;


@Transactional
public interface OrgUserDao extends BaseDao<OrgUser>{

	@Modifying
//	@Transactional
	@Query("delete from OrgUser where userId = :userId")
	void deleteByUserId(@Param("userId") String userId);
	
	@Modifying
//	@Transactional
	@Query("delete from OrgUser where userId = :userId and organizeId = :orgId")
	void deleteByUserIdAndOrgId(@Param("userId") String userId, @Param("orgId") String orgId);

	@Modifying
//	@Transactional
	@Query("delete from OrgUser where userId = :userId and organizeId = :orgId and userType = :userType")
	void deleteByUserIdAndOrgIdAndUserType(@Param("userId") String userId, @Param("orgId") String orgId, @Param("userType") String userType);
	
	
	@Query("select count(ou.id) from OrgUser ou where organizeId = :orgId")
	int getCountByOrgId(@Param("orgId") String orgId);
	
	@Query("select count(ou.id) from OrgUser ou where userId = :userId and unitId = :unitId")
	int getCountByUserIdAndUnitId(@Param("userId") String userId, @Param("unitId") String unitId);
	
	@Modifying
//	@Transactional
	@Query("update OrgUser set organizeId = :newOrgId, unitId = :unitId where userId = :userId and organizeId = :oldOrgId and userType = "+Constants.User.FULLTIME+"")
	void changeUserOrg(@Param("userId") String userId, @Param("newOrgId") String newOrgId,@Param("oldOrgId") String oldOrgId, @Param("unitId") String unitId);

	@Query("select ou from OrgUser ou where organizeId = :orgId")
	List<OrgUser> getByOrgId(@Param("orgId") String orgId);
	
}
