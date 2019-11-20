package com.dap.etl.platform.admin.inf.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.dap.etl.platform.admin.inf.repo.mysql.dao.CustDao;
import com.dap.etl.platform.admin.inf.repo.mysql.dto.CustDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class CustController {
	
	private final Logger logger = LogManager.getLogger(CustController.class);
	
	private static Random random = new Random();
	
	@Autowired
	private CustDao custDao;
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(value = "/getCustAll", method = RequestMethod.POST)
	public PageInfo<CustDTO> getCustAll()
	{
		Page<CustDTO> page = PageHelper.startPage(0, 2);
		List<CustDTO> custs = custDao.findAll();
		
		PageInfo<CustDTO> result = new PageInfo<>(custs);
		
		return result;
	}
	
	@RequestMapping(value = "/getCustByName", method = RequestMethod.POST)
	public List<CustDTO> getCustByName(@RequestBody String name)
	{
		CustDTO param = new CustDTO();
		param.setUserName(name);
		List<CustDTO> result = custDao.findByName(param);
		
		return result;
	}
	
	@RequestMapping(value = "/getRedisKey", method = RequestMethod.POST)
	public String getRedisKey(@RequestBody String key)
	{
		String result = stringRedisTemplate.opsForValue().get(key);
		
		stringRedisTemplate.opsForValue().set("kkk", "ggg");
		
		UserDomain userDomain = new UserDomain();
		userDomain.setUserName("qqtt");
		userDomain.setAge(20);
		
		redisTemplate.opsForValue().set(userDomain.getUserName(), userDomain);
		UserDomain userR = (UserDomain) redisTemplate.opsForValue().get("qqtt");
		
		return userR.getUserName();
	}
	
	
	@RequestMapping(value = "/testRedisPerf", method = RequestMethod.POST)
	public String testRedisPerf()
	{
		int seq = random.nextInt(100000000);
		
		logger.info("begin userName:"+seq);
		
		String userName = "userName_" + seq;
		
		UserDomain user = new UserDomain();
		user.setUserName(userName);
		user.setAge(20);
		
		redisTemplate.opsForValue().set(userName, user);
		
		UserDomain result = (UserDomain) redisTemplate.opsForValue().get(userName);
		
		logger.info("userName:"+result.getUserName());
		
		
		return "success";
	}
	
	static class UserDomain{
		
		private String userName;
		
		private Integer age;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}

}
