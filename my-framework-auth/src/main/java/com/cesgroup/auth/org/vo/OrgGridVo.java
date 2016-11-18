package com.cesgroup.auth.org.vo;

/**
 * 
 * 组织VO对象, 用于显示用户的部门列表
 * <p>描述:组织VO对象, 用于显示用户的部门列表</p>
 * @date 2016年4月27日 下午4:25:49
 */
public class OrgGridVo {



	public OrgGridVo(String orgId, String pid, String name, String abbreviation, String organizeCode, Long organizeTypeId, String comments, String userType, String unitName, String unitCode)
	{
		setId(orgId);
		setpId(pid);
		setName(name);
		setAbbreviation(abbreviation);
		setOrganizeCode(organizeCode);
		setOrganizeTypeId(organizeTypeId);
		setComments(comments);
		setUserType(userType);
		setUnitName(unitName);
		setUnitCode(unitCode);
		
	}

	public OrgGridVo()
	{
	}

	/** 主键id */
	private String id;

	/** 父组织id */
	private String pId;

	/** 组织名称 */
	private String name;

	/** 组织备注 */
	private String comments;

	/** 组织简称 */
	private String abbreviation;

	/** 组织代码 */
	private String organizeCode;

	/** 组织类型（单位：2 部门：1） */
	private Long organizeTypeId;

	/**
	 * 0表示专职，1表示兼职
	 */
	private String userType;
	
	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 单位code
	 */
	private String unitCode;
	
	public String getAbbreviation()
	{
		return abbreviation;
	}

	public String getComments()
	{
		return comments;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getOrganizeCode()
	{
		return organizeCode;
	}

	public Long getOrganizeTypeId()
	{
		return organizeTypeId;
	}

	public String getpId()
	{
		return pId;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setOrganizeCode(String organizeCode)
	{
		this.organizeCode = organizeCode;
	}

	public void setOrganizeTypeId(Long organizeTypeId)
	{
		this.organizeTypeId = organizeTypeId;
	}

	public void setpId(String pId)
	{
		this.pId = pId;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
}
