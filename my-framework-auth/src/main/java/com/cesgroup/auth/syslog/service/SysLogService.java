package com.cesgroup.auth.syslog.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.core.service.BaseService;

/**
 * 登录日志服务接口
 * @author tml
 *
 */
public interface SysLogService extends BaseService<SysLogEntity> {

	void export(List<SysLogEntity> sysLogs, HttpServletRequest request, HttpServletResponse response, String fileName);

}
