package com.cesgroup.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.core.web.NonEntityServiceController;

@Controller
public class MenuController {

	@RequestMapping(value="/leftMenuAuth")
	public String leftMenuAuth(){
		return "left-auth";
	}
	
	@RequestMapping(value="/leftMenuReuseplat")
	public String leftMenuReuseplat(){
		return "left-reuse";
	}

	@RequestMapping(value="/centerMenuAuth")
	public String centerMenuAuth(){
		return "center-auth";
	}
	
	@RequestMapping(value="/centerMenuReuseplat")
	public String centerMenuReuseplat(){
		return "center-reuse";
	}
}
