package com.cesgroup.auth.user.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cesgroup.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author niklaus
 *
 */
@Entity
@Table(name = "t_auth_role_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleUser implements BaseEntity<String>
{
	private static final long serialVersionUID = 2616781931821632687L;
	
	@Id
	@Column(name = "role_user_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;

	/** 用户id */
	@Column(name="user_id")
	private String userId;

	/** 角色id */
	@Column(name="role_id")
	private String roleId;

	/** 单位id */
	@Column(name="unit_id")
	private String unitId;

	/** 是否临时授权. 0:是,1:否.*/
	@Column(name = "is_temp_accredit")
	private String isTempAccredit;

	/** 临时授权开始时间 */
	@Column(name = "temp_accredit_date_start")
	private String tempAccreditDateStart;

	/** 临时授权结束时间 */
	@Column(name = "temp_accredit_date_end")
	private String tempAccreditDateEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
		
	}

	public String getIsTempAccredit() {
		return isTempAccredit;
	}

	public void setIsTempAccredit(String isTempAccredit) {
		this.isTempAccredit = isTempAccredit;
	}

	public String getTempAccreditDateStart() {
		return tempAccreditDateStart;
	}

	public void setTempAccreditDateStart(String tempAccreditDateStart) {
		this.tempAccreditDateStart = tempAccreditDateStart;
	}

	public String getTempAccreditDateEnd() {
		return tempAccreditDateEnd;
	}

	public void setTempAccreditDateEnd(String tempAccreditDateEnd) {
		this.tempAccreditDateEnd = tempAccreditDateEnd;
	}
}
