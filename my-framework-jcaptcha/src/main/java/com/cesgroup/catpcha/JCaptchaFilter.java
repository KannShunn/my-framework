package com.cesgroup.catpcha;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码生车藏起过滤器
 * 
 * @author 国栋
 *
 */
public class JCaptchaFilter extends OncePerRequestFilter
{
	protected void doFilterInternal(ServletRequest req, ServletResponse resp, FilterChain fc) throws ServletException, IOException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String id = request.getRequestedSessionId();
		BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);
		ServletOutputStream out = null;
		try
		{
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
		}
		finally
		{
			if (out != null)
			{
				out.close();
			}
		}
	}
}