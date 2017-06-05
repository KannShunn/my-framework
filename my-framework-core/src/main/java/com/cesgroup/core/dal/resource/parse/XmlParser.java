/*
 * Copyright (C), 2013, 上海汽车集团股份有限公司
 * FileName: XmlParser.java
 * Author:   12010065
 * Date:     2013年10月9日 下午2:03:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cesgroup.core.dal.resource.parse;

import com.cesgroup.core.exception.BaseException;
import com.cesgroup.core.exception.ExceptionType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * SQL模板解析器
 * 
 * @author 12010065
 */
public class XmlParser {
    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(XmlParser.class);

    /** XML解析器 */
    private static XmlParser parser = new XmlParser();

    /** 解析SqlMap后的结果 */
    private Map<String, SqlBean> sqlMapResult = new HashMap<String, SqlBean>();

    /**
     * 构造方法
     */
    private XmlParser() {
    }

    /**
     * 获取XML解析器实例
     * 
     * @return XML解析器实例
     */
    public static XmlParser getInstance() {
        return parser;
    }

    /**
     * SQL解析
     * 
     * @param resources 资源
     * @param sqlContainer SQL容器
     */
    public synchronized void parse(Resource[] resources, Map<String, SqlBean> sqlContainer) {
        parseDocuments(createDocuments(resources));
        sqlContainer.putAll(sqlMapResult);
    }

    /**
     * 解析XML文档 ，填充到Map
     * 
     * @param resources 待解析资源
     * @return 生成的Map对象
     */
    private Map<String, Document> createDocuments(Resource[] resources) {
        Map<String, Document> documents = new HashMap<String, Document>();
        /** 实例化SAX解析器 */
        if (resources != null && resources.length > 0) {
            SAXReader saxReader = new SAXReader();
            /** 遍历资源集，SAX解析XML文档 */
            for (Resource resource : resources) {
                try {
                    String fileName = resource.getFilename();
                    InputStream reader = resource.getInputStream();
                    /** 读取XML文件，获得document对象 */
                    Document doc = saxReader.read(reader);
                    documents.put(fileName, doc);
                } catch (Exception e) {
                    logger.debug("SAXReader parse sqlMap xml error!");
                    throw new BaseException("error.dal.003", e, null, ExceptionType.EXCEPTION_DAL);
                }
            }
        }
        return documents;
    }

    /**
     * 解析XML类型的SQL文档集
     * 
     * @param documents 填充后的Map集合
     */
    private void parseDocuments(Map<String, Document> documents) {
        try {
            Iterator<Map.Entry<String, Document>> it = documents.entrySet().iterator();
            /** 迭代解析SqlMap */
            while (it.hasNext()) {
                Map.Entry<String, Document> entry = it.next();
                logger.debug("Loadding sqlMap.xml :" + entry.getKey());
                parseDocument(entry.getKey(), entry.getValue().getRootElement());
            }
        } catch (Exception e) {
            throw new BaseException("error.dal.003", e, null, ExceptionType.EXCEPTION_DAL);
        }
    }

    /**
     * 解析SQL文件
     * 
     * @param fileName 待解析文件名
     * @param rootElement 根结点
     */
    @SuppressWarnings("unchecked")
    private void parseDocument(String fileName, Element rootElement) {
        if (rootElement != null) {
            String namespace = rootElement.attributeValue("namespace");
            String rootIsRead = rootElement.attributeValue("isRead");
            String rootDsName = rootElement.attributeValue("dsName");
            if (namespace == null || "".equals(namespace)) {
                logger.debug("SqlMap Element must have namespace : " + fileName);
                throw new BaseException("error.dal.003", null, null, ExceptionType.EXCEPTION_DAL);
            }
            List<Element> sqlElements = rootElement.elements();
            /** 循环遍历节点，解析SqlMap */
            for (Element element : sqlElements) {
                /** 读取ID */
                String id = element.attributeValue("id");
                /** 读取可读属性 */
                String isRead = element.attributeValue("isRead");
                /** 读取数据源名称 */
                String dsName = element.attributeValue("dsName");
                /** 判断为空时的处理逻辑 */
                if (isRead == null || "".equals(isRead)) {
                    isRead = rootIsRead;
                }
                if (dsName == null || "".equals(dsName)) {
                    dsName = rootDsName;
                }
                if (id == null || "".equals(id)) {
                    logger.debug("Sql Element must have id : " + fileName);
                    throw new BaseException("error.dal.004", null, null, ExceptionType.EXCEPTION_DAL);
                }
                /** 读取SQL语句内容 */
                String sql = element.getTextTrim();
                sqlMapResult.put(appendSqlId(namespace, id), new SqlBean(id, isRead, sql, dsName));
            }
        }
    }

    /**
     * namespace和SqlID连接成字符串
     * 
     * @param namespace 命名空间
     * @param id SqlID
     * @return 拼接后的SQL串
     */
    private String appendSqlId(String namespace, String id) {
        return new StringBuffer().append(namespace).append(".").append(id).toString();
    }
}
