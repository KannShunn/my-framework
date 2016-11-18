package com.cesgroup.auth.org.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.vo.OrgGridVo;
import com.cesgroup.core.service.BaseService;

/**
 * 组织相关服务接口
 * 
 * @author niklaus
 *
 */
public interface OrgService extends BaseService<Org>
{
	/**
	 * 
	 * <p>描述: 获得页面左侧的组织同步树, 如果单位id是-1, 则显示全部的组织树. 如果不是-1, 则显示该单位的所有组织</p>
	 * @param unitId 单位ID
	 * @return
	 *
	 */
	List<Org> getLeftOrgSyncTree(String unitId);

	/**
	 * 通过组织id获取它的直接单位实体
	 * @param orgId 组织id
	 * @return
	 */
	public Org getUnitOrgByOrgId(String orgId);
	
	/**
	 * 
	 * <p>描述: 根据组织id, 查询该组织下所有的子孙组织</p>
	 * @param allOrgs 组织集合
	 * @param id 组织ID
	 * @return
	 *
	 */
	public List<Org> getAllChildOrgById(List<Org> allOrgs, String id);

	/**
	 * 
	 * 
	 * <p>描述:根据左侧选择的组织节点, 获得应该显示的组织类型</p>
	 * @param orgId
	 * @return [{value:"2",text:"部门"},{value:"1",text:"单位"}]
	 *
	 */
	List<Map<String,Object>> getorganizeTypeIdData(String orgId);

	/**
	 * 获取所有的单位类型的组织
	 * 
	 * @return 所有的单位类型的组织
	 *
	 */
	List<Org> getAllUnit();

	/**
	 * 根据用户id获取组织对象
	 * 
	 * @param userId
	 * @return
	 *
	 */
	List<OrgGridVo> getOrgByUserId(String userId);

	/**
	 * 根据单位id获取该单位下所有的组织
	 * 
	 * @param unitId 当前登录人的单位id
	 * @return
	 *
	 */
	List<Org> getByUnitId(String unitId);

	/**
	 * 移动组织, 将一个组织移动到另一个组织下。可以存在跨单位移动组织
	 * 
	 * @param orgId 被移动的组织id
	 * @param unitId 被移动的组织的单位id
	 * @param targetOrgId 移动到的组织id
	 * @param targetUnitId 移动到的组织的单位id
	 *
	 */
	void moveOrg(String orgId, String unitId, String targetOrgId, String targetUnitId);

	/**
	 * 
	 * 组织合并
	 * @param mergeOrgIds 待合并的组织id
	 * @param org 合并后的组织对象
	 * @return 合并后的组织
	 *
	 */
	Org mergeOrg(String mergeOrgIds, Org org);

	/**
	 * 获取该组织下的直接子组织
	 * 
	 * @param id
	 * @return
	 *
	 */
	List<Org> getChildOrgById(String id);
}
