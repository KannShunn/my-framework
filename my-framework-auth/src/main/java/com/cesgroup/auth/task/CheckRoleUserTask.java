package com.cesgroup.auth.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.auth.user.dao.RoleUserDao;
import com.cesgroup.core.utils.DateUtil;

/**
 * Created by Administrator on 2016-7-8.
 */
@Transactional
public class CheckRoleUserTask {

    private static final Logger logger = LoggerFactory.getLogger(CheckRoleUserTask.class);

    @Autowired
    RoleUserDao roleUserDao;

    
    
    public void execute(){
        logger.warn(DateUtil.getCurrentDateTime() + " - " + "定期检查过期的权限");
        roleUserDao.deleteExpireRole(DateUtil.getCurrentStringDate());
    }
}
