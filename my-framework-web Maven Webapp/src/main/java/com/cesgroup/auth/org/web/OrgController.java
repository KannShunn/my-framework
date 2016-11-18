package com.cesgroup.auth.org.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.service.OrgService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.JsonMapper;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;

@Controller
@RequestMapping(value = "/auth/org")
public class OrgController extends AuthBaseController<Org,OrgService> {

	@Override
	@Autowired
	public void setService(OrgService service)
	{
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "组织管理";
	}

	/**
	 * 
	 * 进入中间页面
	 *
	 */
	@RequestMapping(value = "/index")
	@CesLog(type="组织管理",operate="进入", message="进入中间页面",isLog=false)
	public String index(String orgId, Model model){
		model.addAttribute("orgId", orgId);
		model.addAttribute("organizeTypeIdData",JsonMapper.nonDefaultMapper().toJson(getService().getorganizeTypeIdData(orgId)));
		return "auth/org/index";
	}

	/**
	 * 
	 * 根据当前登录人的单位id, 加载该单位的组织机构树
	 *
	 */
	@RequestMapping(value = "/getLeftOrgSyncTree")
	@ResponseBody
	@CesLog(type="组织管理",operate="加载", message="加载单位组织树",isLog=false)
	@RequiresPermissions(value = "/auth/org/getLeftOrgSyncTree")
	public List<Org> getLeftOrgSyncTree(){
		ShiroUser user = getCurrentUser();
		String unitId = user.getUnitId();
		return getService().getLeftOrgSyncTree(unitId);
	}

	/**
	 * 打开移动组织的对话框
	 * 
	 * <p>描述:</p>
	 * <p>Company:上海中信信息发展股份有限公司</p>
	 * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
	 * @date 2016年5月4日上午11:02:18
	 * @param moveOrgId 待移动的组织id
	 * @param parentOrgId 待移动的组织的父组织id
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/openMoveOrgDialog")
	@CesLog(type="组织管理",operate="跳转", message="打开移动组织的对话框",isLog=false)
	public String openMoveOrgDialog(@RequestParam String moveOrgId,@RequestParam String parentOrgId, Model model){
		//moveOrgId和parentOrgId在树节点上得分别禁选, 因为选了没意义
		model.addAttribute("orgIds", moveOrgId + "," + parentOrgId);
		return "auth/org/moveOrgDialog";
	}
	
	@RequestMapping(value = "/moveOrg")
	@ResponseBody
	@CesLog(type="组织管理",operate="移动组织", message="将组织[${orgName}]移动到[${targetOrgName}]下")
	@RequiresPermissions(value = "/auth/org/moveOrg")
	public boolean moveOrg(@RequestParam String orgId,@RequestParam String unitId,String targetOrgId, String targetUnitId){
		getService().moveOrg(orgId,unitId,targetOrgId,targetUnitId);
		return true;
	}
	
	
	@RequestMapping(value = "/mergeOrg")
	@ResponseBody
	@CesLog(type="组织管理",operate="组织合并", message="将组织[${mergeOrgNames}]合并为[${name}]")
	@RequiresPermissions(value = "/auth/org/mergeOrg")
	public Org mergeOrg(Org org,String mergeOrgIds){
		return getService().mergeOrg(mergeOrgIds,org);
	}


	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/org/list")
	public List<Org> list(
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
	@RequiresPermissions(value = "/auth/org/page")
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
	@RequiresPermissions(value = "/auth/org/update")
	public Org update(Org entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/org/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/org/load")
	public Org load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/org/create")
	public Org create(Org entity){
		return super.create(entity);
	}
}
