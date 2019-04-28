package com.dap.etl.admin.domain.inf.service;

/**
 * 
 * @author liuwei1
 *
 */
public class MySqlConnVO {
	
	private String ip;
	
	private String port;
	
	private String dataBase;
	
	private String userName;
	
	private String pwd;

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
	
	@Override
	public String toString()
	{
		String result = String.format("ip:%s;port:%s;database:%s;userName:%s;pwd:%s", this.ip, this.port, this.dataBase,
				this.userName, this.pwd);
		
		return result;
	}

}
