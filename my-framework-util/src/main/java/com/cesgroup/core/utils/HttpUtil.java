package com.cesgroup.core.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HttpClient请求工具类
 * <p>
 * 描述:HttpClient的封装，用于后台模拟发送http请求。可发送post和get请求
 * </p>
 * <p>
 * Company:红星美凯龙家居股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2017/3/30 20:11
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);


    public static synchronized CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
		/*return HttpClients.createDefault();*/
    }


    public static void main(String[] args) {

        Map<String,String> params = new HashMap<String, String>();

//        params.put("userId", "788253713260220416");
        params.put("unitId","8a8b97935c489711015c4897b6b30001");


//        String url = "http://127.0.0.1:8087/api/operation/audit/queryAccUserInfoById";
        String url = "http://10.11.25.172/ehrBenchBack/api/auth/user/getAllUserResource";
        String post = post(url, params);
        JSONObject jsonObject = JSONObject.parseObject(post);
        Object object = jsonObject.get("data");

        System.out.println(object);
    }

    /**
     * 功能描述:
     *
     * @param httpclient
     * @param url
     * Author:   guan.jun(管俊 <a href="mailto:guan.jun@chinaredstar.com">guan.jun@chinaredstar.com</a>)
     * Date:     2016年10月20日 下午3:34:04
     */
    private static String post(String url, List<NameValuePair> parameters, CloseableHttpClient httpclient) {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding","gzip, deflate, sdch");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpPost.setHeader("Cache-Control","no-cache");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
//        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.setEntity(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
        try {
            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(50000).build();
            httpPost.setConfig(requestConfig);
            logger.info("访问地址 :{},请求参数：{}",url,JSONObject.toJSON(parameters));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
//            response = httpclient.execute(webservicePost);
//            String newURL=response.getFirstHeader("Location").getValue();
//            return get(address,newURL,httpclient);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述:
     *
     * @param httpClient
     * @param url
     * Author:   guan.jun(管俊 <a href="mailto:guan.jun@chinaredstar.com">guan.jun@chinaredstar.com</a>)
     * Date:     2016年10月20日 下午3:34:02
     */
    private static String get(String url,CloseableHttpClient httpClient) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control","no-cache");
//        httpGet.setHeader("Host",address.substring(address.lastIndexOf("http://")+7));
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        try {
            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(50000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(50000).build();
            httpGet.setConfig(requestConfig);
            logger.info("访问地址 :{}",url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String url,Map<String,String> params) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();

        if(params != null && params.size() > 0){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return post(url, parameters, HttpClients.createDefault());
    }

    public static String get(String url) {
        return get(url, getHttpClient());
    }


    public static String webservicePost(String sourceUrl, String actionName, String paramStr) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
//        String sourceUrl = "http://172.16.4.181:8000/PSIGW/PeopleSoftServiceListeningConnector/PSFT_HR";
        String result = "";
        try {
            if (StringUtils.isBlank(sourceUrl)) {
                throw (new Exception("url请求地址为空"));
            }

            httpClient = HttpClients.createDefault();
//            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(sourceUrl);
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(60000).
                    setConnectTimeout(60000).
                    setConnectionRequestTimeout(60000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
//            webservicePost.setHeader("SOAPAction", "GETHEADCOUNT.v1");
            httpPost.setHeader("SOAPAction", actionName);
            httpPost.setHeader("Accept-Encoding", "gzip,deflate");

            StringEntity requestEntity = new StringEntity(paramStr, "text/xml", "utf-8");
            httpPost.setEntity(requestEntity);

            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                result = statusLine.getStatusCode() + "," + statusLine.getReasonPhrase();
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 将源码流保存在一个byte数组当中，因为可能需要两次用到该流，
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    // 如果头部Content-Type中包含了编码信息
                    String charSet = EntityUtils.getContentCharSet(entity);
                    if (StringUtils.isBlank(charSet)) {
                        String regEx = "(?=<meta).*?(?<=charset=[\\'|\\\"]?)([[a-z]|[A-Z]|[0-9]|-]*)";
                        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(new String(bytes, "UTF-8"));
                        if (matcher.find() && matcher.groupCount() == 1) {
                            charSet = matcher.group(1);
                        }
                    }

                    if (StringUtils.isNotBlank(charSet)) {
                        result = new String(bytes, charSet);
                    } else {
                        result = new String(bytes, "UTF-8");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
