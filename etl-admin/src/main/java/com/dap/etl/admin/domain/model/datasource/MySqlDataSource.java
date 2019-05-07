package com.dap.etl.admin.domain.model.datasource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dap.etl.admin.domain.inf.service.MySqlConnVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;

/**
 * mysql 数据库，数据源配置
 * @author liuwei1
 *
 */
public class MySqlDataSource implements DataSource
{
	private static Map<String,MySqlDataSource> dataSourceStore = new ConcurrentHashMap<String,MySqlDataSource>(128);
	
	private String id;
	
	private String ip;
	
	private String port;
	
	private String dataBase;
	
	private String userName;
	
	private String pwd;
	
	private MySqlConnection connection;
	
	private List<TableSchemaVO> tables;
	
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
	
	/**
	 * 获取tables 列表
	 * @return
	 */
	
	public List<TableSchemaVO> getTableList()
	{
		MySqlConnVO connVO = new MySqlConnVO();
		
		connVO.setIp(ip);
		connVO.setPort(port);
		connVO.setDataBase(dataBase);
		connVO.setUserName(userName);
		connVO.setPwd(pwd);
		
		this.tables = connection.getTableList(connVO);
		
		return this.tables;
	}
	
	/**
	 * 构建dataSource
	 * @return
	 */
	public boolean buildDataSource()
	{
		getTableList();
		
		dataSourceStore.put(this.id, this);
		
		return true;
	}
	
	/**
	 * 根据表名获取表定义
	 * @param tableName
	 * @return
	 */
	public TableSchemaVO getTableSchema(String tableName)
	{
		for(TableSchemaVO table : this.tables)
		{
			if(tableName.equals(table.getTableName()))
			{
				return table;
			}
		}
		
		return null;
	}
	
	/**
	 * 通过Id获取数据源
	 * @param id
	 * @return
	 */
	public static DataSource getDataSourceById(String id)
	{
		DataSource result = dataSourceStore.get(id);
		
		return result;
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

	public List<TableSchemaVO> getTables() {
		return tables;
	}

	public void setTables(List<TableSchemaVO> tables) {
		this.tables = tables;
	}
	

}
