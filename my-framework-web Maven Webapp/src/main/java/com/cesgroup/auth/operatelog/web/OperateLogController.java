package com.cesgroup.auth.operatelog.web;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.operatelog.entity.OperateLogEntity;
import com.cesgroup.auth.operatelog.service.OperateLogService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;

/**
 * 操作日志控制类
 * @author niklaus
 *
 */
@Controller
@RequestMapping(value="/auth/log/operateLog")
public class OperateLogController extends AuthBaseController<OperateLogEntity,OperateLogService>{
	

	@Autowired
	@Override
	public void setService(OperateLogService service) {
		super.service=service;
	}

	@Override
	public String getModelName() {
		return "日志管理";
	}

	@RequestMapping(value="/index")
	@CesLog(type="日志管理", operate="进入", message="进入操作日志中间页面", isLog=false)
	public String index(){
		return "auth/operatelog/index";
	}

	@RequestMapping(value="/export",produces=MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="日志管理", operate="导出日志", message="导出操作日志", isLog=false)
	@RequiresPermissions(value = "/auth/log/operateLog/export")
	public boolean export(			
			@RequestParam(defaultValue = "1",value="P_pageNumber") int pageNumber,
			@RequestParam(defaultValue = "16", value="P_pagesize") int pageSize,
			@RequestParam(defaultValue = "desc", value = "sord") String sort,
			@RequestParam(defaultValue = "logDate", value = "P_orders") String colName,
			@RequestParam(value = "filter",required=false) String filter){
		
		PageVo page = super.page(pageNumber, pageSize, sort, colName, filter);
		List<OperateLogEntity> datas = (List<OperateLogEntity>) page.getData();
		getService().export(datas,getRequest(),getResponse(),"操作日志");
		
		return true;
	}



	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/log/operateLog/list")
	public List<OperateLogEntity> list(
			@RequestParam(defaultValue = "asc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(required = false) String filter) {
		return super.list(sort,colName,filter);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/page", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/log/operateLog/page")
	public PageVo page(
			@RequestParam(defaultValue = "1", value = "P_pageNumber") Integer pageNumber,
			@RequestParam(defaultValue = "20", value = "P_pagesize") Integer pageSize,
			@RequestParam(defaultValue = "asc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(required = false) String filter) {
		return super.page(pageNumber,pageSize,sort,colName,filter);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/update", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="修改", message="修改的名称为:${name}")
	@RequiresPermissions(value = "/auth/log/operateLog/update")
	public OperateLogEntity update(OperateLogEntity entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/log/operateLog/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/log/operateLog/load")
	public OperateLogEntity load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/log/operateLog/create")
	public OperateLogEntity create(OperateLogEntity entity){
		return super.create(entity);
	}
	
}
