package com.cesgroup.activity.service;

import com.cesgroup.activity.vo.ProcessDefinitionVo;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MyRepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(MyRepositoryService.class);
    
    
    @Autowired
    RepositoryService repositoryService;


    /**
     * 请求最新的流程定义列表
     * @return
     */
    public List<ProcessDefinitionVo> findLastVersionProcessDefinition(){
        logger.info("请求最新的流程定义列表");
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().latestVersion().list();

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

}
