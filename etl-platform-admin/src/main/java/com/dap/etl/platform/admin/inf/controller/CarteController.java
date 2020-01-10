package com.dap.etl.platform.admin.inf.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dap.etl.platform.admin.inf.xml.model.Jobstatus;

@RestController
public class CarteController {
	
	@RequestMapping(value = "/getJobResult" ,method = RequestMethod.GET)
	public String getJobResult(@RequestParam String url) throws UnsupportedEncodingException, JAXBException
	{
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = restTemplateBuilder.basicAuthorization("cluster", "cluster").build();
		
		String result = restTemplate.getForObject(
				url,
				String.class);
		
	    InputStream  inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
	    
	    JAXBContext context = JAXBContext.newInstance(Jobstatus.class);
	    
	    Unmarshaller unmarshaller = context.createUnmarshaller();
	    Jobstatus jobstatus = (Jobstatus) unmarshaller.unmarshal(inputStream);
	    
	    result = jobstatus.getStatusDesc();
		
		return result;
	}

}
