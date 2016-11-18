package com.cesgroup.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * shiro 验证 密码的 策略
 * <br>
 * 包括加密方式，重试次数等 (重试次数暂未做控制)
 * 
 * @author 国栋
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher
{

	// 登陆失败缓存信息
	private Cache<String, String> loginFailMessage;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager)
	{
		loginFailMessage = cacheManager.getCache("loginFailMessage");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		String loginName = (String) token.getPrincipal();
		loginFailMessage.put(loginName, "用户名或密码不正确");
		boolean matches = super.doCredentialsMatch(token, info);
		return matches;
	}

}
