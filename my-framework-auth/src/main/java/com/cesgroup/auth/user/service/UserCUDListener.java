package com.cesgroup.auth.user.service;

import com.cesgroup.auth.user.entity.User;
import com.cesgroup.core.interfaces.CUDListener;

/**
 * Created by Administrator on 2016-7-12.
 */
public interface UserCUDListener extends CUDListener<User>{

    void onAccreditRoleSuccess(String userId, String addRoleIds,String deleteRoleIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

    void onAccreditRoleError(Exception e,String userId, String addRoleIds,String deleteRoleIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);

    void onAccreditRoleComplete(String userId, String addRoleIds,String deleteRoleIds, String unitId,boolean isTempAccredit,String tempAccreditDateStart,String tempAccreditDateEnd);
}
