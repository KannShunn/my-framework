package com.cesgroup.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cesgroup.auth.failedlog.service.LoginFailedLogService;
import com.cesgroup.auth.syslog.dao.SysLogDao;
import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.utils.DateUtil;
import com.cesgroup.core.utils.Servlets;

/**
 * 用于验证码验证的Shiro拦截器在用于身份认证的拦截器之前运行；但是如果验证码验证拦截器失败了，就不需要进行身份认证拦截器流程了；
 * 所以需要修改下如FormAuthenticationFilter身份认证拦截器，当验证码验证失败时不再走身份认证拦截器。
 * 
 * @author 国栋
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter
{

	private static final Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe);
	}

	private Integer maxFailedTimes;

	@Autowired
	private LoginFailedLogService loginFailedLogService;
	@Autowired
	private SysLogDao sysLogDao;

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
	{
		if (request.getAttribute(getFailureKeyAttribute()) != null)
		{
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}

	@Override
	public void setRememberMeParam(String rememberMeParam)
	{
		super.setRememberMeParam(rememberMeParam);
	}

	@Override
	public void setFailureKeyAttribute(String failureKeyAttribute)
	{
		super.setFailureKeyAttribute(failureKeyAttribute);
	}

	@Override
	public void setUsernameParam(String usernameParam)
	{
		super.setUsernameParam(usernameParam);
	}

	@Override
	public void setPasswordParam(String passwordParam)
	{
		super.setPasswordParam(passwordParam);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

		String loginName = (String) token.getPrincipal();
		logger.warn(loginName + "登录失败");

		loginFailedLogService.saveLoginFailedLog(loginName,Servlets.getClientIp((HttpServletRequest) request));
		loginFailedLogService.isOverMaxFailedTimes(loginName,maxFailedTimes);
		return super.onLoginFailure(token, e, request, response);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		SysLogEntity sysLog = new SysLogEntity();

		ShiroUser currentUser = (ShiroUser) subject.getPrincipal();
		logger.warn(currentUser.getLoginName() + "登录成功");

		sysLog.setIp(Servlets.getClientIp((HttpServletRequest) request));
		sysLog.setLogDate(DateUtil.getCurrentDateTime());
		sysLog.setOperate("登录");
		sysLog.setMessage("用户 ["+currentUser.getLoginName()+"] "+sysLog.getOperate());
		sysLog.setUnitId(StringUtils.isEmpty(currentUser.getUnitId()) ? Constants.User.SUPER_UNITID : currentUser.getUnitId());
		sysLog.setUserId(currentUser.getId());
		sysLog.setUserName(currentUser.getName());
		sysLog.setStatus(Constants.Log.SUCCESS);
		sysLogDao.save(sysLog);
		loginFailedLogService.deleteByLoginName(currentUser.getLoginName()); //清除登录失败日志

		return super.onLoginSuccess(token, subject, request, response);
	}


	public Integer getMaxFailedTimes() {
		return maxFailedTimes;
	}

	public void setMaxFailedTimes(Integer maxFailedTimes) {
		this.maxFailedTimes = maxFailedTimes;
	}
}
