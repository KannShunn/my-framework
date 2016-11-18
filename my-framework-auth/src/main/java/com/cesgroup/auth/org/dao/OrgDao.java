package com.cesgroup.auth.org.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;

public interface OrgDao extends BaseDao<Org>
{
	
	/** 根据单位id获取该单位下的全部组织 */
	@Query("select o from Org o where o.unitId = :unitId and o.status = '"+Constants.Org.IN_USE+"' order by o.showOrder asc")
	List<Org> getByUnitId(@Param("unitId") String unitId);

	/** 查询全部组织 */
	@Query("select o from Org o where o.status = '"+Constants.Org.IN_USE+"' order by o.showOrder asc")
	List<Org> getAll();
	
	/** 查询全部的单位 */
	@Query("select o from Org o where o.status = '"+Constants.Org.IN_USE+"' and organizeTypeId = 1 order by o.showOrder asc")
	List<Org> getAllUnit();
	
	/** 根据组织id获取该组织下的子组织 */
	@Query("select o from Org o where o.pId = :pId and o.status = '"+Constants.Org.IN_USE+"' order by o.showOrder asc")
	List<Org> getByPId(@Param("pId") String pId);
}
