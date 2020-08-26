package com.cesgroup.activity.re.web;

import com.cesgroup.core.annotation.CesLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居集团股份有限公司
 * </p>
 *
 * @author 管俊(lion_guan@foxmail.com)
 * @version 1.0
 * @date 2019/11/29 16:55
 */

@Controller
@RequestMapping(value="/activity/repository-view")
public class RepositoryViewController {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryViewController.class);
    


    /**
     *
     * 进入中间页面
     *
     */
    @RequestMapping(value = "/deploymentIndex")
    @CesLog(type="部署管理",operate="进入", message="进入流程中间页面", isLog=false)
    public String deploymentIndex(Model model){
        return "activity/deployment/index";
    }

    /**
     *
     * 进入中间页面
     *
     */
    @RequestMapping(value = "/processDefinitionIndex")
    @CesLog(type="流程定义管理",operate="进入", message="进入流程中间页面", isLog=false)
    public String processDefinitionIndex(Model model){
        return "activity/processdef/index";
    }

}
