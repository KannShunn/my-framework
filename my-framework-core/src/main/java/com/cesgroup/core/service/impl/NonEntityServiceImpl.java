package com.cesgroup.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cesgroup.core.dao.NonEntityDao;
import com.cesgroup.core.entity.NonEntity;
import com.cesgroup.core.service.NonEntityService;

//@Component
public class NonEntityServiceImpl extends BaseServiceImpl<NonEntity,NonEntityDao> implements NonEntityService{


	@Override
	@Autowired
	public void setDao(NonEntityDao noEntityDao) {
		super.dao = noEntityDao;
	}

}
