package com.cesgroup.auth.code.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.auth.code.entity.Code;
import com.cesgroup.auth.code.service.CodeService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.utils.MediaTypes;
import com.cesgroup.core.vo.PageVo;
import com.cesgroup.shiro.ShiroUser;


/**
 * 编码控制类.
 * <p>描述:编码控制类</p>
 */
@Controller
@RequestMapping(value="/auth/code")
public class CodeController extends AuthBaseController<Code, CodeService> {

	
	@Override
	@Autowired
	public void setService(CodeService service) {
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "编码管理";
	}

	/**
	 * 
	 * 进入中间页面
	 *
	 */
	@RequestMapping(value = "/index")
	@CesLog(type="编码管理",operate="进入", message="进入编码管理中间页面", isLog=false)
	public String index(String pId, String code, Model model){
		model.addAttribute("pId",pId);
		model.addAttribute("parentCode",code);
		return "auth/code/index";
	}


	/**
	 * 加载左侧树
	 * @param id
	 * @return
     */
	@RequestMapping(value = "/getAsyncCodeTree")
	@ResponseBody
	@CesLog(type="编码管理",operate="加载", message="获取编码树", isLog=false)
	@RequiresPermissions(value = "/auth/code/getAsyncCodeTree")
	public List<Code> getAsyncCodeTree(String id){
		//异步树实现方式, 暂时不用, 如果有性能问题再使用
/*		if(id == null){
			List<Code> list = new ArrayList<Code>();
			Code rootNode = getService().getRootNode();
			rootNode.setIsParent(true);
			list.add(rootNode);
			return list;
		}

		ShiroUser user = getCurrentUser();
		return getService().getChildren(id,user.getUnitId());*/

		//同步树实现方式, 如果有性能问题, 再切回异步树
		ShiroUser user = getCurrentUser();
		return getService().getByUnitIds(user.getUnitId());
	}


	/**
	 * 
	 * 更改编码的状态
	 *
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	@CesLog(type="编码管理",operate="更改编码状态", message="将编码[${id}]状态更改为${op}")
	@RequiresPermissions(value = "/auth/code/updateStatus")
	public boolean updateStatus(@RequestParam String id,@RequestParam String op){
		getService().updateStatus(id,op);
		return true;
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
     */
	@RequestMapping(value = "/list", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/code/list")
	public List<Code> list(
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
	@RequiresPermissions(value = "/auth/code/page")
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
	@RequiresPermissions(value = "/auth/code/update")
	public Code update(Code entity){
		return super.update(entity);
	}

	@RequestMapping(value = "/getComboboxDataByCode", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getComboboxDataByCode(@RequestParam String code){
		return getService().getComboboxDataByCode(code);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/delete", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	@RequiresPermissions(value = "/auth/code/delete")
	public boolean delete(String id){
		return super.delete(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/load", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@RequiresPermissions(value = "/auth/code/load")
	public Code load(String id){
		return super.load(id);
	}

	/**
	 * 重写父类通用方法,为了加入权限验证
	 */
	@RequestMapping(value = "/create", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	@RequiresPermissions(value = "/auth/code/create")
	public Code create(Code entity){
		return super.create(entity);
	}

	@RequestMapping(value = "/openMoveCodeDialog")
	@CesLog(type="编码管理", operate = "打开", message="打开移动编码对话框", isLog=false)
	public String openMoveCodeDialog(String codeIds,Model model){
		String[] codeIdsArray = codeIds.split(",");
		StringBuilder disabledCodeIds = new StringBuilder();//移动资源, 不能移动到自己的子节点下
		for (String codeId : codeIdsArray) {
			disabledCodeIds.append(codeId).append(",");
			List<Code> allChildCodes = getService().getAllChildCodeById(new ArrayList<Code>(), codeId);
			for (Code ChildCode : allChildCodes) {
				disabledCodeIds.append(ChildCode.getId()).append(",");
			}
		}
		model.addAttribute("disabledCodeIds",disabledCodeIds.substring(0,disabledCodeIds.length()-1));
		return "auth/code/moveCodeDialog";
	}


	@RequestMapping(value = "/moveCode", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	@CesLog(type="编码管理", operate="移动编码", message="将[${name}]移动到 [${newPCodeName}]下")
	@RequiresPermissions(value = "/auth/code/moveCode")
	public boolean  moveCode(String codeIds, String newPId, String newParentCode){
		getService().moveCode(codeIds, newPId, newParentCode);
		return true;
	}
}
