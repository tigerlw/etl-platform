package com.dap.etl.platform.admin.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientOptions;

@Configuration
public class MongoConfiguration {
	
	@Bean
	public MongoClientOptions getMongoClientOpt()
	{
		MongoClientOptions options = MongoClientOptions.builder().socketKeepAlive(true).build();
		
		return options;
	}

}
