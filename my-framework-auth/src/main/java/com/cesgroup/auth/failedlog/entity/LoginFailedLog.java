package com.cesgroup.auth.failedlog.entity;

import javax.persistence.*;

import com.cesgroup.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_auth_login_failed_log")
public class LoginFailedLog implements BaseEntity<String>
{
	private static final long serialVersionUID = -8744509779841158941L;

	@Id
	@GeneratedValue(generator = "uuid")   //指定生成器名称
	@GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
	private String id;
	/**
	 * 登陆名
	 */
	@Column(name = "login_name")
	private String name;
	/**
	 * 登陆的IP
	 */
	@Column(name = "login_ip")
	private String loginIP;

	/**
	 * 登陆失败的时间
	 */
	@Column(name = "login_failed_time")
	private String loginFailedTime;

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
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginFailedTime() {
		return loginFailedTime;
	}

	public void setLoginFailedTime(String loginFailedTime) {
		this.loginFailedTime = loginFailedTime;
	}
}
