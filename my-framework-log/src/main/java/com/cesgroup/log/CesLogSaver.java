package com.cesgroup.log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cesgroup.auth.operatelog.dao.OperateLogDao;
import com.cesgroup.auth.operatelog.entity.OperateLogEntity;
import com.cesgroup.auth.syslog.dao.SysLogDao;
import com.cesgroup.auth.syslog.entity.SysLogEntity;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.facade.CesLogProcessor;
import com.cesgroup.core.utils.DateUtil;
import com.cesgroup.core.utils.Servlets;
import com.cesgroup.core.utils.StrUtil;
import com.cesgroup.shiro.ShiroUser;

/**
 * Created by Administrator on 2016-7-13.
 */
@Component
public class CesLogSaver implements CesLogProcessor{

    @Resource
    HttpServletRequest request;
    @Autowired
    SysLogDao sysLogDao;
    @Autowired
    OperateLogDao operateLogDao;

    @Override
    public void saveLog(boolean isLog,boolean isLogin,String type,String operate,String message,String note,boolean isSuccess){
        if(isLog){
            ShiroUser currentUser = (ShiroUser)  SecurityUtils.getSubject().getPrincipal();
            message = StrUtil.textCut(message, 950, "...");

            if(isLogin){ //记录登录日志
                SysLogEntity sysLog = new SysLogEntity();

                if(currentUser == null){
                    sysLog.setIp(Servlets.getClientIp(request));
                    sysLog.setLogDate(DateUtil.getCurrentDateTime());
                    sysLog.setOperate(operate);
                    sysLog.setMessage("用户 ["+request.getParameter("loginName")+"] 登录");
                    sysLog.setUnitId(null);
                    sysLog.setUserId(null);
                    sysLog.setUserName(null);
                    sysLog.setStatus(Constants.Log.FAILED);
                }else{
                    sysLog.setIp(Servlets.getClientIp(request));
                    sysLog.setLogDate(DateUtil.getCurrentDateTime());
                    sysLog.setOperate(operate);
                    sysLog.setMessage("用户 ["+currentUser.getLoginName()+"] "+sysLog.getOperate());
                    sysLog.setUnitId(StringUtils.isEmpty(currentUser.getUnitId()) ? Constants.User.SUPER_UNITID : currentUser.getUnitId());
                    sysLog.setUserId(currentUser.getId());
                    sysLog.setUserName(currentUser.getName());
                    sysLog.setStatus(Constants.Log.SUCCESS);
                }
                sysLogDao.save(sysLog);

            } else { //记录操作日志
                OperateLogEntity logEntity = new OperateLogEntity();

                logEntity.setUnitId(currentUser.getUnitId());
                logEntity.setType(type);
                logEntity.setOperate(operate);
                logEntity.setMessage(isSuccess ? "成功。" + message : "失败。" + message);
                logEntity.setNote(note);
                logEntity.setUrl(request.getRequestURL().toString());
                logEntity.setLogDate(DateUtil.getCurrentDateTime());
                logEntity.setUserId(currentUser.getId());
                logEntity.setUserName(currentUser.getName());


                operateLogDao.save(logEntity);
            }
        }

    }
}
