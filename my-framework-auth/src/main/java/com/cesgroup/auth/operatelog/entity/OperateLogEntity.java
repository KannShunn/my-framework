package com.cesgroup.auth.operatelog.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cesgroup.auth.user.entity.User;
import com.cesgroup.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 操作日志实体
 * @author niklaus
 *
 */
@Entity
@Table(name="T_AUTH_OPERATELOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperateLogEntity implements BaseEntity<String>{
	
	private static final long serialVersionUID = -8713293090183837250L;

	/**
	 * 记录id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;
	/**
	 * 用户操作模块
	 */
	@Column(name = "type")
	private String type;
	/**
	 * 操作用户id
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 操作用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 日志记录时间
	 */
	@Column(name = "log_date")
	private String logDate;
	/**
	 * 操作动作
	 */
	@Column(name = "operate")
	private String operate;
	/**
	 * 操作的内容
	 */
	@Column(name = "message")
	private String message;
	/**
	 * 系统的key
	 */
	@Column(name = "appkey")
	private String appKey;
	/**
	 * 访问的url
	 */
	@Column(name = "url")
	private String url;
	/**
	 * 备注
	 */
	@Column(name = "note")
	private String note;
	/**
	 * 操作人的单位id
	 */
	@Column(name = "unit_id")
	private String unitId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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


















