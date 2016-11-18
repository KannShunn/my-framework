package com.cesgroup.auth.role.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.core.dao.BaseDao;

public interface RoleDao extends BaseDao<Role>{

	List<Role> getByRoleClassificationId(String roleClassificationId);
	
	@Query("select r from Role r where r.roleClassificationId = :roleClassificationId and r.unitId in (:unitIds) order by r.showOrder")
    List<Role> getRoleByRoleClassificationIdAndUnitId(@Param("roleClassificationId")String roleClassificationId, @Param("unitIds")List<String> unitIds);
	
	@Query("select r from Role r where r.unitId in (:unitIds) order by r.showOrder")
    List<Role> getRoleByUnitId( @Param("unitIds")List<String> unitIds);

	@Query("select r from Role r,RoleUser ru where ru.roleId = r.id and ru.unitId =?1 and ru.userId = ?2")
	List<Role> getRoleByUnitIdAndUserId(String unitId, String userId);
	
	@Query("select r from Role r,RoleUser ru where ru.roleId = r.id and ru.userId = ?1")
	List<Role> getRoleByUserId(String userId);

}
