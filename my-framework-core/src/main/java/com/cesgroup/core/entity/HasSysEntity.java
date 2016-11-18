package com.cesgroup.core.entity;

/**
 * 是否拥有系统内置数据的实体类, 例如默认角色, 默认资源等重要信息, 不允许用户删除,则实现该接口
 */
public interface HasSysEntity {

    String getIsSystem();

}
