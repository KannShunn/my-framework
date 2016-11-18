package com.cesgroup.shiro;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable
{
    private static final long serialVersionUID = -1373760761780840081L;
    private String id; //用户id
    private String loginName; //用户登录名
    private String name; //用户名
    private String unitId; //单位id
    private String isAdmin; //是否管理员
    private String urlPath; //头像地址


    public ShiroUser(String id, String loginName, String name, String unitId, String isAdmin,String urlPath) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.unitId = unitId;
        this.isAdmin = isAdmin;
        this.urlPath = urlPath;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString()
    {
        return loginName;
    }

    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode()
    {
        return Objects.hashCode(loginName);
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (loginName == null)
        {
            if (other.loginName != null)
            {
                return false;
            }
        }
        else if (!loginName.equals(other.loginName))
        {
            return false;
        }
        return true;
    }
}
