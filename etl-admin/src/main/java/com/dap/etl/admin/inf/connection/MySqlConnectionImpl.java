package com.dap.etl.admin.inf.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dap.etl.admin.domain.inf.service.MySqlConnVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;
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

}
