package com.cesgroup.auth.roleclassification.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.cesgroup.core.entity.HasSysEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_auth_roleclassification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleClassification implements BaseEntity<String>,SortEntity,TailEntity,HasSysEntity
{


	private static final long serialVersionUID = -5497365592595076299L;
	
	@Id
	@Column(name = "roleclassification_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	/** 主键 */
	private String id;
	
	/** 角色分类名 */
	@Column(name = "name")
	private String name;
	
	/** 父id */
	@Column(name = "parent_id")
	private String pId;
	
	/** 角色分类值 */
	@Column(name = "roleclass_key")
	private String roleClassKey;
	
	/** 备注 */
	@Column(name = "comments")
	private String comments;
	
	/** 排序号 */
	@Column(name = "show_order")
	private Long showOrder;
	
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

	@Transient
	private List<RoleClassification> children = new ArrayList<RoleClassification>();
	
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
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getRoleClassKey() {
		return roleClassKey;
	}
	public void setRoleClassKey(String roleClassKey) {
		this.roleClassKey = roleClassKey;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public Long getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}
	public List<RoleClassification> getChildren() {
		return children;
	}
	public void setChildren(List<RoleClassification> children) {
		this.children = children;
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
}
