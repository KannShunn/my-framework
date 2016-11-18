package com.cesgroup.common.web;

import com.cesgroup.core.entity.NonEntity;
import com.cesgroup.core.service.NonEntityService;

public abstract class NonEntityController<Service extends NonEntityService> extends AuthBaseController<NonEntity, Service>{

}
