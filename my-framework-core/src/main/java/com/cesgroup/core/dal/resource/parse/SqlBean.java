/*
 * Copyright (C), 2013, 上海汽车集团股份有限公司
 * FileName: SqlBean.java
 * Author:   12010065
 * Date:     2013年10月9日 下午2:03:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cesgroup.core.dal.resource.parse;

/**
 * 
 * SQL映射
 * 
 * @author 12010065
 */
public class SqlBean {
    /** id */
    private String id;

    /** 是否读取 */
    private boolean isRead;

    /** 具体内容 */
    private String content;

    /** 数据源名称 */
    private String dsName;

    /** 数据库类型 */
    private String dbType;

    /**
     * 构造方法重载
     * 
     * @param id SQLID
     * @param isRead 是否可读
     * @param content BEAN内容
     * @param dsName 数据源名称
     */
    public SqlBean(String id, String isRead, String content, String dsName) {
        super();
        this.id = id;
        if (isRead != null) {
            try {
                this.isRead = Boolean.valueOf(isRead);
            } catch (NumberFormatException e) {
                this.isRead = Boolean.FALSE;
            }
        }
        this.content = content;
        this.dsName = dsName;
    }

    /**
     * 构造方法重载
     * 
     * @param id SQLID
     * @param isRead 是否可读
     * @param content BEAN内容
     * @param dsName 数据源名称
     */
    public SqlBean(String id, boolean isRead, String content, String dsName) {
        super();
        this.id = id;
        this.isRead = isRead;
        this.content = content;
        this.dsName = dsName;
    }

    /**
     * 构造方法
     */
    public SqlBean() {
        super();
    }

    /**
     * 获取ID
     * 
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     * 
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 是否可读
     * 
     * @return 布尔值
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * 设置是否可读
     * 
     * @param isRead 是否可读
     */
    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    /**
     * 获取BEAN内容
     * 
     * @return BEAN内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置BEAN内容
     * 
     * @param content BEAN内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取数据源名称
     * 
     * @return 数据源名称
     */
    public String getDsName() {
        return dsName;
    }

    /**
     * 设置数据源名称
     * 
     * @param dsName 数据源名称
     */
    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    /**
     * 获取数据源类型
     * 
     * @return 数据源类型
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 设置数据源类型
     * 
     * @param dbType 数据源类型
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

}
