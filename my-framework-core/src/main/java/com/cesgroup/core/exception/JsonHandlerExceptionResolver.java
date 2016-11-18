package com.cesgroup.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.core.utils.AjaxUtils;

/**
 * 异常处理
 * 
 * @author 国栋
 *
 */
public class JsonHandlerExceptionResolver implements HandlerExceptionResolver
{
//	private static final Logger logger = LoggerFactory.getLogger(JsonHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		ModelAndView mv = new ModelAndView();

		if (isAjaxRequest(request))
		{
			// 只关注两类异常，一类是业务异常
			if (ex instanceof MyException)
			{
				AjaxUtils.rendJson(response, false, ex.getMessage());
			}
			else
			{
				// 其他异常归类为系统异常

			}

			// 或者使用view视图返回
			// FastJsonJsonView view = new FastJsonJsonView();
			// view.setContentType(MediaTypes.JSON_UTF_8);
			// Map<String, Object> attributes = new HashMap<String, Object>();
			// attributes.put("success", Boolean.FALSE);
			// attributes.put("msg", ex.getMessage());
			// view.setAttributesMap(attributes);
			// mv.setView(view);

		}

		return mv;
	}

	public static boolean isAjaxRequest(HttpServletRequest request)
	{
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
}
