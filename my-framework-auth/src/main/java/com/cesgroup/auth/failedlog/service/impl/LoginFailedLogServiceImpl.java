package com.cesgroup.auth.failedlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.failedlog.dao.LoginFailedLogDao;
import com.cesgroup.auth.failedlog.entity.LoginFailedLog;
import com.cesgroup.auth.failedlog.service.LoginFailedLogService;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.service.impl.BaseServiceImpl;
import com.cesgroup.core.utils.DateUtil;

/**
 * 登陆失败次数日志服务
 * 
 * @author 国栋
 *
 */
@Service
@Transactional
public class LoginFailedLogServiceImpl extends BaseServiceImpl<LoginFailedLog,LoginFailedLogDao> implements LoginFailedLogService
{
//	private static final Logger LOG = LoggerFactory.getLogger(LoginFailedLogServiceImpl.class);
	
	// 依赖注入///////////////////////////////////////////////////////////////

	@Override
	@Autowired
	public void setDao(LoginFailedLogDao logDao)
	{
		super.dao = logDao;
	}

	@Autowired
	UserService userService;

	/////////////////////////////////////////////////////////////////////////


	@Override
	public Integer getCountByLoginName(String loginName) {
		return getDao().getCountByLoginName(loginName);
	}

	@Override
	public void saveLoginFailedLog(String loginName, String clientIp) {
		LoginFailedLog loginFailedLog = new LoginFailedLog();
		loginFailedLog.setName(loginName);
		loginFailedLog.setLoginIP(clientIp);
		loginFailedLog.setLoginFailedTime(DateUtil.getCurrentDateTime());
		getDao().save(loginFailedLog);
	}

	public boolean isOverMaxFailedTimes(String loginName,Integer maxFailedTimes) {
		Integer failedCount = getCountByLoginName(loginName);

		if(failedCount >= maxFailedTimes){
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				user.setFlagAction(Constants.User.LOCKED);
				userService.update(user);
			}
			return true;
		}
		return false;
	}

	@Override
	public void deleteByLoginName(String loginName){
		getDao().deleteByLoginName(loginName);
	}


}
