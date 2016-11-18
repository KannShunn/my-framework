package com.cesgroup.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.auth.syslog.service.SysLogService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.utils.DateUtil;
import com.cesgroup.core.utils.Servlets;

/**
 * 重写shiro的logout, 做一些自己的业务逻辑
 */
public class AuthLogoutFilter extends LogoutFilter {

    @Autowired
    public SysLogService sysLogService;
    @Autowired
    public ShiroDbRealm shiroDbRealm;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest)request;

        //记录登出日志
        saveLogoutLog(req);

        //清除用户缓存
        shiroDbRealm.doClearCache(SecurityUtils.getSubject().getPrincipals());

        //调用父类preHandle方法，执行退出操作。
        return super.preHandle(request, response);
    }

    private void saveLogoutLog(HttpServletRequest req){
        // 记录登出日志
        ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(currentUser != null){
            SysLogEntity sysLog = new SysLogEntity();
            sysLog.setIp(Servlets.getClientIp(req));
            sysLog.setLogDate(DateUtil.getCurrentDateTime());
            sysLog.setUnitId(StringUtils.isEmpty(currentUser.getUnitId())? Constants.User.SUPER_UNITID : currentUser.getUnitId());
            sysLog.setOperate("登出");
            sysLog.setMessage("用户 "+currentUser.getName()+" 登出");
            sysLog.setUserId(currentUser.getId());
            sysLog.setUserName(currentUser.getName());
            sysLog.setStatus(Constants.Log.SUCCESS);
            sysLogService.create(sysLog);
        }
    }
}
