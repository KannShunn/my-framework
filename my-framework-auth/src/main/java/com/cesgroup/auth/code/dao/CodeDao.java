package com.cesgroup.auth.code.dao;


import com.cesgroup.auth.code.entity.Code;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.dao.BaseDao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * 编码数据访问层
 * <p>描述:编码数据访问层</p>
 */
//@Transactional
public interface CodeDao extends BaseDao<Code>{

	@Modifying
	@Transactional
	@Query("update Code set status = :status where id = :id")
	void updateStatus(@Param("id") String id,@Param("status") String status);

	@Query("select c from Code c where c.pId = ?1 and c.unitId in (?2) and c.status = '"+ Constants.Common.ON_USE +"' order by c.showOrder asc")
	List<Code> getByPIdAndUnitIds(String pId, List<String> unitIds);

	@Query("select c from Code c where c.unitId in (?1) and c.status = '"+ Constants.Common.ON_USE +"' order by c.showOrder asc")
	List<Code> getByUnitIds(List<String> unitIds);

	@Query("select c from Code c where c.pId = ?1 and c.status = '"+ Constants.Common.ON_USE +"' order by c.showOrder asc")
	List<Code> getByPId(String pId);

	@Query("select c from Code c where c.status = '"+ Constants.Common.ON_USE +"' order by c.showOrder asc")
	List<Code> getAll();

	@Query("select count(c.id) from Code c where c.pId = ?1 and c.unitId in (?2) and c.status = '"+ Constants.Common.ON_USE +"'")
	Integer getCountByPIdAndUnitIds(String id, List<String> unitIds);

	@Query("select c from Code c where c.parentCode = ?1  and c.status = '"+ Constants.Common.ON_USE +"' order by c.showOrder asc")
	List<Code> getByParentCode(String parentCode);

	@Modifying
	@Transactional
	@Query("update Code set pId = :pId, parentCode = :parentCode where id = :id")
	void moveCode(@Param("id")String codeId, @Param("pId")String newPId, @Param("parentCode")String newParentCode);
}
