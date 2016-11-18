package com.cesgroup.auth.user.vo;


/**
 * 
 * 用于前台用户列表显示
 * <p>描述:用于前台用户列表显示</p>
 */
public class UserGridVo {

	
	
	
	public UserGridVo() {
	}

	/**
	 * 用于用户列表的构造
	 */
	public UserGridVo(String id, String loginName, String name, String flagAction,
			String email, String mobile, String jobNo, String unitId,
			String urlPath, String userType, String orgId, String orgName) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.flagAction = flagAction;
		this.email = email;
		this.mobile = mobile;
		this.jobNo = jobNo;
		this.unitId = unitId;
		this.urlPath = urlPath;
		this.userType = userType;
		this.orgId = orgId;
		this.orgName = orgName;
	}

	/**
	 * 用于树节点的构造
	 */
	public UserGridVo(String id, String loginName, String name, String unitId, String urlPath, String userType, String orgId) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.unitId = unitId;
		this.urlPath = urlPath;
		this.userType = userType;
		this.orgId = orgId;
	}
	
	
	/**
	 * 用于查找离职用户的构造
	 */
	public UserGridVo(String id, String name, String unitId, String unitName) {
		this.id = id;
		this.name = name;
		this.unitId = unitId;
		this.unitName = unitName;
	}



	private String id;

	/** 登陆名 */
	private String loginName;
	
	/** 用户名 */
	private String name;

	/** 0 : 未锁定, 1 : 锁定 */
	private String flagAction;
	
	/** 邮箱 */
	private String email;
	
	/** 手机 */
	private String mobile;
	
	/** 工号  */
	private String jobNo;
	
	/** 单位ID */
	private String unitId;
	
	/** 头像路径 */
	private String urlPath; // 图像路径
	
	/** 是否兼职: 0:专职, 1:兼职*/
	private String userType;
	
	/** 部门id */
	private String orgId;
	
	/** 部门名称 */
	private String orgName;

	/** 角色名称 */
	private String roleNames;
	
	/** 单位名称 */
	private String unitName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlagAction() {
		return flagAction;
	}

	public void setFlagAction(String flagAction) {
		this.flagAction = flagAction;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}
