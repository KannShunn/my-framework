package com.cesgroup.auth.test.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2017/5/27 15:47
 */
@Controller
@RequestMapping("/api/test")
public class TestApi {

    @RequestMapping("/test1")
    @ResponseBody
    public Map<String,Object> test1(){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("message","这是一条测试消息");
        return  result;
    }
}
