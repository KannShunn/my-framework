package com.cesgroup.auth.user.entity;

import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.HasSysEntity;
import com.cesgroup.core.entity.SortEntity;
import com.cesgroup.core.entity.TailEntity;
import org.dozer.Mapping;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "t_auth_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements BaseEntity<String>,SortEntity,TailEntity
{
	private static final long serialVersionUID = -1608482771285176994L;

	/** 用户id */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	@Mapping(value = "id")
	private String id;

	/** 登录名 */
	@Column(name = "login_name")
	@Mapping(value = "loginName")
	private String loginName;
	
	/** 密码 */
	@Column(name = "password")
	@Mapping(value = "password")
	private String password;
	
	/** 用户名 */
	@Column(name = "user_name")
	@Mapping(value = "name")
	private String name;

	/** 0 : 未锁定, 1 : 锁定 */
	@Column(name = "flag_action")
	@Mapping(value = "flagAction")

	private String flagAction;
	
	/** 邮箱 */
	@Column(name = "email")
	@Mapping(value = "email")
	//@NotNull(message = "{error.user.email.notnull}")
	private String email;
	
	/** 手机 */
	@Mapping(value = "mobile")
	@Column(name = "mobile")
	private String mobile;
	
	/** 头衔 */
	@Mapping(value = "title")
	@Column(name = "title")
	private String title;
	
	/** 排序号 */
	@Mapping(value = "showOrder")
	@Column(name = "show_order")
	private Long showOrder;

	/** 固定电话 */
	@Mapping(value = "telephone")
	@Column(name = "telephone")
	private String telephone;
	
	/** 备注 */
	@Mapping(value = "comments")
	@Column(name = "comments")
	private String comments;

	/** 用户所在岗位 */
	@Mapping(value = "station")
	private String station;

	/** 判断用户是否在职  0 : 在职, 1 : 离职  */
	@Mapping(value = "status")
	private String status;
	
	/** 工号  */
	@Mapping(value = "jobNo")
	@Column(name = "job_no")
	private String jobNo;
	
	/** 最后登录时间 */
	@Mapping(value = "lastLoginDate")
	@Column(name = "last_login_date")
	private Timestamp lastlogindate;
	
	/** 最后修改密码时间 */
	@Mapping(value = "lastModifyPsd")
	@Column(name = "last_modify_psd")
	private Timestamp lastmodifypsd;
	
	/** 单位ID */
	@Mapping(value = "unitId")
	@Column(name = "unit_id")
	private String unitId;
	
	/** 是否是管理员 0:是, 1:不是 */
	@Mapping(value = "isAdmin")
	@Column(name = "is_admin")
	private String isAdmin;
	
	/** 简称 */
	@Mapping(value = "abbr")
	private String abbr;
	
	/** 身份证号 */
	@Mapping(value = "cardNo")
	@Column(name = "card_no")
	private String cardNo;
	
	/** 0:女, 1:男 */
	@Mapping(value = "sex")
	@Column(name = "sex")
	private String sex;
	
	/** 年龄 */
	@Mapping(value = "age")
	@Column(name = "age")
	private Integer age; 
	
	/** 出生日期 */
	@Mapping(value = "birthday")
	@Column(name = "birthday")
	private Date birthday; 
	
	/** 头像路径 */
	@Mapping(value = "urlPath")
	@Column(name = "urlpath")
	private String urlPath; // 图像路径
	
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

	/** 入职时间 */
	@Column(name = "on_job_date")
	private String onJobDate;

	/** 离职时间 */
	@Column(name = "OFF_JOB_DATE")
	private String offJobDate;

	/** 是否二次入职 */
	@Column(name = "IS_SECOND_ON_JOB")
	private String isSecondOnJob;

	/** 政治面貌 */
	@Column(name = "political")
	private String political;

	@Column(name = "flag1")
	private String flag1;
	@Column(name = "flag2")
	private String flag2;
	@Column(name = "flag3")
	private String flag3;
	@Column(name = "flag4")
	private String flag4;
	@Column(name = "flag5")
	private String flag5;
	@Column(name = "flag6")
	private String flag6;
	@Column(name = "flag7")
	private String flag7;
	@Column(name = "flag8")
	private String flag8;
	@Column(name = "flag9")
	private String flag9;
	@Column(name = "flag10")
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public Timestamp getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public Timestamp getLastmodifypsd() {
		return lastmodifypsd;
	}

	public void setLastmodifypsd(Timestamp lastmodifypsd) {
		this.lastmodifypsd = lastmodifypsd;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getOnJobDate() {
		return onJobDate;
	}

	public void setOnJobDate(String onJobDate) {
		this.onJobDate = onJobDate;
	}
	public String getOffJobDate() {
		return offJobDate;
	}

	public void setOffJobDate(String offJobDate) {
		this.offJobDate = offJobDate;
	}

	public String getIsSecondOnJob() {
		return isSecondOnJob;
	}

	public void setIsSecondOnJob(String isSecondOnJob) {
		this.isSecondOnJob = isSecondOnJob;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
}
