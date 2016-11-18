package com.cesgroup.core.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * ajax 异常返回值约定
 * 
 * @author 国栋
 *
 */
public class AjaxUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(AjaxUtils.class);

	/**
	 * 返回文本
	 * 
	 * @param response
	 * @param content
	 * @throws IOException
	 */
	public static void rendText(HttpServletResponse response, String content) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(content);
	}

	/**
	 * 返回异常的json数据，供前台ajax 判断
	 * 
	 * @param response
	 * @param success
	 * @param message
	 */
	public static void rendJson(HttpServletResponse response, boolean success, String message)
	{
		JSONObject json = new JSONObject();
		json.put("isSuccess", success);
		json.put("message", message);
		try
		{
			rendText(response, json.toString());
		}
		catch (IOException e)
		{
			LOG.error("返回Ajax请求的错误信息失败：" + e.getMessage());
		}
	}
}
