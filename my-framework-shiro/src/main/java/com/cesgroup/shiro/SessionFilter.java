package com.cesgroup.shiro;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {}
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //      if (httpServletRequest.getSession().getAttribute("user") == null) {  
        if (!SecurityUtils.getSubject().isRemembered()) {
            if (!SecurityUtils.getSubject().isAuthenticated()) {
                //判断session里是否有用户信息
                if (httpServletRequest.getHeader("x-requested-with") != null
                        && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    //如果是ajax请求响应头会有，x-requested-with
                    httpServletResponse.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                    return;
                }
            }
        }
        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
  
    }  
  
}  