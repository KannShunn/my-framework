package com.cesgroup.auth.failedlog.service;

import com.cesgroup.auth.failedlog.entity.LoginFailedLog;
import com.cesgroup.core.service.BaseService;

/**
 * 登陆失败服务
 * 
 * @author 国栋
 *
 */
public interface LoginFailedLogService extends BaseService<LoginFailedLog>
{

	Integer getCountByLoginName(String loginName);

	void saveLoginFailedLog(String loginName,String clientIp);

	/**
	 * 是否超过最大的失败次数
	 * @param loginName
	 * @param maxFailedTimes
     * @return true:是,false:否
     */
	boolean isOverMaxFailedTimes(String loginName,Integer maxFailedTimes);

	void deleteByLoginName(String loginName);
}
