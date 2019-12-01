package com.cesgroup.activity.service;

import com.cesgroup.activity.vo.DeploymentVo;
import com.cesgroup.activity.vo.ProcessDefinitionVo;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
 * @date 2019/11/29 18:00
 */
@Service
@Transactional
public class RepositoryApiService {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryApiService.class);
    
    
    @Autowired
    RepositoryService repositoryService;


    /**
     * 请求最新的流程定义列表
     * @return
     */
    public List<ProcessDefinitionVo> findLastVersionProcessDefinition(String searchText){
        logger.info("请求最新的流程定义列表");
        List<ProcessDefinition> list = null;
        if(StringUtils.isEmpty(searchText)){
            list = repositoryService.createProcessDefinitionQuery().latestVersion().list();
        } else {
            list = repositoryService.createProcessDefinitionQuery().processDefinitionKeyLike("%"+searchText+"%").latestVersion().list();
        }
        

        List<ProcessDefinitionVo> resultList = new ArrayList<>(list.size());
        ProcessDefinitionVo processDefinitionVo = null;
        for (ProcessDefinition processDefinition : list) {
            processDefinitionVo = new ProcessDefinitionVo();
            BeanUtils.copyProperties(processDefinition,processDefinitionVo
                    ,new String[]{"definitionIdentityLinkEntities","candidateStarterUserIdExpressions","candidateStarterGroupIdExpressions"});
            resultList.add(processDefinitionVo);
        }
        return resultList;
    }

    public List<DeploymentVo> findDeploymentList(String searchText) {
        List<Deployment> list = null;
        if(StringUtils.isEmpty(searchText)){
            list = repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list();
        } else {
            list = repositoryService.createDeploymentQuery().deploymentNameLike("%"+searchText+"%").orderByDeploymenTime().asc().list();
        }
        List<DeploymentVo> resultList = new ArrayList<>(list.size());
        DeploymentVo deploymentVo = null;
        for (Deployment deployment : list) {
            deploymentVo = new DeploymentVo();
            BeanUtils.copyProperties(deployment,deploymentVo
                    ,new String[]{});
            resultList.add(deploymentVo);
        }
        return resultList;
    }

    /**
     * 删除部署信息
     */
    public void deleteDeployment(String deploymentId){
        repositoryService.deleteDeployment(deploymentId,true);
    }
}
