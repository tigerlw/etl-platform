package com.dap.etl.platform.admin.inf.repo.mysql.dao;

import java.util.List;

import com.dap.etl.platform.admin.inf.repo.mysql.dto.CustDTO;

public interface CustDao {
	
	public List<CustDTO> findAll();

}
