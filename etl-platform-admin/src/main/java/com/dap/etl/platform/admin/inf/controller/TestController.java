package com.dap.etl.platform.admin.inf.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private final Logger logger = LogManager.getLogger(TestController.class);
	
	@RequestMapping(value = "/getMsg", method = RequestMethod.POST)
	public String getMsg()
	{
		logger.error("success");
		return "success";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getTest()
	{
		
		return "success";
	}

}
