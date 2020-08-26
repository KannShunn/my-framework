package com.cesgroup.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 跨站脚本攻击过滤类.
 * <p>
 * 描述:跨站脚本攻击过滤类
 * </p>
 * <p>
 * Company:lion_guan
 * </p>
 *
 * @author 管俊(lion_guan@foxmail.com)
 * @version 1.0
 * @date 2017年5月5日12:03:54
 */
public class XSSFilter implements Filter {
	/** errorCode(int):当请求中含有跨站脚本时返回的错误响应代码，默认为405. */
	private int errorCode = 405;
	/** errorMessage(String):当请求中含有跨站脚本时返回的错误响应信息，默认为请求不合法. */
	private String errorMessage = "请求不合法";
	/** errorPage(String):当请求中含有跨站脚本时返回的响应页面，默认无. */
	private String errorPage = null;
	private List<String> regList = null;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-02-20 16:16:20
	 */
	public void destroy() {}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-02-20 16:16:20
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String queryString = httpRequest.getQueryString();
		
		// 如果请求中含有跨站脚本则跳转到错误页面，否则继续请求
		if (queryString != null && !"".equals(queryString)) {
			String temp = htmlEncode(queryString);
			
			if (!temp.equals(queryString)||this.isXSS(queryString)) {
				if (errorPage != null && !"".equals(errorPage)) {
					// 设置错误响应代码
					httpResponse.setStatus(errorCode);
					
					// 跳转到错误页面
					RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
					dispatcher.forward(request, response);
				} else {
					// 直接返回错误响应信息
					httpResponse.sendError(errorCode, errorMessage);
				}
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-02-20 16:16:20
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("errorPage") != null && !"".equals(filterConfig.getInitParameter("errorPage"))) {
			errorPage = filterConfig.getInitParameter("errorPage");
		}
		if (filterConfig.getInitParameter("errorCode") != null && !"".equals(filterConfig.getInitParameter("errorCode"))) {
			try {
				errorCode = Integer.parseInt(filterConfig.getInitParameter("errorCode"));
			} catch (NumberFormatException e) {
				errorCode = 405;
			}
		}
		if (filterConfig.getInitParameter("errorMessage") != null && !"".equals(filterConfig.getInitParameter("errorMessage"))) {
			errorMessage = filterConfig.getInitParameter("errorMessage");
		}
		this.regList = this.getRegList();
	}

	
	/**
	 * 替换注入脚本.
	 * @param str 要替换的脚本
	 * @return 替换后脚本
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-02-20  16:19:22
	 */
	private String htmlEncode(String str) {
		str = str.replaceAll("(<|%3C)[\\s\\S]*([/\\w]*)[\\s\\S]*(>|%3E)", "&lt;$2&gt;");
		//str = str.replaceAll("/((/%3C)|<)[^/n]+((/%3E)|>)/I", "&lt;$2&gt;");
		return str;
	}
	
	private List<String> getRegList(){
		List<String> list = new ArrayList<String>();
		list.add("( \\s|\\S)*(exec(\\s|\\+)+(s|x)p\\w+)(\\s|\\S)*"); //Exec Commond 
		list.add("( \\s|\\S)*((%3C)|<)((%2F)|/)*[a-z0-9%]+((%3E)|>)(\\s|\\S)*");//Simple XSS 
		list.add("( \\s|\\S)*((%65)|e)(\\s)*((%76)|v)(\\s)*((%61)|a)(\\s)*((%6C)|l)(\\s|\\S)*"); //Eval XSS 
		list.add("( \\s|\\S)*((%3C)|<)((%69)|i|I|(%49))((%6D)|m|M|(%4D))((%67)|g|G|(%47))[^\\n]+((%3E)|>)(\\s|\\S)*");//Image XSS 
		list.add("( \\s|\\S)*((%73)|s)(\\s)*((%63)|c)(\\s)*((%72)|r)(\\s)*((%69)|i)(\\s)*((%70)|p)(\\s)*((%74)|t)(\\s|\\S)*");//Script XSS
		//list.add("( \\s|\\S)*([|&;$%@'\"\\'\\\"<>()])(\\s|\\S)*");//Script XSS
		//list.add("( \\s|\\S)*((%27)|(')|(%3D)|(=)|(/)|(%2F)|(\")|((%22)|(-|%2D){2})|(%23)|(%3B)|(;))+(\\s|\\S)*");//SQL Injection​
		return list;
	}
	
	private boolean isXSS(String str){
		for(String reg:regList){
			//String reg = "script|iframe";  
			Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE); 
			if (sqlPattern.matcher(str).find()) {  
		        return true;  
		    }
		}
		return false;
	}
	
/*	public static void main(String[] args) {
		String str = "< script ></ script >";
		str="GET /infoplat5.0/reception/log/front-article!searchTopNList.json?tm=1449042528767&map.type=channel&map.count=10&siteId=408aa1f64eafa736014eafcb34d50000&channelId=408aa1f64eafa736014eb009dab80018&map.orders=clickCount%2CDESC%3Ciframe+src%3Djavasct%3Aalert%28866%29+ HTTP/1.1";
		//System.out.println(str.equals(htmlEncode(str)));
		System.out.println(isXSS(str));
	}*/
}
