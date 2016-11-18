package com.cesgroup.auth.syslog.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.syslog.dao.SysLogDao;
import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.auth.syslog.service.SysLogService;
import com.cesgroup.core.utils.ExcelUtil;
import com.cesgroup.core.utils.StrUtil;
import com.cesgroup.core.service.impl.BaseServiceImpl;
/**
 * 登录日志服务接口实现类
 * @author tml
 *
 */
@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLogEntity,SysLogDao> implements SysLogService{


	@Override
	@Autowired
	public void setDao(SysLogDao sysLogDao)
	{
		super.dao = sysLogDao;
	}

	@Override
	public void export(List<SysLogEntity> sysLogs, HttpServletRequest request, HttpServletResponse response, String fileName) {
		List<String[]> sheet = new ArrayList<String[]>();
		String[] column = new String[]{"用户名","操作","操作内容","ip","操作时间"};
		sheet.add(column);
		String[] datas = null;
		for (SysLogEntity log : sysLogs) {
			datas = new String[column.length];
			datas[0] = StrUtil.null2Emtpy(log.getUserName());
			datas[1] = StrUtil.null2Emtpy(log.getOperate());
			datas[2] = StrUtil.null2Emtpy(log.getMessage());
			datas[3] = StrUtil.null2Emtpy(log.getIp());
			datas[4] = StrUtil.null2Emtpy(log.getLogDate());
			sheet.add(datas);
		}
		
		try {
			Map<String,List<String[]>> excel = new HashMap<String,List<String[]>>();
			excel.put("登录登出日志",sheet);
			ExcelUtil.exportExcel(request, response, excel, fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

