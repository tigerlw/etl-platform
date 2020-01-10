package com.dap.etl.platform.admin.inf.repo.mysql.dao;

import java.util.List;

import com.dap.etl.platform.admin.inf.repo.mysql.dto.CustDTO;

public interface CustDao {
	
	public List<CustDTO> findAll();
	
	public List<CustDTO> findByName(CustDTO custDTO);
	
	public void insertCust(CustDTO custDTO);
	
	public void insertCustBatch(List<CustDTO> custs);

}
