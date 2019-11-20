package com.dap.etl.platform.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

@Configuration
public class RedisConfiguration {
	
	@Bean
	public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory)
	{
		
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		GenericFastJsonRedisSerializer jsonSerializer = new GenericFastJsonRedisSerializer();
		
		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(jsonSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(jsonSerializer);
		
		return template;
	}

}
