package com.cesgroup.core.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.core.annotation.CesLog;
import com.cesgroup.core.entity.BaseEntity;
import com.cesgroup.core.entity.IModel;
import com.cesgroup.core.service.BaseService;
import com.cesgroup.core.utils.DynamicSpecifications;
import com.cesgroup.core.utils.SearchFilter;
import com.cesgroup.core.vo.PageVo;

/**
 * 提供一些通用的处理方法
 * 
 * @author niklaus
 *
 */
public abstract class BaseController<T extends BaseEntity<String>,Service extends BaseService<T>> implements IModel
{
/*	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;*/

	protected ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	protected ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	protected ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();

	protected HttpServletRequest getRequest()
	{
		return request.get();
	}

	protected HttpServletResponse getResponse()
	{
		return response.get();
	}

	protected HttpSession getSession()
	{
		return session.get();
	}

	protected Service service;

	public Service getService() {
		return service;
	}

	
	public <S extends BaseService> S getService(Class<S> clazz) {
		return getService().getService(clazz);
	}

	public abstract void setService(Service service);

	/**
	 * 每个继承此controller 的子类，方法都会首先执行此类，设置一些必要的属性，方便操作。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ModelAttribute
	public void setBaseController(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		this.request.set(request);// = request;
		this.response.set(response);// = response;
		this.session.set(request.getSession());// = request.getSession();
	}

	public boolean isAjaxRequest(HttpServletRequest request)
	{
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	/**
	 * 返回客户端IP地址
	 *
	 * @return
	 */
	protected String getClientIp()
	{
		String ip = request.get().getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
		{
			return ip;
		}
		ip = request.get().getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
		{
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1)
			{
				return ip.substring(0, index);
			}
			else
			{
				return ip;
			}
		}
		else
		{
			return request.get().getRemoteAddr();
		}
	}

	/**
	 * 使用属性名从session中获取对应的值
	 * 
	 * @param attName
	 *            属性名
	 * @return
	 */
	protected Object getSessionAttributeByName(String attName)
	{
		return getSession().getAttribute(attName);
	}

	/**
	 * 设置 名值对 到当前session中
	 * 
	 * @param attName
	 * @param obj
	 */
	protected void setAttributeToSession(String attName, Object obj)
	{
		if (getSession() != null)
		{
			getSession().setAttribute(attName, obj);
		}
	}

	/**
	 * 移除当前session中对应的名值对
	 * 
	 * @param attName
	 */
	protected void removeAttributeFromSession(String attName)
	{
		if (getSession() != null)
		{
			getSession().removeAttribute(attName);
		}
	}
	
	protected void invalidate()
	{
		if (getSession() != null)
		{
			getSession().invalidate();
		}
	}
	
	@RequestMapping(value = "/create")
	@ResponseBody
	@CesLog(operate="新增", message="新增的名称为:${name}")
	public T create(T entity){
		
		setCreateUser(entity);
		entity = getService().create(entity);
		return entity;
	}	
	
	
	/**
	 * <code>BaseController</code>中的基础方法<p>
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/load")
	@ResponseBody
	public T load(String id){
		return getService().getOneById(id);
	}
	
	/**
	 * <code>BaseController</code>中的基础方法
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	@CesLog(operate="修改", message="修改的名称为:${name}")
	public T update(T entity){
		setUpdateUser(entity);
		entity = getService().update(entity);
		return entity;
	}
	
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	@CesLog(operate="删除", message="删除的名称为:${name}")
	public boolean delete(String id){
		getService().delete(id);
		return true;
	}
	
	/**
	 * <code>BaseController</code>中的基础方法<p>
	 * 根据条件校验唯一性
	 * 
	 * @param id
	 *            如果传入 ID，那么忽略自己
	 * @param filter
	 *            唯一性校验条件
	 * @return
	 */
	@RequestMapping(value = "/checkUnique")
	@ResponseBody
	public boolean checkUnique(String id, String filter){
		return getService().checkUnique(id, filter);
	}

	@RequestMapping(value = "/sort")
	@ResponseBody
	public boolean sort(String sortBeforeIDs, String sortAfterIDs){
		return getService().sort(sortBeforeIDs, sortAfterIDs);
	}

	/**
	 * 
	 * 通用的分页查询方法
	 *
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	public PageVo page(
		@RequestParam(defaultValue = "1", value = "P_pageNumber") Integer pageNumber,
		@RequestParam(defaultValue = "20", value = "P_pagesize") Integer pageSize,
		@RequestParam(defaultValue = "asc", value = "sord") String sort,
		@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
		@RequestParam(required = false) String filter) {
		
		Sort sorter = DynamicSpecifications.parseSorter(colName + "_" + sort);
		PageVo page = PageVo.convertFromSpringJPAPage(getService().getByPage(filter, pageNumber - 1, pageSize, sorter));
		return page;
	}
	
	/**
	 * 
	 * 通用的查询方法(不分页)
	 *
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<T> list(
			@RequestParam(defaultValue = "asc", value = "sord") String sort,
			@RequestParam(defaultValue = "showOrder", value = "P_orders") String colName,
			@RequestParam(required = false) String filter)
	{
		Sort sorter = DynamicSpecifications.parseSorter(colName + "_" + sort);
		List<SearchFilter> sfs = null ;
		if(StringUtils.isNotEmpty(filter)){
			sfs = SearchFilter.parse(filter.split(";"));
		}

		return getService().getAllByCondition(sfs, sorter);
	}
	
	/** 设置创建人,创建时间等尾部信息 */
	protected void setCreateUser(T entity){
	}
	
	/** 设置修改人,修改时间等尾部信息 */
	protected void setUpdateUser(T entity){
	}
}
