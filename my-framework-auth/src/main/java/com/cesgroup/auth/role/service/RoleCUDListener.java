package com.cesgroup.auth.role.service;

import com.cesgroup.auth.role.entity.Role;
import com.cesgroup.core.interfaces.CUDListener;

/**
 * Created by Administrator on 2016-7-12.
 */
public interface RoleCUDListener extends CUDListener<Role>{


    void onGrantUserSuccess(String roleId, String addUserIds,String deleteUserIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

    void onGrantUserError(Exception e,String roleId, String addUserIds,String deleteUserIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

    void onGrantUserComplete(String roleId, String addUserIds,String deleteUserIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

    void onGrantResourceSuccess(String roleId, String addResourceIds,String deleteResourceIds, String unitId);

    void onGrantResourceError(Exception e,String roleId, String addResourceIds,String deleteResourceIds, String unitId);

    void onGrantResourceComplete(String roleId, String addResourceIds,String deleteResourceIds, String unitId);
}
