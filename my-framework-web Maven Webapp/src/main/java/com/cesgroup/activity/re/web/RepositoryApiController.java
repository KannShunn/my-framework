package com.cesgroup.activity.re.web;

import com.cesgroup.activity.service.RepositoryApiService;
import com.cesgroup.common.vo.ResultResponse;
import com.cesgroup.core.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

@RestController
@RequestMapping(value="/activity/repository-api")
public class RepositoryApiController {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryApiController.class);
    
    @Autowired
    RepositoryApiService repositoryApiService;


    @RequestMapping(value="/findLastVersionProcessDefinition",method = RequestMethod.GET)
    public PageVo findLastVersionProcessDefinition(@RequestParam(value = "searchText",required = false) String searchText){
        return PageVo.success(repositoryApiService.findLastVersionProcessDefinition(searchText));
    }


    @RequestMapping(value="/findDeploymentList",method = RequestMethod.GET)
    public PageVo findDeploymentList(@RequestParam(value = "searchText",required = false) String searchText){
        return PageVo.success(repositoryApiService.findDeploymentList(searchText));
    }


    @RequestMapping(value="/deleteDeployment",method = RequestMethod.POST)
    public ResultResponse deleteDeployment(@RequestParam(value = "id") String id){
        repositoryApiService.deleteDeployment(id);
        return ResultResponse.success("成功");
    }

    @RequestMapping(value="/uploadDeployment",method = RequestMethod.POST)
    public ResultResponse uploadDeployment(@RequestParam(value = "Filedata",required = false) MultipartFile filedata,
                                           @RequestParam(value = "filename",required = false) String filename){
        repositoryApiService.uploadDeployment(filedata,filename);
        return ResultResponse.success("成功");
    }
}
