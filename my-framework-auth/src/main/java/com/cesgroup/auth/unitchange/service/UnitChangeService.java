package com.cesgroup.auth.unitchange.service;

import com.cesgroup.core.service.NonEntityService;

public interface UnitChangeService extends NonEntityService{

	/**
	 * 跨单位改变组织
	 * 
	 * @param userId 用户id
	 * @param newOrgId
	 * @param newUnitId
	 * @param oldOrgId
	 * @param oldUnitId
	 *
	 */
	void changeOrg(String userId, String newOrgId, String newUnitId, String oldOrgId, String oldUnitId);

	/**
	 * 跨单位兼职
	 * 
	 * @param userId 用户id
	 * @param orgIds 兼职到的新的部门ids, 与unitIds一一相对
	 * @param unitIds 兼职到的新的单位ids, 与orgIds一一相对
	 *
	 */
	void partTimeJob(String userId, String orgIds, String unitIds);

}
