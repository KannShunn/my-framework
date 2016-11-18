package com.cesgroup.core.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.cesgroup.core.entity.NonEntity;
import com.cesgroup.core.service.NonEntityService;

public abstract class NonEntityServiceController extends BaseController<NonEntity, NonEntityService>{

	@Override
	@Autowired
	public void setService(NonEntityService service) {
		super.service = service;
	}

}
