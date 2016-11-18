package com.cesgroup.auth.resource.entity;

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

@Entity
@Table(name = "t_auth_role_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleRes implements BaseEntity<String> {

	private static final long serialVersionUID = 4609603942462156990L;
	
	/** 角色资源id */
	@Id
	@Column(name="role_resource_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;

	/** 角色id */
	@Column(name = "role_id")
	private String roleId;
	
	/** 资源id */
	@Column(name = "resource_id")
	private String resourceId;
	
	/** 单位id */
	@Column(name = "unit_id")
	private String unitId;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	
	
}
