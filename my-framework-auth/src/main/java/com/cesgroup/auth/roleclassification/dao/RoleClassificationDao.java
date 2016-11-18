package com.cesgroup.auth.roleclassification.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesgroup.auth.roleclassification.entity.RoleClassification;
import com.cesgroup.core.dao.BaseDao;

public interface RoleClassificationDao extends BaseDao<RoleClassification>
{

	@Query("select rc from RoleClassification rc where rc.unitId in (?1) order by rc.showOrder")
	public List<RoleClassification> getByUnitIds(List<String> unitIds);
	
	@Query("select count(*) from RoleClassification rc where rc.pId = :pId")
	public Long getCountByPId(@Param("pId") String pId);
	
}
