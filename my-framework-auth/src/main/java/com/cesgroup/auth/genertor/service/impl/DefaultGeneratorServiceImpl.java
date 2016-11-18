package com.cesgroup.auth.genertor.service.impl;

import com.cesgroup.auth.genertor.dao.GeneratorDao;
import com.cesgroup.auth.genertor.entity.GeneratorEntity;
import com.cesgroup.auth.genertor.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016-8-4.
 */
public class DefaultGeneratorServiceImpl implements GeneratorService {


    private static final String JOBNO_ID = "1";
    @Autowired
    GeneratorDao generatorDao;

    @Override
    @Transactional
    public String generatorJobNo() {

        GeneratorEntity jobNoGeneratorEntity = generatorDao.findOne(JOBNO_ID);

        String result = jobNoGeneratorEntity.getPrefix() + getNextSysNumber(JOBNO_ID) + jobNoGeneratorEntity.getSuffix();


        return result;
    }

    private Integer getNextSysNumber(String id){
        GeneratorEntity generatorEntity = generatorDao.findOne(JOBNO_ID);

        Integer result = generatorEntity.getSysNumber() + 1;
        generatorEntity.setSysNumber(result);
        generatorDao.save(generatorEntity);

        return result;
    }
}
