package com.cesgroup.login.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.auth.failedlog.entity.LoginFailedLog;
import com.cesgroup.auth.failedlog.service.LoginFailedLogService;
import com.cesgroup.common.web.AuthBaseController;
import com.cesgroup.core.utils.Util;

@Controller
public class LoginController extends AuthBaseController<LoginFailedLog, LoginFailedLogService>
{

	@Autowired
	private EhCacheManager cachemanager;

	@Autowired
	@Override
	public void setService(LoginFailedLogService service)
	{
		super.service = service;
	}

	@Override
	public String getModelName() {
		return "登录";
	}

	@RequestMapping(value = "/blank")
	public String blank(){
		return "blank";
	}
	
	@RequestMapping(value = "/index")
	public String index(){
		return "index";
	}


	@RequestMapping(value = "/login")
	public String login(String loginName,Model model)
	{
		if( SecurityUtils.getSubject().getPrincipal() != null){
			return "redirect:index";
		}
		//取出放入缓存中的登录提示信息
		if(Util.notNull(cachemanager.getCache("loginFailMessage").get(loginName))){
			String msg = (String)cachemanager.getCache("loginFailMessage").get(loginName);
			model.addAttribute("msg", msg);
		}
		return "login";
	}


/*	@RequestMapping(value = "/login")
	@CesLog(operate="登录", isLogin=true)
	public void login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, Locale locale) throws IOException, ServletException {

		//获取当前用户
		Subject currentUser = SecurityUtils.getSubject();

		//检验用户是否已经登陆
//		if (!currentUser.isAuthenticated()) {
			//若没有登录, 则把 用户名 和 密码 封装为一个 UsernamePasswordToken 对象.
			UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
			token.setRememberMe(true);

			try {
				//执行登陆操作. 调用 Subject#login(UsernamePasswordToken) 方法.
				currentUser.login(token);
			} catch (IncorrectCredentialsException e){
				getService().saveLoginFailedLog(user.getLoginName(),getClientIp());
				getService().isOverMaxFailedTimes(user.getLoginName());

				request.setAttribute("errorMessage", messageSource.getMessage("error.login.UserNameErrorOrpasswordError", null, locale));
				request.getRequestDispatcher("/").forward(request, response);
				return;
			} catch (UnknownAccountException e){
				request.setAttribute("errorMessage", messageSource.getMessage("error.login.UserNameErrorOrpasswordError", null, locale));
				request.getRequestDispatcher("/").forward(request, response);
				return;//LockedAccountException
			} catch (AuthenticationException e) {
				request.setAttribute("errorMessage", messageSource.getMessage("error.login.defaultError", null, locale));
				request.getRequestDispatcher("/").forward(request, response);
				return;
			}
//		}

		//此处仍旧采用User对象作为HttpSession, 是为了兼容以前的代码.
		ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		user = getService(UserService.class).getOneById(shiroUser.getId());

		//由于shiro的缓存, 多次登录失败并不会立刻锁上,这里加上一层判断
		if (Constants.User.LOCKED.equals(user.getFlagAction())){
			request.setAttribute("errorMessage", messageSource.getMessage("error.login.LockedAccountError", null, locale));
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		user.setLastlogindate(new Timestamp(System.currentTimeMillis()));
		getService(UserService.class).update(user);

		String url = response.encodeRedirectURL(request.getContextPath() + "/index");
		response.sendRedirect(url);
	}*/

	/**
	 * 登陆
	 * 
	 * @param user	前台传入的用户登陆参数
	 * @param model	模型
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
/*	@RequestMapping(value = "/login")
	@CesLog(operate="登录", isLogin=true)
	public void login(@ModelAttribute("user") User user, Model model) throws ServletException, IOException
	{
		
		if(StringUtils.isEmpty(user.getLoginName())){
			return;
		}
		
		
		User loginUser = getService(UserService.class).getUserByLoginName(user.getLoginName());
		if (loginUser == null || !StringUtils.equals(Util.md5Encode(user.getPassword()), loginUser.getPassword()))
		{
			request.setAttribute("errorMessage", "用户名或密码错误");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		if (StringUtils.equals(Constants.User.NOT_ADMIN, loginUser.getIsAdmin()))
		{
			request.setAttribute("errorMessage", "业务用户无法登录");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		if (getCurrentUser() != null) //当前已登录过用户
		{
			if (!StringUtils.equals(getCurrentUser().getLoginName(), user.getLoginName()))
			{
				setAttributeToSession(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME, CSRFTokenManager.random(4));
			}
		}
		else { //当前没用户登录, 则走登录路线
			setAttributeToSession(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME, CSRFTokenManager.random(4));
		}
		
		setAttributeToSession("CURRENTUSER", loginUser);

		String url = response.encodeRedirectURL(request.getContextPath() + "/index");	
		response.sendRedirect(url);
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 记录登出日志
		User currentUser = getCurrentUser();
		if(currentUser != null){
			SysLogEntity sysLog = new SysLogEntity();
			sysLog.setIp(Servlets.getClientIp(request));
			sysLog.setLogDate(DateUtil.getCurrentDateTime());
			sysLog.setUnitId(currentUser.getUnitId() == null ? -1L : currentUser.getUnitId());
			sysLog.setOperate("登出");
			sysLog.setMessage("用户 "+getCurrentUser().getName()+" 登出");
			sysLog.setUserId(currentUser.getId());
			sysLog.setUserName(currentUser.getName());
			sysLogService.create(sysLog);
		}
		
		
		removeAttributeFromSession("CURRENTUSER");
		invalidate();
		String url = resp.encodeRedirectURL(req.getContextPath() + "/logout.jsp");
		resp.sendRedirect(url);
	}*/
}
