package com.cesgroup.common.filter;

import com.cesgroup.core.utils.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用于打印输出请求参数的过滤器.
 * <p>
 * 描述:用于打印输出请求参数的过滤器
 * </p>
 * <p>
 * Company:红星美凯龙家居股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2017/5/5 13:26
 */
public class ParameterFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ParameterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        logger.info("请求地址:{} 请求参数:{}",httpRequest.getRequestURI(), ServletUtil.getQueryString(httpRequest));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
