package com.cesgroup.auth.syslog.web;


import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.auth.syslog.service.SysLogService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 登录日志控制类
 * @author tml
 *
 */
@Controller
@RequestMapping(value="/auth/log/sysLog")
public class SysLogController extends AuthBaseController<SysLogEntity, SysLogService> {
	private static final Logger LOG=LoggerFactory.getLogger(SysLogController.class);

	
	
	@Autowired
	public void setService(SysLogService service) {
		super.service=service;
	}

	@Override
	public String getModelName() {
		return "日志管理";
	}

	@RequestMapping(value="/index")
	@CesLog(type="日志管理", operate = "进入", message="进入登录日志中间页面", isLog=false)
	public String index(){
		return "auth/syslog/index";
	}
	
	
	
	@RequestMapping(value="/export",produces=MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="日志管理", operate = "导出", message="导出登录日志", isLog=false)
	@RequiresPermissions(value = "/auth/log/sysLog/export")
	public boolean export(			
			@RequestParam(defaultValue = "1",value="P_pageNumber") int pageNumber,
			@RequestParam(defaultValue = "16", value="P_pagesize") int pageSize,
			@RequestParam(defaultValue = "desc", value = "sord") String sort,
			@RequestParam(defaultValue = "logDate", value = "P_orders") String colName,
			@RequestParam(value = "filter",required=false) String filter){

		PageVo page = super.page(pageNumber, pageSize, sort, colName, filter);
		List<SysLogEntity> datas = (List<SysLogEntity>) page.getData();
		getService().export(datas,getRequest(),getResponse(),"登录登出日志");
		
		return true;
	}


	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/log/sysLog/list")
	public List<SysLogEntity> list(
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
	@RequiresPermissions(value = "/auth/log/sysLog/page")
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
	@RequiresPermissions(value = "/auth/log/sysLog/update")
	public SysLogEntity update(SysLogEntity entity){
		return super.update(entity);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/log/sysLog/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/log/sysLog/load")
	public SysLogEntity load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/log/sysLog/create")
	public SysLogEntity create(SysLogEntity entity){
		return super.create(entity);
	}
}
