package com.dap.etl.admin.inf.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dap.etl.admin.domain.inf.service.MySqlConnVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.datasource.TableSchemaVO;
/**
 * 
 * @author liuwei1
 *
 */
@Component
public class MySqlConnectionImpl implements MySqlConnection{
	
	private final Logger logger = LogManager.getLogger(MySqlConnectionImpl.class);
	
	private static Integer CONNECT_TIMEOUT = 1000;

	@Override
	public boolean testConnection(MySqlConnVO conn) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection con = null;
		try {
            
			String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=GMT&useSSL=true", conn.getIp(),
					conn.getPort(), conn.getDataBase());
            
            con = DriverManager.getConnection(connectionUrl, conn.getUserName(), conn.getPwd());
            
			if (con.isValid(CONNECT_TIMEOUT)) 
			{
				result = true;
			}
            
        } catch (Exception e) {

        	logger.error("MYSQL ERROR:" + ExceptionUtils.getStackTrace(e));
        }finally
		{
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}
		
		return result;
	}

	@Override
	public List<TableSchemaVO> getTableList(MySqlConnVO conn) {
		// TODO Auto-generated method stub
		List<TableSchemaVO> tables = new ArrayList<TableSchemaVO>();
		
		Connection con = null;
		ResultSet rsTables = null;
		ResultSet rsColumns = null;
		try {

			String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=GMT&useSSL=true", conn.getIp(),
					conn.getPort(), conn.getDataBase());

			con = DriverManager.getConnection(connectionUrl, conn.getUserName(), conn.getPwd());
			DatabaseMetaData dbmd = con.getMetaData();
			String[] types = {"TABLE"};
			rsTables = dbmd.getTables(con.getCatalog(), con.getCatalog(), "%", types);
			
			while(rsTables.next())
			{
				TableSchemaVO table = new TableSchemaVO();
				
				String tableName = rsTables.getString(3);
				table.setTableName(tableName);
				
				List<ColumnSchemaVO> columns = new ArrayList<ColumnSchemaVO>();
				
				rsColumns = dbmd.getColumns(null,"%", tableName ,"%");
				
				while(rsColumns.next())
				{
					ColumnSchemaVO colum = new ColumnSchemaVO();
					colum.setColumnName(rsColumns.getString("COLUMN_NAME"));
					colum.setColumnType(rsColumns.getString("TYPE_NAME"));
					columns.add(colum);
					
				}
				
				table.setColumns(columns);
				
				tables.add(table);
			}


		} catch (Exception e) {
			logger.error("MYSQL ERROR:" + ExceptionUtils.getStackTrace(e));
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}

		return tables;
	}
	
	

}
