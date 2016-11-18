package com.cesgroup.auth.resource.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.resource.entity.RoleRes;
import com.cesgroup.core.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

public interface RoleResDao extends BaseDao<RoleRes>{

	@Modifying
	@Query("delete from RoleRes where roleId = ?1 and unitId = ?2")
	public void deleteByRoleIdAndUnitId(String roleId, String unitId);

	@Modifying
	@Query("delete from RoleRes where roleId = ?1 and resourceId = ?2 and unitId = ?3")
	public void deleteByRoleIdAndResourceIdAndUnitId(String roleId,String resourceId, String unitId);


	@Query("select count(*) from RoleRes rrs where rrs.resourceId = :resourceId")
	public int getCountByResourceId(@Param("resourceId") String resourceId);
	
	@Query("select count(*) from RoleRes rrs where rrs.roleId = :roleId")
	public int getCountByRoleId(@Param("roleId") String roleId);

	@Modifying
	@Query("delete from RoleRes where resourceId = ?1")
	public void deleteByResourceId(String resourceId);
	
	@Modifying
	@Query("delete from RoleRes where roleId = ?1")
	public void deleteByRoleId(String roleId);
}
