package com.dap.etl.platform.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.dap.etl.platform.admin.inf.repo.mysql.dao")
public class SpringApplicationMain {
	
	public static void main(String args[])
	{
		ConfigurableApplicationContext context = SpringApplication.run(SpringApplicationMain.class, args);
		
	
	}

}
