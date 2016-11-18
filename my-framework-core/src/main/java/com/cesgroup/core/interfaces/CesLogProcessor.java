package com.cesgroup.core.interfaces;

/**
 * Created by Administrator on 2016-7-13.
 */
public interface CesLogProcessor {


    void saveLog(boolean isLog,boolean isLogin,String type,String operate,String message,String note,boolean isSuccess);

}
