package com.dap.etl.admin.inf.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dap.etl.admin.inf.xxljob.vo.XxlJobInfo;
import com.alibaba.fastjson.JSON;

@RestController
public class JobApiController {
	
	private final Logger logger = LogManager.getLogger(JobApiController.class);
	
	
	private static String cookie = "";
	
	
	@RequestMapping(value = "/runJob" ,method = RequestMethod.GET)
	public String runJob(@RequestParam String jobId, @RequestParam String taskId)
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		
		List<String> cookies = new ArrayList<>();
	    //cookies.add("XXL_JOB_LOGIN_IDENTITY=" + "6333303830376536353837616465323835626137616465396638383162336437");
		cookies.add(cookie);
	    headers.put(HttpHeaders.COOKIE,cookies);

		
		String params = "{}";
		
		HttpEntity<String> formEntity = new HttpEntity<String>(params, headers);
		
		Map<String,Object> paramsMap = new HashMap<String,Object>(16);
		
		paramsMap.put("id", jobId);
		paramsMap.put("executorParam", taskId);

		ResponseEntity<String> response = restTemplate.postForEntity(
				"http://127.0.0.1:8080/xxl-job-admin/jobinfo/trigger?id={id}&executorParam={executorParam}", formEntity, String.class, paramsMap);
		

		return response.getBody();
	}
	
	
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String login()
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		
		HttpEntity<String> formEntity = new HttpEntity<String>("", headers);

		ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:8080/xxl-job-admin/login?userName=admin&password=123456", formEntity, String.class);
		
		
		List<String> cookies = response.getHeaders().get("Set-Cookie");
		
		if(cookies!=null && !cookies.isEmpty())
		{
			cookie = cookies.get(0);
		}
		
		logger.info(cookie);
		
		
		return "success";
	}
	
	@RequestMapping(value = "/createjob" ,method = RequestMethod.POST)
	public String createJob(@RequestBody XxlJobInfo jobInfo)
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		List<String> cookies = new ArrayList<>();
		// cookies.add("XXL_JOB_LOGIN_IDENTITY=" +
		// "6333303830376536353837616465323835626137616465396638383162336437");
		cookies.add(cookie);
		headers.put(HttpHeaders.COOKIE, cookies);

		String params = JSON.toJSONString(jobInfo);

		HttpEntity<String> formEntity = new HttpEntity<String>(params, headers);

		Map<String, Object> paramsMap = new HashMap<String, Object>(16);

		

		ResponseEntity<String> response = restTemplate.postForEntity(
				"http://127.0.0.1:8080/xxl-job-admin/jobinfo/add", formEntity,
				String.class, paramsMap);
		
		return "success";
	}
	
	
	
	@RequestMapping(value="/doDownload",method = RequestMethod.GET)
	public ResponseEntity<byte[]> doDownLoad(HttpServletRequest request)
	{
		String filename="636918476332613128_testdbtrans.ktr.xsd";
        //ServletContext scontext=request.getServletContext();
		String path = JobApiController.class.getResource("/").getPath()  + filename;
		//String path = "D:\\work\\code\\etl-platform\\etl-admin\\target\\classes\\636918476332613128_testdbtrans.ktr.xsd";
        System.out.println(path);
        File f=new File(path);
        InputStream in = null;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            filename = new String(filename.getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            HttpStatus statusCode=HttpStatus.OK;
            response = new ResponseEntity<byte[]>(b, headers, statusCode);  
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
        	IOUtils.closeQuietly(in);
        }
        
        return response;

	}
	
	
	@RequestMapping(value="/getFile",method = RequestMethod.GET)
	public String getFile()
	{
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://127.0.0.1:8013/doDownload";
		HttpHeaders header = new HttpHeaders();
		List<MediaType> list = new ArrayList<MediaType>();
		// 指定下载文件类型
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		header.setAccept(list);

		HttpEntity<byte[]> request = new HttpEntity<byte[]>(null, header);
		ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
		// 取得文件字节
		byte[] result = response.getBody();
		
		
		String filename="download.xsd";
        //ServletContext scontext=request.getServletContext();
		String path = JobApiController.class.getResource("/").getPath()  + filename;
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = new ByteArrayInputStream(result);

			outputStream = new FileOutputStream(new File(path));

		/*	int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf, 0, 1024)) != -1) {
				outputStream.write(buf, 0, len);
			}

			outputStream.flush();*/
			
			IOUtils.copy(inputStream, outputStream);
			
			inputStream.close();
			outputStream.close();
			
		} catch (Exception e) {
			
			logger.error(e);
		}finally
		{
			
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);

		}


		
		return "success";
	}
	
	

}
