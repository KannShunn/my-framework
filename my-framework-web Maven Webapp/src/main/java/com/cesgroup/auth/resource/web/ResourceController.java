package com.cesgroup.auth.resource.web;

import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.resource.service.ResourceService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/auth/resource")
public class ResourceController extends AuthBaseController<Resource, ResourceService>{


	@Autowired
	@Override
	public void setService(ResourceService resService) {
		super.service = resService;
	}

	@Override
	public String getModelName() {
		return "资源管理";
	}


	/**
	 * 本方法主要用于加载左侧树
	 * @return
	 */
	@RequestMapping(value="/getLeftSyncResourceTree", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="资源管理", operate = "加载", message="加载左侧资源树", isLog=false)
	@RequiresPermissions(value = "/auth/resource/getLeftSyncResourceTree")
	public List<Resource> getLeftSyncResourceTree() {
		boolean isShowAuthSystem = false; //资源管理模块左侧显示系统管理平台系统
		return getService().getAllResourceTree(isShowAuthSystem,null,null);
	}
	
	@RequestMapping(value = "/loadMoveResourceTree")
	@ResponseBody
	@CesLog(type="资源管理", operate = "加载", message="加载移动资源树", isLog=false)
	@RequiresPermissions(value = "/auth/resource/loadMoveResourceTree")
	public List<Resource> loadMoveResourceTree(){
		boolean isShowAuthSystem = false; //资源管理模块左侧显示系统管理平台系统
		return getService().getAllResourceTree(isShowAuthSystem,null,null);
	}

	@RequestMapping(value = "/openMoveResourceDialog")
	@CesLog(type="资源管理", operate = "打开", message="打开移动资源对话框", isLog=false)
	public String openMoveResourceDialog(String resourceIds,Model model){
		String[] resourceIdsArray = resourceIds.split(",");
		StringBuilder disabledResIds = new StringBuilder();//移动资源, 不能移动到自己的子节点下
		for (String resourceId : resourceIdsArray) {
			disabledResIds.append(resourceId).append(",");
			List<Resource> allChildResources = getService().getAllChildResourceById(new ArrayList<Resource>(), resourceId);
			for (Resource ChildResource : allChildResources) {
				disabledResIds.append(ChildResource.getId()).append(",");
			}
		}
		model.addAttribute("disabledResIds",disabledResIds.substring(0,disabledResIds.length()-1));
		return "auth/resource/moveResourceDialog";
	}

	/**
	 * 本方法的作用是加载中央的index页面
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/index")
	@CesLog(type="资源管理", operate = "进入", message="进入资源管理中间页面, resourceId:${resourceId}", isLog=false)
	public String index(String pId , Model model){
		
		model.addAttribute("pId", pId); //model中填塞的参数是将来在index中发出去的,一路到模块中index的所有参数的接受都是需要用一样的名字
		return "auth/resource/index";
	}
	
	@RequestMapping(value = "/moveResource", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="资源管理", operate="移动资源", message="将资源[${name}] 移动到: [${newPResourceName}]下")
	@RequiresPermissions(value = "/auth/resource/moveResource")
	public boolean  moveResource(String resourceIds , String newPResourceId){
		// resourceId是待移动的资源  newPResourceId是新父资源Id
		getService().moveResource(resourceIds, newPResourceId);
		return true;
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/resource/list")
	public List<Resource> list(
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
	@RequiresPermissions(value = "/auth/resource/page")
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
	@RequiresPermissions(value = "/auth/resource/update")
	public Resource update(Resource entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/resource/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/resource/load")
	public Resource load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/resource/create")
	public Resource create(Resource entity){
		return super.create(entity);
	}
}
