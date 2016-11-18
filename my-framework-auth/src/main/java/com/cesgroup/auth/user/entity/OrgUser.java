package com.cesgroup.auth.user.entity;


import com.cesgroup.core.entity.BaseEntity;
import org.dozer.Mapping;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_auth_org_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgUser implements BaseEntity<String>
{
	private static final long serialVersionUID = -6768709271585721665L;

	@Id
	@Column(name = "org_user_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	@Mapping("id")
	private String id;
	
	//用户id
	@Column(name = "user_id")
	@Mapping("userId")
	private String userId;
	//部门id
	@Column(name = "organize_id")
	@Mapping("orgId")
	private String organizeId;
	//用户在部门下的排序号
	@Column(name = "user_showorder")
	@Mapping("showOrder")
	private Long showOrder;
	//状态：0表示专职，1表示兼职
	@Mapping("usertype")
	@Column(name = "usertype")
	private String userType;
	//单位ID，用于跨单位兼职
	@Column(name = "unit_id")
	private String unitId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
	}

	public Long getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}
