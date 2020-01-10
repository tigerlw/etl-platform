package com.dap.etl.platform.admin.inf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
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
	
	@RequestMapping(value = "/insertCust", method = RequestMethod.POST)
	public String insertCust(@RequestBody CustDTO cust)
	{
		custDao.insertCust(cust);
		return "success";
	}
	
	
	@RequestMapping(value = "/insertPerfMysql", method = RequestMethod.POST)
	public String insertPerfMysql(@RequestParam int count)
	{
		
		Executor executor = Executors.newFixedThreadPool(20);
		
		for (int i = 0; i < count; i++) 
		{
			
			executor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					List<CustDTO> custs = new ArrayList<CustDTO>();
					
					for (int i = 0; i < 100; i++) 
					{

						CustDTO cust = new CustDTO();
						cust.setId(Long.toString(System.currentTimeMillis()));
						cust.setUserName("ttt");
						cust.setAge(22);
						custs.add(cust);
					}
					
					custDao.insertCustBatch(custs);
				}
				
			});

		}
		
		
		
		return "success";
		
	}
	
	
	@RequestMapping(value = "/insertCustBatch", method = RequestMethod.POST)
	public String insertCustBatch(@RequestParam int count)
	{
		List<CustDTO> custs = new ArrayList<CustDTO>();
		
		for (int i = 0; i < count; i++) 
		{
			CustDTO cust =new CustDTO();
			cust.setId(Long.toString(System.currentTimeMillis()));
			cust.setUserName("ttt");
			cust.setAge(22);
			
			custs.add(cust);
		}
		
		custDao.insertCustBatch(custs);
		
		return "success";
	}
	
	
	
	@RequestMapping(value = "/getRedisKey", method = RequestMethod.POST)
	public String getRedisKey(@RequestBody String key)
	{
		String result = stringRedisTemplate.opsForValue().get(key);
		
		/*stringRedisTemplate.opsForValue().set("kkk", "ggg");
		
		UserDomain userDomain = new UserDomain();
		userDomain.setUserName("qqtt");
		userDomain.setAge(20);
		
		redisTemplate.opsForValue().set(userDomain.getUserName(), userDomain);
		UserDomain userR = (UserDomain) redisTemplate.opsForValue().get("qqtt");*/
		
		return result;
	}
	
	
	@RequestMapping(value = "/setRedisKey", method = RequestMethod.POST)
	public String setRedisKey(@RequestParam String key, @RequestParam String value)
	{
		stringRedisTemplate.opsForValue().set(key, value);
		
		/*stringRedisTemplate.opsForValue().set("kkk", "ggg");
		
		UserDomain userDomain = new UserDomain();
		userDomain.setUserName("qqtt");
		userDomain.setAge(20);
		
		redisTemplate.opsForValue().set(userDomain.getUserName(), userDomain);
		UserDomain userR = (UserDomain) redisTemplate.opsForValue().get("qqtt");*/
		
		return "success";
	}
	
	@RequestMapping(value = "/delRedisKey", method = RequestMethod.POST)
	public String setRedisKey(@RequestParam String key)
	{
		stringRedisTemplate.delete(key);
		
		/*stringRedisTemplate.opsForValue().set("kkk", "ggg");
		
		UserDomain userDomain = new UserDomain();
		userDomain.setUserName("qqtt");
		userDomain.setAge(20);
		
		redisTemplate.opsForValue().set(userDomain.getUserName(), userDomain);
		UserDomain userR = (UserDomain) redisTemplate.opsForValue().get("qqtt");*/
		
		return "success";
	}
	
	
	@RequestMapping(value = "/testRedisPerf", method = RequestMethod.POST)
	public String testRedisPerf()
	{
		long wasteTime = System.currentTimeMillis();
		
		int seq = random.nextInt(100000000);
		
		logger.info("begin userName:"+seq);
		
		String userName = "userName_" + seq;
		
		UserDomain user = new UserDomain();
		user.setUserName(userName);
		user.setAge(20);
		
		redisTemplate.opsForValue().set(userName, user);
		
		UserDomain result = (UserDomain) redisTemplate.opsForValue().get(userName);
		
		wasteTime = System.currentTimeMillis() - wasteTime;
		
		logger.info("userName:"+result.getUserName()+";wastTime:"+wasteTime);
		
		
		return "success";
	}
	
	
	@RequestMapping(value = "/findCustByName", method = RequestMethod.POST)
	public String findCustByName(@RequestBody String name)
	{
		Query query=new Query(Criteria.where("userName").is(name));
		
		UserDomain user = mongoTemplate.findOne(query, UserDomain.class, "UserDomain");
		
		return "user:"+user.getUserName()+";age:"+user.getAge();
	}
	
	
	
	@RequestMapping(value = "/insertCustToMongo", method = RequestMethod.POST)
	public String insertCustToMongo(@RequestParam String name)
	{
		UserDomain user = new UserDomain();
		user.setUserName(name);
		user.setAge(20);
		
		mongoTemplate.save(user, "UserDomain");
		
		return "success";
	}
	
	
	@RequestMapping(value = "/mongoPerfTest", method = RequestMethod.POST)
	public String mongoPerfTest(@RequestParam int count)
	{
		Executor executor = Executors.newFixedThreadPool(20);
		
		for (int i = 0; i < count; i++)
		{
			executor.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					UserDomain user = new UserDomain();
					user.setUserName(Long.toString(System.currentTimeMillis()));
					user.setAge(20);
					
					mongoTemplate.save(user, "UserDomain");
				}
				
			});
		}
		
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
