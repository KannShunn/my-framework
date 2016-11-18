package com.cesgroup.auth.unitchange.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.service.OrgService;
import com.cesgroup.auth.org.vo.OrgGridVo;
import com.cesgroup.auth.unitchange.service.UnitChangeService;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.web.NonEntityController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;

/**
 * 
 * 单位调度控制层
 * <p>描述:单位调度控制层</p>
 */
@Controller
@RequestMapping(value="/auth/unitChange")
public class UnitChangeController extends NonEntityController<UnitChangeService>{

	private static final Logger logger = LoggerFactory.getLogger(UnitChangeController.class);
	
	//依赖注入开始
	@Autowired
	OrgService orgService;
	
	@Autowired
	UserService userService;

	@Override
	@Autowired
	public void setService(UnitChangeService service) {
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "单位调度";
	}

	//依赖注入结束
	
	
	
	@RequestMapping(value="/getUnitChangeTree")
	@ResponseBody
	@CesLog(type="单位调度",operate="加载", message="加载左侧单位调度树",isLog=false)
	@RequiresPermissions(value = "/auth/unitChange/getUnitChangeTree")
	public List<Org> getUnitChangeTree(){
		logger.debug("获取组织树");
		return orgService.getLeftOrgSyncTree(getCurrentUser().getUnitId());
	}

	@RequestMapping(value="/getAllOrgTree")
	@ResponseBody
	@CesLog(type="单位调度",operate="加载", message="加载单位调度全组织树",isLog=false)
	@RequiresPermissions(value = "/auth/unitChange/getAllOrgTree")
	public List<Org> getAllOrgTree(){
		logger.debug("获取组织树");
		return orgService.getAll();
	}

	/**
	 * 点击单位调度, 刷新中间页面
	 * 
	 * @param orgId
	 *            点击的组织ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	@CesLog(type="单位调度",operate="跳转", message="跳转中间页面",isLog=false)
	public String index(String orgId, Model model){
		
		logger.debug("单位调度");
		model.addAttribute("orgId", orgId);
		return "auth/unitchange/index";
	}
	
	
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/getUserPage")
	@ResponseBody
	@CesLog(type="单位调度",operate="加载", message="加载用户列表数据",isLog=false)
	@RequiresPermissions(value = "/auth/unitChange/getUserPage")
	public PageVo getUserPage(
			@RequestParam String orgId,
			@RequestParam(defaultValue = "1",value="P_pageNumber") int pageNumber,
			@RequestParam(defaultValue = "16", value="P_pagesize") int pageSize,
			@RequestParam(defaultValue = "desc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(value = "filter",required=false) String filter){
		List<SearchFilter> sfList = null;
		if (StringUtils.isNotEmpty(filter))
		{
			String[] filterParams = filter.split(";");
			sfList = SearchFilter.parse(filterParams);
		}
		ShiroUser user = getCurrentUser();
		
		PageVo result = userService.getUsersByOrgId(orgId, user.getUnitId(), pageNumber - 1, pageSize, colName + "_" + sort, sfList, false, false);

		return result;
	}

	
	@RequestMapping(value = "/openChangeOrgDialog")
	@CesLog(type="单位调度",operate="加载", message="加载改变组织对话框",isLog=false)
	public String openChangeOrgDialog(@RequestParam String userId,Model model){
		//获得用户专职部门id,用于前台树节点屏蔽
		List<OrgGridVo> orgVos = orgService.getOrgByUserId(userId);
		StringBuilder orgIds = new StringBuilder();
		for (int i = 0; i < orgVos.size(); i++) {
			orgIds.append(orgVos.get(i).getId()).append(",");
		}
		model.addAttribute("orgIds", orgIds.substring(0, orgIds.length() -1 ));
		return "auth/unitchange/changeOrgDialog";
	}
	
	@RequestMapping(value = "/openPartTimeJobDialog")
	@CesLog(type="单位调度",operate="加载", message="加载兼职对话框",isLog=false)
	public String openPartTimeJobDialog(@RequestParam String userId,Model model){
		List<OrgGridVo> orgVos = orgService.getOrgByUserId(userId);
		StringBuilder orgIds = new StringBuilder();
		for (int i = 0; i < orgVos.size(); i++) {
			orgIds.append(orgVos.get(i).getId()).append(",");
		}
		model.addAttribute("orgIds", orgIds.substring(0, orgIds.length() -1 ));
		return "auth/unitchange/partTimeJobDialog";
	}

	@RequestMapping(value = "/changeOrg")
	@ResponseBody
	@CesLog(type="单位调度",operate="改变组织", message="将[${userName}]从旧组织[${oldOrgName}]改变到新组织[${newOrgName}]")
	@RequiresPermissions(value = "/auth/unitChange/changeOrg")
	public boolean changeOrg(@RequestParam String userId,
						@RequestParam String newOrgId,
						@RequestParam String newUnitId,
						@RequestParam String oldOrgId,
						@RequestParam String oldUnitId) {
		 getService().changeOrg(userId,newOrgId,newUnitId,oldOrgId,oldUnitId);
		 return true;
	}
	
	@RequestMapping(value = "/partTimeJob")
	@ResponseBody
	@CesLog(type="单位调度",operate="兼职", message="将[${userName}]从兼职到部门:[${orgNames}]")
	@RequiresPermissions(value = "/auth/unitChange/partTimeJob")
	public boolean partTimeJob(@RequestParam String userId,
						@RequestParam String orgIds, 
						@RequestParam String unitIds) {
		 getService().partTimeJob(userId,orgIds,unitIds);
		 return true;
	}
}
