package com.cesgroup.auth.user.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.user.entity.RoleUser;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;

//@Transactional
public interface RoleUserDao extends BaseDao<RoleUser>{

	@Modifying
//	@Transactional
	@Query("delete from RoleUser where userId = :userId")
	void deleteByUserId(@Param("userId") String userId);
	
	@Query("select count(ou.id) from RoleUser ou where roleId = :roleId")
	int getCountByRoleId(@Param("roleId") String roleId);

	@Modifying
//	@Transactional
	@Query("delete from RoleUser where userId = :userId and unitId = :unitId")
	void deleteByUserIdAndUnitId(@Param("userId") String userId, @Param("unitId") String unitId);
	
	@Modifying
//	@Transactional
	@Query("delete from RoleUser where roleId = :roleId and unitId = :unitId")
	void deleteByRoleIdAndUnitId(@Param("roleId") String roleId, @Param("unitId") String unitId);
	
	
	@Modifying
//	@Transactional
	@Query("delete from RoleUser where roleId = :roleId")
	void deleteByRoleId(@Param("roleId") String roleId);

	@Modifying
//	@Transactional
	@Query("delete from RoleUser where userId = :userId and roleId = :roleId and unitId = :unitId")
	void deleteByUserIdAndRoleIdAndUnitId(@Param("userId") String userId,@Param("roleId") String roleId,@Param("unitId") String unitId);

	@Modifying
//	@Transactional
	@Query("delete from RoleUser where isTempAccredit = '"+ Constants.User.IS_TEMP_ACCREDIT_YES +"' and tempAccreditDateEnd < :currentDate")
	void deleteExpireRole(@Param("currentDate") String currentDate);

}
