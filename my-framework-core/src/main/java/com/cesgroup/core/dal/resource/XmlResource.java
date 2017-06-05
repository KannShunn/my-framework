/*
 * Copyright (C), 2013, 上海汽车集团股份有限公司
 * FileName: XmlResource.java
 * Author:   12010065
 * Date:     2013年10月9日 下午2:03:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cesgroup.core.dal.resource;


import com.cesgroup.core.dal.resource.parse.SqlBean;
import com.cesgroup.core.dal.resource.parse.XmlParser;
import com.cesgroup.core.exception.BaseException;
import com.cesgroup.core.exception.ExceptionType;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * SQL资源模板解析层
 * 
 * @author 12010065
 */
public class XmlResource{

    /** SQL模板容器 */
    private static Map<String, SqlBean> sqlContainer = new HashMap<String, SqlBean>();

    /** SQL资源模板 */
    private Resource[] resources;

    /**
     * 获取资源模板
     * 
     * @return 资源模板
     */
    public synchronized Resource[] getResources() {
        return resources;
    }

    /**
     * 设置资源模板
     * 
     * @param resources 资源模板
     */
    public synchronized void setResources(Resource[] resources) {
        this.resources = resources.clone();
    }

    public void init(){
        parseResource();
    }

    /**
     * 
     * 功能描述: 解析SQL模板
     * 
     */
    protected void parseResource() {
        XmlParser.getInstance().parse(getResources(), sqlContainer);
    }

    /**
     * 
     * 功能描述: 根据SQLID获取SQLBean
     * 
     * @param sqlId SQLID
     * @return SQL模板映射
     */
    public static SqlBean getSQL(String sqlId) {
        SqlBean sqlBean = sqlContainer.get(sqlId);
        if (sqlBean == null || sqlBean.getContent() == null || "".equals(sqlBean.getContent())) {
            throw new BaseException("error.dal.003", null, null, ExceptionType.EXCEPTION_DAL);
        }
        return sqlBean;
    }
}
