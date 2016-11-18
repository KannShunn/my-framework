package com.cesgroup.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.cesgroup.auth.resource.entity.Resource;
import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;

/**
 * Created by Administrator on 2016-7-20.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;



    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        User user = userService.getUserByLoginName(token.getUsername());
        if (user != null)
        {
            if (Constants.User.LOCKED.equals(user.getFlagAction())){
                throw new DisabledAccountException();
            }
            //设置盐值, 盐值也是从数据表中获取的
            ByteSource credentialsSalt = null;
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName(), user.getUnitId(), user.getIsAdmin(),user.getUrlPath()), user.getPassword(), credentialsSalt, getName());
        }
        else
        {
            throw new UnknownAccountException();
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //从Principal中获取用户的登录信息
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if(Constants.User.SUPERADMIN_ID.equals(shiroUser.getId())){//超级管理员 始终拥有所有权限
            info.addStringPermission("*");
            return info;
        }
        List<Resource> resourceList = userService.getResourcesByUserId(shiroUser.getId(),shiroUser.getUnitId());
        if (resourceList != null)
        {
            for (Resource resource : resourceList)
            {
                info.addStringPermission(resource.getResUrl());
            }
        }
        return info;
    }

    public void doClearCache(PrincipalCollection principals) {
        super.doClearCache(principals);
    }
}
