package com.cesgroup.auth.roleclassification.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.roleclassification.entity.RoleClassification;
import com.cesgroup.auth.roleclassification.service.RoleClassificationService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;

@Controller
@RequestMapping(value="/auth/roleclassification")
public class RoleClassificationController extends AuthBaseController<RoleClassification, RoleClassificationService>{
	private static final Logger logger = LoggerFactory.getLogger(RoleClassificationController.class);

	@Autowired
	public void setService(RoleClassificationService service) {
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "角色分类管理";
	}


	@RequestMapping(value="/getLeftRoleClassificationSyncTree", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="角色分类管理", operate = "加载", message="加载角色分类管理左侧树", isLog=false)
	@RequiresPermissions(value = "/auth/roleclassification/getLeftRoleClassificationSyncTree")
	public List<RoleClassification> getLeftRoleClassificationSyncTree(){
		logger.debug("获取角色分类同步树");
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();
		
		RoleClassification rootNode = getService().createRootNode("角色分类管理");
		return getService().getAllRoleClassificationSyncTree(unitId, rootNode);
	}
	
	@RequestMapping(value="/index")
	@CesLog(type="角色分类管理", operate = "进入", message="进入角色分类管理中间页面, pId:[${pId}]", isLog=false)
	public String index(String pId,Model model){
		model.addAttribute("pId", pId);
		return "auth/roleclassification/index";
	}
	
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/sort")
	@ResponseBody
	@CesLog(type="角色分类管理", operate = "排序", message="角色分类管理排序", isLog=false)
	public boolean sort(String sortBeforeIDs, String sortAfterIDs, String beforeSortPublicFirstId){
		ShiroUser user = super.getCurrentUser();
		String unitId = user.getUnitId();
		return getService().sort(sortBeforeIDs, sortAfterIDs,beforeSortPublicFirstId,unitId);
		
	}


	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/roleclassification/list")
	public List<RoleClassification> list(
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
	@RequiresPermissions(value = "/auth/roleclassification/page")
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
	@RequiresPermissions(value = "/auth/roleclassification/update")
	public RoleClassification update(RoleClassification entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/roleclassification/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/roleclassification/load")
	public RoleClassification load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/roleclassification/create")
	public RoleClassification create(RoleClassification entity){
		return super.create(entity);
	}
}
