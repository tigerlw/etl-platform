package com.dap.etl.admin.domain.model.datasource;

import com.dap.etl.admin.domain.inf.service.MySqlConnVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;

/**
 * mysql 数据库，数据源配置
 * @author liuwei1
 *
 */
public class MySqlDataSource 
{
	
	private String id;
	
	private String ip;
	
	private String port;
	
	private String dataBase;
	
	private String userName;
	
	private String pwd;
	
	private MySqlConnection connection;
	
	public MySqlDataSource()
	{
		
	}
	
	public MySqlDataSource(String id, String ip, String port, String dataBase, String userName, String pwd,
			MySqlConnection connection) 
	{
		this.id = id;
		this.ip = ip;
		this.port = port;
		this.dataBase = dataBase;
		this.userName = userName;
		this.pwd = pwd;
		this.connection = connection;
	}
	
	/**
	 * 测试数据库链接
	 * @return
	 */
	public boolean testConnection()
	{
		MySqlConnVO connVO = new MySqlConnVO();
		
		connVO.setIp(ip);
		connVO.setPort(port);
		connVO.setDataBase(dataBase);
		connVO.setUserName(userName);
		connVO.setPwd(pwd);
		
		return connection.testConnection(connVO);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MySqlConnection getConnection() {
		return connection;
	}

	public void setConnection(MySqlConnection connection) {
		this.connection = connection;
	}
	

}
