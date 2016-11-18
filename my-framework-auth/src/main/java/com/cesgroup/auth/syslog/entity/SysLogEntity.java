package com.cesgroup.auth.syslog.entity;



import java.sql.Date;
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

import com.cesgroup.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 登录日志实体
 * @author tml
 *
 */
@Entity
@Table(name="T_AUTH_SYSLOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysLogEntity implements BaseEntity<String>{
	
	private static final long serialVersionUID = 3907234935593061691L;
	
	
	/**
	 * 记录id（主键）
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;
	/**
	 * 操作用户id
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 登录用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 登录时间
	 */
	@Column(name = "log_date")
	private String logDate;
	/**
	 * 操作
	 */
	@Column(name = "operate")
	private String operate;
	/**
	 * 操作的内容
	 */
	@Column(name = "message")
	private String message;
	/**
	 * 用户的ip
	 */
	@Column(name = "ip")
	private String ip;
	/**
	 * 用户的mac地址
	 */
	@Column(name = "mac")
	private String mac;
	/**
	 * 状态 0: 成功, 1:失败
	 */
	@Column(name = "status")
	private String status;
	/**
	 * 操作人的组织id
	 */
	@Column(name = "unit_id")
	private String unitId;
	
	
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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
	@Override
	public String getName() {
		return null;
	}
	@Override
	public void setName(String name) {
	}
	
	
}

















