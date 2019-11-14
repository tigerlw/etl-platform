package com.dap.etl.platform.admin.inf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dap.etl.platform.admin.inf.repo.mysql.dao.CustDao;
import com.dap.etl.platform.admin.inf.repo.mysql.dto.CustDTO;

@RestController
public class CustController {
	
	@Autowired
	private CustDao custDao;
	
	@RequestMapping(value = "/getCustAll", method = RequestMethod.POST)
	public List<CustDTO> getCustAll()
	{
		List<CustDTO> result = custDao.findAll();
		
		return result;
	}

}
