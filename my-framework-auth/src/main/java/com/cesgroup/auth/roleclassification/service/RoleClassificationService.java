package com.cesgroup.auth.roleclassification.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cesgroup.auth.roleclassification.entity.RoleClassification;
import com.cesgroup.auth.roleclassification.vo.RoleClassificationRoleTreeVo;
import com.cesgroup.core.service.BaseService;

public interface RoleClassificationService extends BaseService<RoleClassification>{

	/**
	 * 获取一颗角色分类的同步树
	 * 
	 * @param unitId 单位id
	 * @param rootNode 根结点(可选)
	 * @return 
	 *
	 */
	public List<RoleClassification> getAllRoleClassificationSyncTree(String unitId,RoleClassification rootNode);

	/**
	 * 手动创建根结点
	 * 
	 * <p>描述:手动创建角色分类树的根结点, 根结点的id是-1, 名称由参数定义</p>
	 * @version 1.0.2015.0409
	 * @param name 根结点名称
	 * @return
	 *
	 */
	public RoleClassification createRootNode(String name);

	/**
	 * 角色分类的拖动排序
	 * 
	 * <p>Company:上海中信信息发展股份有限公司</p>
	 * @param sortBeforeIDs 列表拖动前的ids
	 * @param sortAfterIDs 列表拖动后的ids
	 * @param beforeSortPublicFirstId 列表拖动前第一个公共角色分类id
	 * @return
	 *
	 */
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId, String unitId);

	/**
	 * 获得一颗角色分类和角色的组合树
	 * 
	 * <p>描述:</p>
	 * @param unitId 单位id
	 * @param userId 用户id
	 * @return
	 *
	 */
	public List<RoleClassificationRoleTreeVo> getRoleclassificationRoleTree(String unitId, String userId);

	/**
	 * 根据单位id获取角色分类
	 * 
	 * <p>描述:</p>
	 * @param unitId 单位id
	 * @return
	 *
	 */
	List<RoleClassification> getRoleClassificationByUnitId(String unitId);

}
