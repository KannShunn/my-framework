package com.cesgroup.auth.role.vo;

/**
 *
 * 组织VO对象, 用于显示用户的角色列表
 * <p>描述:组织VO对象, 用于显示用户的角色列表</p>
 * @date 2016-8-2 16:23:32
 */
public class RoleUserGridVo {

    private String id;

    private String roleId;

    private String userId;

    private String unitId;

    private String roleClassificationId;

    private String roleName;

    private String roleKey;

    private String roleClassificationName;

    private String isTempAccredit;

    private String tempAccreditDateStart;

    private String tempAccreditDateEnd;

    public RoleUserGridVo() {
    }

    public RoleUserGridVo(String id, String roleId, String userId, String unitId, String roleClassificationId, String roleName, String roleKey, String roleClassificationName, String isTempAccredit, String tempAccreditDateStart, String tempAccreditDateEnd) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
        this.unitId = unitId;
        this.roleClassificationId = roleClassificationId;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.roleClassificationName = roleClassificationName;
        this.isTempAccredit = isTempAccredit;
        this.tempAccreditDateStart = tempAccreditDateStart;
        this.tempAccreditDateEnd = tempAccreditDateEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRoleClassificationId() {
        return roleClassificationId;
    }

    public void setRoleClassificationId(String roleClassificationId) {
        this.roleClassificationId = roleClassificationId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleClassificationName() {
        return roleClassificationName;
    }

    public void setRoleClassificationName(String roleClassificationName) {
        this.roleClassificationName = roleClassificationName;
    }

    public String getIsTempAccredit() {
        return isTempAccredit;
    }

    public void setIsTempAccredit(String isTempAccredit) {
        this.isTempAccredit = isTempAccredit;
    }

    public String getTempAccreditDateStart() {
        return tempAccreditDateStart;
    }

    public void setTempAccreditDateStart(String tempAccreditDateStart) {
        this.tempAccreditDateStart = tempAccreditDateStart;
    }

    public String getTempAccreditDateEnd() {
        return tempAccreditDateEnd;
    }

    public void setTempAccreditDateEnd(String tempAccreditDateEnd) {
        this.tempAccreditDateEnd = tempAccreditDateEnd;
    }
}
