package com.cesgroup.auth.operatelog.service.impl;



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

import com.cesgroup.auth.operatelog.dao.OperateLogDao;
import com.cesgroup.auth.operatelog.entity.OperateLogEntity;
import com.cesgroup.auth.operatelog.service.OperateLogService;
import com.cesgroup.core.utils.ExcelUtil;
import com.cesgroup.core.utils.StrUtil;
import com.cesgroup.core.service.impl.BaseServiceImpl;


/**
 * 操作日志服务接口实现类
 * @author niklaus
 *
 */
@Service
@Transactional
public class OperateLogServiceImpl extends BaseServiceImpl<OperateLogEntity,OperateLogDao> implements OperateLogService{

	@Autowired
	@Override
	public void setDao(OperateLogDao operateLogDao)
	{
		super.dao = operateLogDao;
	}

	@Override
	public void export(List<OperateLogEntity> operateLogs, HttpServletRequest request, HttpServletResponse response, String fileName) {
		List<String[]> sheet = new ArrayList<String[]>();
		String[] column = new String[]{"用户名","用户操作模块","操作动作","操作内容","访问url","备注","操作时间"};
		sheet.add(column);
		String[] datas = null;
		for (OperateLogEntity log : operateLogs) {
			datas = new String[column.length];
			datas[0] = StrUtil.null2Emtpy(log.getUserName());
			datas[1] = StrUtil.null2Emtpy(log.getType());
			datas[2] = StrUtil.null2Emtpy(log.getOperate());
			datas[3] = StrUtil.null2Emtpy(log.getMessage());
			datas[4] = StrUtil.null2Emtpy(log.getUrl());
			datas[5] = StrUtil.null2Emtpy(log.getNote());
			datas[6] = StrUtil.null2Emtpy(log.getLogDate());
			sheet.add(datas);
		}
		
		try {
			Map<String,List<String[]>> excel = new HashMap<String,List<String[]>>();
			excel.put("操作日志",sheet);
			ExcelUtil.exportExcel(request, response, excel, fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
