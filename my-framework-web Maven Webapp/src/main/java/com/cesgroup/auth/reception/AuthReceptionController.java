package com.cesgroup.auth.reception;

import com.cesgroup.auth.user.entity.User;
import com.cesgroup.auth.user.service.UserService;
import com.cesgroup.core.web.NonEntityServiceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * User: 管俊 guan.jun@cesgroup.com.cn
 * Date: 2016-9-4
 * Time: 17:19
 */
@Controller
@RequestMapping(value = "/reception/auth")
public class AuthReceptionController extends NonEntityServiceController{

    @Override
    public String getModelName() {
        return "auth匿名访问模块";
    }

    @RequestMapping(value = "/unlock")
    @ResponseBody
    public Map<String,Object> unlock(@RequestParam String loginName){

       Map<String,Object> result = new HashMap<String, Object>();
       User user = getService(UserService.class).getUserByLoginName(loginName);
       if(user != null){
           getService(UserService.class).unlockUser(user.getId());
           result.put("message","解锁["+loginName+"]成功");
       }else{

           result.put("message","未能解锁["+loginName+"]用户");
       }
        return result;
    }


}
