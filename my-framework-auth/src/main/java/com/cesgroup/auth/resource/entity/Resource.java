package com.cesgroup.auth.resource.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.HasSysEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;

@Entity
@Table(name="t_auth_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource implements BaseEntity<String>,SortEntity,TailEntity,HasSysEntity
{
	private static final long serialVersionUID = -8752904352765096523L;
	

	//资源ID
	@Id
	@Column(name="resource_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;

	//父资源ID
	@Column(name = "parent_id")
	private String pId;

	//资源URL
	@Column(name = "url")
	private String resUrl;

	//资源名称
	@Column(name = "name")
	private String name;

	//备注
	@Column(name = "comments")
	private String comments;

	//导航URL
	@Column(name = "navigate_url")
	private String navigateUrl;

	//业务URL
	@Column(name = "business_url")
	private String businessUrl;

	//其他URL
	@Column(name = "other_url")
	private String otherUrl;

	//功能
	@Column(name="use_function")
	private String useFunction;

	//图片
	@Column(name="resource_img")
	private String resourceImg;

	//资源代码
	@Column(name = "resourcekey")
	private String resourceKey;

	//排序ID
	@Column(name = "show_order")
	private Long showOrder;

	//资源类型, 0:按钮,1:菜单,2:系统
	@Column(name = "res_type")
	private String resType;

	//模块ID
	@Column(name = "module_id")
	private String moduleId;

	//模块ID
	@Column(name = "source_file")
	private String sourceFile;

	/** 创建人id */
	@Column(name = "create_user_id")
	private String createUserId;

	/** 创建人名称 */
	@Column(name = "create_user_name")
	private String createUserName;

	/** 创建时间 */
	@Column(name = "create_time")
	private String createTime;

	/** 修改人id */
	@Column(name = "update_user_id")
	private String updateUserId;

	/** 修改人名称 */
	@Column(name = "update_user_name")
	private String updateUserName;

	/** 修改时间 */
	@Column(name = "update_time")
	private String updateTime;

	/** 是否系统内置, 0:是, 1:否 */
	@Column(name = "is_system")
	private String isSystem;

	/** 资源是否显示, 0:是, 1:否 */
	@Column(name = "is_default")
	private String isDefault;


	/** 是否显示checkbox属性, 系统节点不显示checkbox, 资源节点显示checkbox */
	@Transient
	private boolean nocheck = false;
	/** 是否选中 */
	@Transient
	private boolean checked = false;
	/** 是否禁选 */
	@Transient
	private boolean chkDisabled = false;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id ;
	}

	@Override
	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	@Override
	public Long getShowOrder() {
		return showOrder;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getNavigateUrl() {
		return navigateUrl;
	}

	public void setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
	}

	public String getBusinessUrl() {
		return businessUrl;
	}

	public void setBusinessUrl(String businessUrl) {
		this.businessUrl = businessUrl;
	}

	public String getOtherUrl() {
		return otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}

	public String getUseFunction() {
		return useFunction;
	}

	public void setUseFunction(String useFunction) {
		this.useFunction = useFunction;
	}

	public String getResourceImg() {
		return resourceImg;
	}

	public void setResourceImg(String resourceImg) {
		this.resourceImg = resourceImg;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean getNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
