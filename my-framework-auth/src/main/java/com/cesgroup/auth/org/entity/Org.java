package com.cesgroup.auth.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.cesgroup.core.entity.HasSysEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 组织实体类
 */
@Entity
@Table(name = "t_auth_org")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Org implements BaseEntity<String>,SortEntity,TailEntity,HasSysEntity
{
	private static final long serialVersionUID = -445688662982548988L;

	@Id
	@Column(name = "organize_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	/** 主键id */
	private String id;
	
	/** 父组织id */
	@Column(name = "parent_id")
	private String pId;
	
	/** 组织名称 */
	@Column(name = "organize_name")
	private String name;
	
	/** 组织备注 */
	private String comments;
	
	/** 排序号 */
	@Column(name = "show_order")
	private Long showOrder;
	
	/** 组织简称 */
	private String abbreviation;
	
	/** 组织代码 */
	@Column(name = "organize_code")
	private String organizeCode;
	
	/** 组织类型（单位：1  部门：2） */
	@Column(name = "organize_type_id")
	private Long organizeTypeId;
	
	/** 是否删除状态（否：0 是：1） */
	@Column(name = "status")
	private String status;
	
	/** 单位ID */
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
	
	/** 保留字段 */
	private String flag1;
	private String flag2;
	private String flag3;
	private String flag4;
	private String flag5;
	private String flag6;
	private String flag7;
	private String flag8;
	private String flag9;
	private String flag10;

	/** 是否系统内置, 0:是, 1:否 */
	@Column(name = "is_system")
	private String isSystem;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
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
	public Long getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getOrganizeCode() {
		return organizeCode;
	}
	public void setOrganizeCode(String organizeCode) {
		this.organizeCode = organizeCode;
	}
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	public String getFlag2() {
		return flag2;
	}
	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	public String getFlag3() {
		return flag3;
	}
	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}
	public String getFlag4() {
		return flag4;
	}
	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}
	public String getFlag5() {
		return flag5;
	}
	public void setFlag5(String flag5) {
		this.flag5 = flag5;
	}
	public String getFlag6() {
		return flag6;
	}
	public void setFlag6(String flag6) {
		this.flag6 = flag6;
	}
	public String getFlag7() {
		return flag7;
	}
	public void setFlag7(String flag7) {
		this.flag7 = flag7;
	}
	public String getFlag8() {
		return flag8;
	}
	public void setFlag8(String flag8) {
		this.flag8 = flag8;
	}
	public String getFlag9() {
		return flag9;
	}
	public void setFlag9(String flag9) {
		this.flag9 = flag9;
	}
	public String getFlag10() {
		return flag10;
	}
	public void setFlag10(String flag10) {
		this.flag10 = flag10;
	}
	public Long getOrganizeTypeId() {
		return organizeTypeId;
	}
	public void setOrganizeTypeId(Long organizeTypeId) {
		this.organizeTypeId = organizeTypeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
}
