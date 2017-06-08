package com.cesgroup.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2017/4/10 16:49
 */
public class ServletUtil {


    /**
     * 获取基础路径
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }


    /** 需过滤的参数列表 */
    public static final String[] PARAM_FILTER_ARRAY = {"_version","Cookie","_appid","_token","_ts"};

    public static final Set<String> PARAM_FILTER_SET = new HashSet<String>(Arrays.asList(PARAM_FILTER_ARRAY));
    /**
     * 描述:获取 request 中请求的内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getQueryString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return request.getQueryString();
            // POST
        } else {
            return getPostQueryString(request);
        }
    }

    /***
     * 获取 get请求参数的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getGetQueryString(HttpServletRequest request)
            throws IOException {
        return request.getQueryString();
    }

    /**
     * 描述:获取 post 请求的 参数
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getPostQueryString(HttpServletRequest request)
            throws IOException {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            if(!PARAM_FILTER_SET.contains(paramName)){
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        //System.out.println("参数：" + paramName + "=" + paramValue);
                        map.put(paramName, paramValue);
                    }
                }
            }
        }
        JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
        return jsonObject.toString();
    }
}
