package com.cesgroup.auth.code.entity;

import javax.persistence.*;

import com.cesgroup.core.entity.HasSysEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 编码实体类
 * <p>描述:编码实体类</p>
 */
@Entity
@Table(name="T_AUTH_CODE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Code implements BaseEntity<String>,SortEntity,TailEntity,HasSysEntity
{

	private static final long serialVersionUID = -1139879813260828660L;

	
	/** id */
	@Id
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;

	/** 父id */
	@Column(name = "parent_id")
	private String pId;
	
	/** 编码名称 */
	private String name;
	
	/** 编码值 */
	private String code;

	/** 父code */
	@Column(name = "parent_code")
	private String parentCode;


	/** 排序号 */
	@Column(name="show_order")
	private Long showOrder;
	
	/** 备注 */
	private String comments;
	
	/** 单位ID */
	@Column(name="unit_id")
	private String unitId;

	/** 是否有效, 0:有效, 1:无效 */
	private String status;
	
	/** 是否是系统内置, 0:是, 1:不是 */
	@Column(name="is_system")
	private String isSystem;
	
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

	@Transient
	private boolean isParent;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean parent) {
		isParent = parent;
	}
}
