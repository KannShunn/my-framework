package com.cesgroup.auth.user.api;

import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.common.global.Constants;
import com.cesgroup.core.vo.JsonResult;
import com.cesgroup.core.web.NonEntityServiceController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User: 管俊 guan.jun@cesgroup.com.cn
 * Date: 2016-9-4
 * Time: 17:19
 */
@RestController
@RequestMapping(value = "/api/auth/user")
public class UserApi extends NonEntityServiceController{

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Override
    public String getModelName() {
        return "auth匿名访问模块";
    }

    @Autowired
    UserService userService;

    @RequestMapping(value = "/unlock")
    public JsonResult<String> unlock(@RequestParam String loginName){

        JsonResult<String> jsonResult = new JsonResult<String>();
        User user = getService(UserService.class).getUserByLoginName(loginName);
        if (user != null) {
            getService(UserService.class).unlockUser(user.getId());
            jsonResult.setMsg("解锁[" + loginName + "]成功");
            jsonResult.setCode(Constants.Common.SUCCESS);
        } else {
            jsonResult.setMsg("未能解锁[" + loginName + "]用户");
            jsonResult.setCode(Constants.Common.FAILED);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/getAllUserResource",method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> getAllUserResource(@RequestParam String unitId){
        JsonResult<Map<String,Object>> jsonResult = new JsonResult<Map<String,Object>>();
        if(StringUtils.isEmpty(unitId)){
            return jsonResult;
        }
        try {
            List<Map<String, Object>> data = userService.getAllUserResources(unitId);
            jsonResult.setData(data);
            jsonResult.setTotal(data.size());
            jsonResult.setCode(Constants.Common.SUCCESS);
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setCode(Constants.Common.FAILED);
            logger.error("获取用户资源映射关系出错"+unitId,e);
        }
        return jsonResult;
    }

}
