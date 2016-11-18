package com.cesgroup.core.utils;

import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * CSRF token 管理器。
 * 
 * @author 国栋
 *
 */
public final class CSRFTokenManager
{
	/**
	 * token 名字
	 */
	static final String CSRF_PARAM_NAME = "_CSRFToken";

	/**
	 * session中存储token的位置
	 */
	public static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = "_tokenval";

	/**
	 * 从 session 中获取token
	 * 
	 * @param session
	 * @return
	 */
	public static String getTokenForSession(HttpSession session)
	{
		String token = null;
		// 同步当前请求的session
		synchronized (session)
		{
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (!StringUtils.isNotEmpty(token))
			{
				token = random(4);
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}

	/**
	 * 生成 几位 随机数
	 * 
	 * @param n
	 * @return
	 */
	public static String random(int n)
	{
		if (n < 1 || n > 10)
		{
			throw new IllegalArgumentException("生成  " + n + " 位随机数出错");
		}
		Random ran = new Random();
		if (n == 1)
		{
			return String.valueOf(ran.nextInt(10));
		}
		int bitField = 0;
		char[] chs = new char[n];
		for (int i = 0; i < n; i++)
		{
			while (true)
			{
				int k = ran.nextInt(10);
				if ((bitField & (1 << k)) == 0)
				{
					bitField |= 1 << k;
					chs[i] = (char) (k + '0');
					break;
				}
			}
		}
		return new String(chs);
	}

	/**
	 * 从请求中分理处 token
	 * 
	 * @param request
	 * @return
	 */
	public static String getTokenFromRequest(ServletRequest request)
	{
		return request.getParameter(CSRF_PARAM_NAME);
	}

	/**
	 * 单实例
	 */
	private CSRFTokenManager()
	{
	};
}