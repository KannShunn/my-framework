package com.cesgroup.auth.roleclassification.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.auth.role.service.RoleService;
import com.cesgroup.auth.roleclassification.dao.RoleClassificationDao;
import com.cesgroup.auth.roleclassification.entity.RoleClassification;
import com.cesgroup.auth.roleclassification.service.RoleClassificationService;
import com.cesgroup.auth.roleclassification.vo.RoleClassificationRoleTreeVo;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;
import com.cesgroup.core.utils.Collections3;
import com.cesgroup.core.utils.Util;

@Service
@Transactional
public class RoleClassifiactionServiceImpl extends BaseServiceImpl<RoleClassification,RoleClassificationDao> implements RoleClassificationService {

	//依赖注入开始
	@Autowired
	public RoleService roleService;

	@Override
	@Autowired
	public void setDao(RoleClassificationDao roleClassificationDao)
	{
		super.dao = roleClassificationDao;
	}

	//依赖注入结束
	
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<RoleClassification> getAllRoleClassificationSyncTree(String unitId,RoleClassification rootNode) {
		//如果单位id为-1, 代表这是超级系统管理员, 则显示公共角色分类(不包含私有角色分类)
		//如果单位id不为-1, 代表是单位系统管理员, 则显示公共+私有角色分类
		
		List<RoleClassification> result = new ArrayList<RoleClassification>();
		List<String> unitIds = new ArrayList<String>();
		if(unitId == null){
			unitIds.add(Constants.User.SUPER_UNITID);
		} else {
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);
		}
		
		result.addAll(getDao().getByUnitIds(unitIds));
		if(rootNode == null){
			rootNode = this.createRootNode("角色分类管理");
		}
		result.add(rootNode);

		return result;
	}

	
	/**
	 * 手动创建根结点
	 *
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public RoleClassification createRootNode(String name){
		RoleClassification rootNode = new RoleClassification();
		rootNode.setId(Constants.User.SUPER_UNITID);
		rootNode.setName(name);
		rootNode.setRoleClassKey("-1");
		rootNode.setUnitId(Constants.User.SUPER_UNITID);
		
		return rootNode;
	}


	@Override
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId, String unitId) {
		String[] afterIDs = sortAfterIDs.split(",");
		String[] beforeIDs = sortBeforeIDs.split(",");
		if (afterIDs.length == 0 && beforeIDs.length == 0)
			return false;
		RoleClassification roleClassification = null;
		Long orderNo = 100L;
		roleClassification = this.getOneById(beforeIDs[0]); //原列表第一个数据, 取它的排序号, 接下来的排序号以此为基准往上递增
		if(roleClassification.getShowOrder()!=null){
			orderNo = roleClassification.getShowOrder();
		}
		if(Constants.User.SUPER_UNITID.equals(unitId)){ //超级系统管理员维护公用角色, 公用角色的排序号是以100递增

			for (int i = 0; i < afterIDs.length; i++) {
				roleClassification = this.getOneById(afterIDs[i]);
				roleClassification.setShowOrder(orderNo);
				getDao().save(roleClassification);
				orderNo+=100;
			}
		} else { //单位系统管理员维护私有角色, 私有角色的排序号是以1递增
			RoleClassification afterSortFirstRole = this.getOneById(afterIDs[0]);
			if(beforeIDs[0].equals(beforeSortPublicFirstId) && Constants.User.SUPER_UNITID.equals(afterSortFirstRole.getUnitId())){ //特殊情况,原列表上第一条数据是公用角色, 用户拖动私有角色至第一条公用角色之上
				RoleClassification beforeSoreFirstPublicRole = this.getOneById(beforeSortPublicFirstId);
				afterSortFirstRole.setShowOrder(beforeSoreFirstPublicRole.getShowOrder()-30); //减10是为了让这个特殊的角色排序顺序往下降一些,以免让后面来的私有角色的排序号不超出公用排序号
				getDao().save(afterSortFirstRole);
			}else{
				for (int i = 0; i < afterIDs.length; i++) {
					roleClassification = this.getOneById(afterIDs[i]);
					if(Constants.User.SUPER_UNITID.equals(roleClassification.getUnitId())){ //公用角色不排序
						orderNo = roleClassification.getShowOrder();
						orderNo++;
						continue;
					}
					roleClassification.setShowOrder(orderNo);
					getDao().save(roleClassification);
					orderNo++;
				}
			}
		}
		return true;
	}
	
	
	@Override
	public void delete(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String[] idsArray = ids.split(",");
			for (String roleClassificationId : idsArray) {
				//查询子角色分类的数量
				Long childRoleClassificationCount = getDao().getCountByPId(roleClassificationId);
				if(childRoleClassificationCount > 0L){
					throw new RuntimeException("该角色分类下含有子分类, 不能删除");
				}
				RoleClassification entity = getOneById(roleClassificationId);
				if(Constants.Common.YES.equals(entity.getIsSystem())){
					throw new RuntimeException("不能删除系统内置数据");
				}
				//查询该角色分类下的角色数量
				List<Role> roles = roleService.getByRoleClassificationId(roleClassificationId);
				
				for (Role role : roles) {
					roleService.delete(role.getId());
				}
				getDao().delete(entity);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<RoleClassification> getRoleClassificationByUnitId(String unitId)
	{
		List<String> unitIds = new ArrayList<String>();
		if(unitId == null){
			unitIds.add(Constants.User.SUPER_UNITID);
		} else {
			unitIds.add(Constants.User.SUPER_UNITID);
			unitIds.add(unitId);
		}
		return getDao().getByUnitIds(unitIds);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<RoleClassificationRoleTreeVo> getRoleclassificationRoleTree(String unitId, String userId) {
		
		
		List<RoleClassificationRoleTreeVo> result = new ArrayList<RoleClassificationRoleTreeVo>();
		
		
		//先根据单位id查出所有的role，并存入集合中
		List<Role> allRoles = roleService.getRoleByUnitId(unitId);
        Map<String,List<Role>> roleClassificationIdRoleMap = new HashMap<String, List<Role>>();
        for(Role role : allRoles){
        	String roleClassificationId = role.getRoleClassificationId();
        	List<Role> list = null;
        	if(roleClassificationIdRoleMap.containsKey(roleClassificationId)){
        		list = roleClassificationIdRoleMap.get(roleClassificationId);
        	}else{
        		list = new ArrayList<Role>();
        	}
        	list.add(role);
    		roleClassificationIdRoleMap.put(roleClassificationId, list);
        }
        
        //根据单位id查询出所有的角色分类
        List<RoleClassification> allRoleClassifications = getRoleClassificationByUnitId(unitId);
        List<Role> userRoles = null;
        if(userId != null){
        	userRoles = roleService.getRoleByUnitIdAndUserId(unitId, userId);
        }
        
        for(RoleClassification rc : allRoleClassifications){
            result.add(convertEntityToRoleClassificationRoleTreeVo(rc,false));
            //List<Role> list = roleService.getRoleByRoleClassificationId(rc.getId(),tenantId);
            List<Role> list = roleClassificationIdRoleMap.get(rc.getId());
            if(null!=list){
            	for(Role role : list){
            		Boolean isChecked = null;
            		//判断用户是否拥有该角色、当拥有该角色，则在树节点上勾选
            		if(Collections3.isNotEmpty(userRoles)){
            			if(userRoles.contains(role)){
            				isChecked = true;
            			}else{
            				isChecked = false;
            			}
            		}
            		result.add(this.convertEntityToRoleClassificationRoleTreeVo(role,isChecked));
            	}
            }
        }
        
		return result;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private RoleClassificationRoleTreeVo convertEntityToRoleClassificationRoleTreeVo(Object object,Boolean checked) {
		RoleClassificationRoleTreeVo vo = new RoleClassificationRoleTreeVo();
		if(object instanceof RoleClassification){
			RoleClassification rc = (RoleClassification)object;
			
			vo.setId(rc.getId());
			vo.setpId(rc.getpId());
			vo.setName(rc.getName());
			vo.setNocheck(true);
			vo.setType(Constants.TreeNode.ROLECLASSIFCATION_NODE);
            if(Util.notNull(checked)){
            	vo.setChecked(checked);
            }
		}else if(object instanceof Role){
            Role role = (Role)object;
            
			vo.setId(role.getId());
			vo.setpId(role.getRoleClassificationId());
			vo.setName(role.getName());
			vo.setType(Constants.TreeNode.ROLE_NODE);
            if(Util.notNull(checked)){
            	vo.setChecked(checked);
            }
        }
		
		return vo;
	}
	
}
