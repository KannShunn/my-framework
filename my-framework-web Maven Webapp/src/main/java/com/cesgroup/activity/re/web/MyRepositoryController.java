package com.cesgroup.activity.re.web;

import com.cesgroup.activity.service.MyRepositoryService;
import com.cesgroup.activity.vo.ProcessDefinitionVo;
import com.cesgroup.common.vo.ResultResponse;
import com.cesgroup.core.annotation.CesLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居集团股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2019/11/29 16:55
 */

@Controller
@RequestMapping(value="/activity/repository")
public class MyRepositoryController {

    private static final Logger logger = LoggerFactory.getLogger(MyRepositoryController.class);
    
    @Autowired
    MyRepositoryService myRepositoryService;


    @RequestMapping(value="/findLastVersionProcessDefinition",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse<List<ProcessDefinitionVo>> findLastVersionProcessDefinition(){
        return  ResultResponse.success(myRepositoryService.findLastVersionProcessDefinition());
    }



    /**
     *
     * 进入中间页面
     *
     */
    @RequestMapping(value = "/index")
    @CesLog(type="流程部署",operate="进入", message="进入流程中间页面", isLog=false)
    public String index(Model model){
        return "activity/repository/index";
    }
}
