package com.cesgroup.auth.role.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.org.vo.OrgUserTreeVo;
import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.resource.service.ResourceService;
import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.auth.role.service.RoleService;
import com.cesgroup.auth.roleclassification.entity.RoleClassification;
import com.cesgroup.auth.roleclassification.service.RoleClassificationService;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.JsonMapper;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;

@Controller
@RequestMapping(value="/auth/role")
public class RoleController extends AuthBaseController<Role, RoleService>{
	
	/* 依赖注入开始 */
	@Autowired
	public void setService(RoleService service) {
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "角色管理";
	}

	@Autowired
	RoleClassificationService roleClassificationService;
	@Autowired
	ResourceService resourceService;
	@Autowired
	UserService	userService;

	/* 依赖注入结束 */

	@RequestMapping(value="/index")
	@CesLog(type="角色管理", operate = "进入", message="进入角色管理中间页面, roleClassificationId:${roleClassificationId}", isLog=false)
	public String index(String roleClassificationId, Model model){
		if(!Constants.User.SUPER_UNITID.equals(roleClassificationId) ){//点击角色树的根结点是没有作用的
			
			model.addAttribute("roleClassificationId", roleClassificationId);
			
			return "auth/role/index";
		}
		if(Constants.User.SUPER_UNITID.equals(roleClassificationId) &&  Constants.User.SUPERADMIN_ID.equals(getCurrentUser().getId())){
			return "auth/role/rootIndex";
		}
		return "blank";
		
	}

	/**
	 * 获取左侧角色管理的树
	 * 
	 *
	 */
	@RequestMapping(value="/getLeftRoleClassificationSyncTree",produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="角色管理", operate = "加载", message="加载角色管理左侧树", isLog=false)
	@RequiresPermissions(value = "/auth/role/getLeftRoleClassificationSyncTree")
	public List<RoleClassification> getLeftRoleClassificationSyncTree(){
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();

		RoleClassification rootNode = roleClassificationService.createRootNode("角色管理");
		return roleClassificationService.getAllRoleClassificationSyncTree(unitId, rootNode);
	}

	/**
	 * 角色排序
	 * 
	 *
	 */
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/sort")
	@ResponseBody
	@CesLog(type="角色管理", operate = "排序", message="角色排序", isLog=false)
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId){
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();
		return getService().sort(sortBeforeIDs, sortAfterIDs,beforeSortPublicFirstId,unitId);

	}

	/**
	 * 点击授予资源, 加载一颗资源树
	 * 
	 *
	 */
	@RequestMapping(value = "/loadRoleResourceTree")
	@ResponseBody
	@CesLog(type="角色管理", operate = "加载", message="加载授予资源树", isLog=false)
	@RequiresPermissions(value = "/auth/role/loadRoleResourceTree")
	public List<Resource> loadRoleResourceTree(String roleId){
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();
		//获取该角色所拥有的公共资源+私有资源
		Map<String,List<Resource>> resourceOfRole = resourceService.getResourcesOfRole(roleId, unitId);
		/*boolean isShowAuthSystem = false; //角色授予资源不显示系统管理平台节点
		if(roleId.length() < 32 && user.getId().equals(Constants.User.SUPERADMIN_ID)){//超级管理员显示系统管理平台节点
			isShowAuthSystem = true;
		}*/
		boolean isShowAuthSystem = true;
		List<Resource> resData = resourceService.getAllResourceTree(isShowAuthSystem,resourceOfRole,unitId);

		return resData;


	}

	/**
	 * 点击授予资源, 打开授予资源对话框
	 *
	 *
	 */
	@RequestMapping(value = "/openGrantResourceDialog")
	@CesLog(type="角色管理", operate = "加载", message="打开授予资源对话框", isLog=false)
	public String openGrantResourceDialog(String roleId,Model model){
		model.addAttribute("roleId",roleId);
		return "auth/role/grantResourceDialog";
	}

	/**
	 * 授予资源点击保存, 为角色赋予资源
	 * 
	 */
	@RequestMapping(value = "/grantResource",produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="角色管理", operate="授予资源", message="为角色[${roleName}]添加了资源:[${addResourceNames}], 移除了资源:[${deleteResourceNames}]")
	@RequiresPermissions(value = "/auth/role/grantResource")
	public boolean grantResource(String roleId, String addResourceIds,String deleteResourceIds){

		ShiroUser currentUser = super.getCurrentUser();
		String unitId = currentUser.getUnitId();
		boolean result = getService().grantResource(roleId,addResourceIds,deleteResourceIds,unitId);

		return result;
	}

	
	/**
	 * 点击授予用户, 加载一颗组织用户树
	 * 
	 *
	 */
	@RequestMapping(value = "/loadOrgUserTree")
	@CesLog(type="角色管理", operate = "加载", message="加载授予用户树", isLog=false)
	@RequiresPermissions(value = "/auth/role/loadOrgUserTree")
	public String loadOrgUserTree(String roleId,Model model){
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();
		List<OrgUserTreeVo> orgUserData = userService.getAllOrgUserTree(unitId,roleId);

		model.addAttribute("orgUserData",JsonMapper.nonDefaultMapper().toJson(orgUserData) );

		return "auth/role/grantUserDialog";
	}
	
	/**
	 * 授予用户点击保存, 将角色赋予用户
	 * 
	 */
	@RequestMapping(value = "/grantUser",produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="角色管理", operate="授予用户", message="将角色[${roleName}]授予给了用户:[${addUserNames}], 移除了用户:[${deleteUserNames}]")
	@RequiresPermissions(value = "/auth/role/grantUser")
	public boolean grantUser(String roleId, String addUserIds,String deleteUserIds,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd){

		ShiroUser currentUser = super.getCurrentUser();
		String unitId = currentUser.getUnitId();
		
		return getService().grantUser(roleId,addUserIds,deleteUserIds,unitId,isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/role/list")
	public List<Role> list(
			@RequestParam(defaultValue = "asc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(required = false) String filter) {
		return super.list(sort,colName,filter);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/page", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/role/page")
	public PageVo page(
			@RequestParam(defaultValue = "1", value = "P_pageNumber") Integer pageNumber,
			@RequestParam(defaultValue = "20", value = "P_pagesize") Integer pageSize,
			@RequestParam(defaultValue = "asc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(required = false) String filter) {
		return super.page(pageNumber,pageSize,sort,colName,filter);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/update", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="修改", message="修改的名称为:${name}")
	@RequiresPermissions(value = "/auth/role/update")
	public Role update(Role entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/role/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/role/load")
	public Role load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/role/create")
	public Role create(Role entity){
		return super.create(entity);
	}
}
