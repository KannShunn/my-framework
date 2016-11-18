package com.cesgroup.auth.resource.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;


public interface ResourceDao extends BaseDao<Resource>
{

	@Query("select re from Resource re where re.pId = :pId order by re.showOrder")
	public List<Resource> getByPId(@Param("pId") String pId);

	/** 查询角色拥有的资源 */
	@Query(" select re from Resource re where exists (select rr.resourceId from RoleRes rr where rr.resourceId = re.id and rr.roleId = ?1 and rr.unitId = ?2 )")
	public List<Resource> getResourcesOfRole(String roleId, String unitId);

	@Query("select re from com.cesgroup.auth.resource.entity.Resource re where re.id in ( select rr.resourceId from com.cesgroup.auth.resource.entity.RoleRes rr where rr.unitId in ('-1',:unitId) and rr.roleId in (select ru.roleId from com.cesgroup.auth.user.entity.RoleUser ru where ru.userId = :userId and (isTempAccredit = '"+ Constants.User.IS_TEMP_ACCREDIT_NO+"' or ( isTempAccredit = '"+ Constants.User.IS_TEMP_ACCREDIT_YES+"' and tempAccreditDateStart <= :currentDate and tempAccreditDateEnd >= :currentDate)))) ")
	public List<Resource> getResourcesByUserId(@Param("userId") String userId, @Param("unitId") String unitId,@Param("currentDate") String currentDate);

	@Query("select re from Resource re where re.isSystem = '"+Constants.Common.NO+"' or re.id = '-1' order by re.showOrder")
	public List<Resource> getAllNoAuth();

	@Query("update Resource re set re.pId = :pId where re.id = :resourceId")
	@Modifying
	public void updatePId(@Param("resourceId") String resourceId,@Param("pId") String pId);

	@Query("select re from Resource re where re.isDefault = '"+Constants.Common.YES+"' order by re.showOrder")
	List<Resource> getDefaultResources();
}
