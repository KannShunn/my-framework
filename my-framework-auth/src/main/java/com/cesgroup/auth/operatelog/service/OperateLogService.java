package com.cesgroup.auth.operatelog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.auth.operatelog.entity.OperateLogEntity;
import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.core.service.BaseService;

/**
 * 操作日志服务接口
 * @author niklaus
 *
 */
public interface OperateLogService extends BaseService<OperateLogEntity>{


	void export(List<OperateLogEntity> operateLogs, HttpServletRequest request, HttpServletResponse response, String fileName);
}
