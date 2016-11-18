package com.cesgroup.auth.role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.HasSysEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 系统管理平台 - 角色实体类
 * <p>描述:角色实体类</p>
 */
@Entity
@Table(name = "t_auth_role")
public class Role implements BaseEntity<String>,SortEntity,TailEntity,HasSysEntity
{
	private static final long serialVersionUID = 1633162337455275412L;

	/** 角色id */
	@Id
	@Column(name = "role_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;

	/** 角色名称 */
	@Column(name = "role_name")
	private String name;

	/** 备注 */
	private String comments;

	/** 角色值 */
	@Column(name = "role_key")
	private String roleKey;

	/** 排序号 */
	@Column(name = "show_order")
	private Long showOrder;

	/** 角色对应的角色分类 */
	@Column(name = "roleclassification_id")
	private String roleClassificationId;

	/** 单位id */
	@Column(name = "unit_id")
	private String unitId;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Long getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	public String getRoleClassificationId() {
		return roleClassificationId;
	}

	public void setRoleClassificationId(String roleClassificationId) {
		this.roleClassificationId = roleClassificationId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "t_role_res", joinColumns = {
//			@JoinColumn(name = "role_id", referencedColumnName = "role_id"),
//			@JoinColumn(name="tenant_id",referencedColumnName = "tenant_id")}, 
//		inverseJoinColumns = {@JoinColumn(name="resource_id",referencedColumnName="resource_id")}
//			)
//	List<Resource> resources;
	
	

	
}
