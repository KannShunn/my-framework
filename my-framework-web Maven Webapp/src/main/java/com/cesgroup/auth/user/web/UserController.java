package com.cesgroup.auth.user.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import sun.misc.BASE64Encoder;

import com.cesgroup.auth.genertor.service.GeneratorService;
import com.cesgroup.auth.org.entity.Org;
import com.cesgroup.auth.org.service.OrgService;
import com.cesgroup.auth.org.vo.OrgGridVo;
import com.cesgroup.auth.role.service.RoleService;
import com.cesgroup.auth.role.vo.RoleUserGridVo;
import com.cesgroup.auth.roleclassification.service.RoleClassificationService;
import com.cesgroup.auth.roleclassification.vo.RoleClassificationRoleTreeVo;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.auth.user.vo.UserImageVo;
import com.cesgroup.common.global.Constants;
import com.cesgroup.common.global.ContextPathManager;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.ImageUtil;
import com.cesgroup.core.utils.JsonMapper;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.utils.Util;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;

/**
 * 用户相关action
 * 
 * @author niklaus
 *
 */
@Controller
@RequestMapping(value = "/auth/user")
public class UserController extends AuthBaseController<User, UserService>
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// 依赖注入开始
	@Autowired
	@Override
	public void setService(UserService service) {
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "用户管理";
	}

	@Autowired
	RoleClassificationService roleClassificationService;
	@Autowired
	OrgService orgService;
	@Autowired
	RoleService roleService;
	@Autowired(required = false)
	GeneratorService generatorService;

	//依赖注入结束
	
	/**
	 * 点击用户管理，跳转center中的用户页面 /WEB-INF/views/auth/user/index.jsp
	 * 超级管理员则跳转到/WEB-INF/views/auth/user/superIndex.jsp
	 * @param orgId
	 *            点击的组织ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	@CesLog(type="用户管理", operate = "进入", message="进入用户管理中间页面, orgId : ${orgId}, orgName : ${orgName}", isLog=false)
	public String index(String orgId, String orgName, Model model){
		
		model.addAttribute("orgId", orgId);
		model.addAttribute("orgName", orgName);
		if(getCurrentUser().getUnitId().equals(Constants.User.SUPER_UNITID)){
			return "auth/user/superIndex";
		}else{
			return "auth/user/index";
		}
		
	}

	/**
	 * 
	 * 根据当前登录人的单位id, 加载该单位的组织机构树
	 *
	 */
	@RequestMapping(value = "/getLeftOrgSyncTree")
	@ResponseBody
	@CesLog(type="用户管理", operate = "加载", message="加载用户管理左侧树", isLog=false)
	@RequiresPermissions(value = "/auth/user/getLeftOrgSyncTree")
	public List<Org> getLeftOrgSyncTree(){
		ShiroUser user = getCurrentUser();
		String unitId = user.getUnitId();
		return getService().getLeftOrgSyncTree(unitId);
	}
	
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/getUserPage")
	@ResponseBody
	@RequiresPermissions(value = "/auth/user/getUserPage")
	public PageVo getUserPage(
			@RequestParam String orgId,
			@RequestParam(defaultValue = "1",value="P_pageNumber") int pageNumber,
			@RequestParam(defaultValue = "16", value="P_pagesize") int pageSize,
			@RequestParam(defaultValue = "desc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(value = "filter",required=false) String filter){
		List<SearchFilter> sfList = null;
		ShiroUser user= getCurrentUser();
		String unitId=user.getUnitId();
		if (StringUtils.isNotEmpty(filter))
		{
			String[] filterParams = filter.split(";");
			sfList = SearchFilter.parse(filterParams);
		}
		boolean isSuperadmin = false;
		boolean isClickRootNode = false;
		if(Constants.User.SUPER_UNITID.equals(unitId))//超级系统管理员
		{ 
			isSuperadmin = true;
			if(orgId.equals(Constants.Org.TOP)) //说明当前点击的是根结点，这时候需要获取所有单位系统管理员用户
			{
				isClickRootNode = true;
			}
		} 
		else //单位系统管理员
		{
			if(unitId.equals(orgId)) //说明当前单位管理员点击的是自己单位的根结点，这时候需要获取所有该单位下的用户
			{
				isClickRootNode = true;
			}
		}

		PageVo result = getService().getUsersByOrgId(orgId, unitId, pageNumber - 1, pageSize, colName + "_" + sort, sfList, isClickRootNode, isSuperadmin);

		return result;
	}
	
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/create")
	@ResponseBody
	@CesLog(type="用户管理", operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/user/create")
	public User create(User user, String orgId,String roleId){
		setCreateUser(user);
		ShiroUser currentUser = getCurrentUser();
		return getService().create(user, orgId, currentUser.getUnitId(), roleId);
	}
	
	@RequestMapping("/upload")
	@RequiresPermissions(value = "/auth/user/upload")
	public @ResponseBody UserImageVo upload()
	{
		logger.debug("文件上传");
		UserImageVo result = new UserImageVo();
		String dirOri = ContextPathManager.getUploadOriginPath();// 获取源文件保存目录
		String dirSmall = ContextPathManager.getUploadSmallPath();// 缩略图路径
		BASE64Encoder en = new BASE64Encoder();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(getRequest()))
		{
			try
			{
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) getRequest();
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext())
				{
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null)
					{
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (file != null)
						{
							// 重命名上传后的文件名
							String reName = UUID.randomUUID().toString();
							String fileName = dirOri + reName;
							String filtType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
							String filePath =  fileName + "." + filtType;
							result.setUrlPath(reName + "." + filtType);
							FileCopyUtils.copy(file.getBytes(), new FileOutputStream(new File(filePath)));

							//输出缩略图
							BufferedImage bi = ImageUtil.resize(ImageIO.read(file.getInputStream()), Constants.Image.WIDTH, Constants.Image.HEIGHT);
							File smallFile = new File(dirSmall + File.separator +  reName + "." + filtType);
							ImageIO.write(bi, filtType, smallFile);

							result.setImageContent(file.getContentType(), en.encode(file.getBytes()));
							result.setSuccess(true);
						}
					}
				}
			}
			catch (Exception e)
			{
				result.setSuccess(false);
				logger.error("上传出错", e);
			}
		}
		return result;
	}
	
	/**
	 * 初始化密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initPassword", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="用户管理",operate="初始化密码",message="被初始化密码的用户是:[${name}]")
	@RequiresPermissions(value = "/auth/user/initPassword")
	public boolean initPassword(String userIds)
	{
		getService().initPassword(userIds);
		return true;
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changePassword", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="用户管理",operate="修改密码",message="修改了[${name}]的密码")
	@RequiresPermissions(value = "/auth/user/changePassword")
	public boolean changePassword(String password , String id)
	{
		getService().changePassword(password , id);
		return true;
	}

	/**
	 * 修改自身密码
	 *
	 * @return
	 */
	@RequestMapping(value = "/changeSelfPassword", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="用户管理",operate="修改密码",message="[${name}]修改了自己的密码")
	@RequiresPermissions(value = "/auth/user/changeSelfPassword")
	public boolean changeSelfPassword(String oldPassword,String password , String id)
	{
		User loginUser = getService().getOneById(id);
		if(!StringUtils.equals(Util.md5Encode(oldPassword), loginUser.getPassword())){
			throw new RuntimeException("原密码不正确");
		}
		getService().changePassword(password , id);
		return true;
	}

	/**
	 * 打开授予角色的对话框
	 * @return
	 */
	@RequestMapping(value = "/openAccreditRoleDialog")
	@CesLog(type="用户管理", operate = "打开", message="打开授予角色对话框, userId : ${userId}", isLog=false)
	public String openAccreditRoleDialog(@RequestParam String userId, Model model){
		model.addAttribute("userId", userId);
		return "auth/user/accreditRoleDialog";
	}
	
	/**
	 * 生成业务用户授予角色的角色树
	 * @return
	 */
	@RequestMapping(value="/getAccreditRoleTree",produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="用户管理", operate = "加载", message="加载授予角色树, userId : ${userId}", isLog=false)
	@RequiresPermissions(value = "/auth/user/getAccreditRoleTree")
	public List<RoleClassificationRoleTreeVo> getAccreditRoleTree(@RequestParam String userId){
		ShiroUser user = getCurrentUser();
		List<RoleClassificationRoleTreeVo> result = roleClassificationService.getRoleclassificationRoleTree(user.getUnitId(),userId);
		return result;
	}
	
	
	/**
	 * 授予角色保存事件
	 * @return
	 */
	@RequestMapping(value="/accreditRole",produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="用户管理",operate="授予角色",message="为用户[${userName}]授予角色: [${addRoleNames}], 移除了角色: [${deleteRoleNames}]")
	@RequiresPermissions(value = "/auth/user/accreditRole")
	public boolean accreditRole(@RequestParam String userId,String addRoleIds,String deleteRoleIds,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd){
		ShiroUser user = getCurrentUser();
		getService().accreditRole(userId,addRoleIds,deleteRoleIds,user.getUnitId(),isTempAccredit,tempAccreditDateStart,tempAccreditDateEnd);
		return true;
	}
	
	/**
	 * 打开查看部门对话框, 并根据用户id加载用户所在的部门
	 *
	 */
	@RequestMapping(value = "/openLookOrgDialog")
	@CesLog(type="用户管理",operate="打开",message="打开查看组织对话框, userId : ${id}", isLog=false)
	@RequiresPermissions(value = "/auth/user/openLookOrgDialog")
	public String openLookOrgDialog(String id, Model model){
		List<OrgGridVo> orgVos = orgService.getOrgByUserId(id);
		model.addAttribute("orgData", JsonMapper.nonDefaultMapper().toJson(orgVos));
		return "auth/user/lookOrgDialog";
	}

	/**
	 * 打开查看部门对话框, 并根据用户id加载用户所拥有的角色
	 *
	 */
	@RequestMapping(value = "/openLookRoleDialog")
	@CesLog(type="用户管理",operate="打开",message="打开查看角色对话框, userId : ${id}", isLog=false)
	@RequiresPermissions(value = "/auth/user/openLookRoleDialog")
	public String openLookRoleDialog(String id, Model model){

		ShiroUser user = getCurrentUser();

		List<RoleUserGridVo> roleUserGridVos = roleService.getRoleUserVoByUnitIdAndUserId(user.getUnitId(),id);
		model.addAttribute("roleData", JsonMapper.nonDefaultMapper().toJson(roleUserGridVos));
		return "auth/user/lookRoleDialog";
	}
	
	
	/**
	 * 打开改变组织对话框
	 *
	 */
	@RequestMapping(value = "/openChangeOrgDialog")
	@CesLog(type="用户管理",operate="打开",message="打开改变组织对话框, userId : ${userId}", isLog=false)
	public String openChangeOrgDialog(@RequestParam String userId,Model model){
		List<OrgGridVo> orgVos = orgService.getOrgByUserId(userId);
		StringBuilder orgIds = new StringBuilder();
		for (int i = 0; i < orgVos.size(); i++) {
			orgIds.append(orgVos.get(i).getId()).append(",");
		}
		model.addAttribute("orgIds", orgIds.substring(0, orgIds.length() -1 ));
		return "auth/user/changeOrgDialog";
	}

	/**
	 * 打开兼职对话框
	 *
	 */
	@RequestMapping(value = "/openPartTimeJobDialog")
	@CesLog(type="用户管理",operate="打开",message="打开兼职对话框, userId : ${userId}", isLog=false)
	public String openPartTimeJobDialog(@RequestParam String userId,Model model){
		List<OrgGridVo> orgVos = orgService.getOrgByUserId(userId);
		StringBuilder orgIds = new StringBuilder();
		for (int i = 0; i < orgVos.size(); i++) {
			orgIds.append(orgVos.get(i).getId()).append(",");
		}
		model.addAttribute("orgIds", orgIds.substring(0, orgIds.length() -1 ));
		return "auth/user/partTimeJobDialog";
	}
	
	/**
	 * 改变用户组织，点击保存事件
	 * 
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/changeOrg")
	@ResponseBody
	@CesLog(type="用户管理", operate="改变组织", message="将[${userName}]的组织从[${oldOrgName}]改变到[${newOrgName}]")
	@RequiresPermissions(value = "/auth/user/changeOrg")
	public boolean changeOrg(@RequestParam String userId,@RequestParam String newOrgId,@RequestParam String oldOrgId)
	{
		getService().changeOrg(userId, newOrgId, oldOrgId, getCurrentUser().getUnitId());
		return true;
	}
	
	
	/**
	 * 用户兼职，点击保存事件
	 * 
	 * @param userId
	 * @param orgIds
	 * @return
	 */
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/partTimeJob")
	@ResponseBody
	@CesLog(type="用户管理", operate="兼职", message="将[${userName}]的兼职到[${orgName}]")
	@RequiresPermissions(value = "/auth/user/partTimeJob")
	public boolean partTimeJob(@RequestParam String userId,@RequestParam String orgIds)
	{
		getService().partTimeJob(userId, orgIds,getCurrentUser().getUnitId());
		return true;
	}

	/**
	 * 撤销兼职
	 * 
	 * @param orgIds
	 * @return
	 */
	@RequestMapping(produces = MediaTypes.JSON_UTF_8, value = "/removePartTimeJob")
	@ResponseBody
	@CesLog(type="用户管理", operate="撤销兼职", message="将[${orgNames}]部门下的[${userNames}]撤销兼职")
	@RequiresPermissions(value = "/auth/user/removePartTimeJob")
	public boolean removePartTimeJob(@RequestParam String userIds,@RequestParam String orgIds,@RequestParam String unitIds){
		
		getService().removePartTimeJob(userIds,orgIds,unitIds,getCurrentUser().getUnitId());
		return true;
	}
	
	/**
	 * 打开启用老用户对话框
	 *
	 */
	@RequestMapping(value = "/openRenewOldManDialog")
	@CesLog(type="用户管理",operate="打开",message="打开启用老用户对话框, orgId : ${orgId}, orgName : ${orgName} ", isLog=false)
	public String openRenewOldManDialog(String orgId, String orgName, Model model){
		model.addAttribute("orgId", orgId);
		model.addAttribute("orgName", orgName);
		return "auth/user/renewOldManDialog";
	}
	
	@RequestMapping(value = "/renewOldToNew")
	@ResponseBody
	@CesLog(type="用户管理", operate="恢复用户", message="在[${orgName}]部门下启用老用户[${userName}]")
	@RequiresPermissions(value = "/auth/user/renewOldToNew")
	public boolean renewOldToNew(String id , String orgId){
		getService().renewOldToNew(id , orgId);
		return true;
	}
	
	@RequestMapping(value = "/resumeUser")
	@ResponseBody
	@CesLog(type="用户管理", operate="恢复用户", message="在新增用户时恢复离职用户[${name}]")
	@RequiresPermissions(value = "/auth/user/resumeUser")
	public boolean resumeUser(User user , String orgId){
		super.setUpdateUser(user);
		getService().resumeUser(user , orgId);
		return true;
	}
	
	@RequestMapping(value = "/updateOldUser")
	@ResponseBody
	@CesLog(type="用户管理", operate="修改老用户", message="修改老用户[${name}]的登录名为:[${loginName}], 工号为:[${jobNo}]")
	@RequiresPermissions(value = "/auth/user/updateOldUser")
	public User updateOldUser(User user){
		return getService().updateOldUser(user);
	}

	@RequestMapping(value = "/isOffJobUser")
	@ResponseBody
	public Map<String,String> isOffJobUser(@RequestParam String loginName){
		ShiroUser user = getCurrentUser();
		return getService().isOffJobUser(loginName,user.getUnitId());
	}


	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/user/list")
	public List<User> list(
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
	@RequiresPermissions(value = "/auth/user/page")
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
	@RequiresPermissions(value = "/auth/user/update")
	public User update(User entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/user/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/user/load")
	public User load(String id){
		return super.load(id);
	}

	@RequestMapping(value = "/unlockUser", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="修改", message="解锁了以下用户:${name}")
	@RequiresPermissions(value = "/auth/user/unlockUser")
	public boolean unlockUser(String id){
		getService().unlockUser(id);
		return true;
	}

	@RequestMapping(value = "/lockUser", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="修改", message="锁定了以下用户:${name}")
	@RequiresPermissions(value = "/auth/user/lockUser")
	public boolean lockUser(String id){
		getService().lockUser(id);
		return true;
	}

	/**
	 * 自动生成工号
	 * @return
     */
	@RequestMapping(value = "/autoGeneratorJobNo", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Map<String,Object> autoGeneratorJobNo(){
		Map<String,Object> result = new HashMap<String, Object>();
		if(generatorService != null){
			String jobNo = generatorService.generatorJobNo();
			result.put("jobNo",jobNo);
		}else {
			throw new RuntimeException("没有实现自动生成接口");
		}
		return result;
	}
}
