package com.dap.etl.admin.inf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dap.etl.admin.app.service.DataTaskService;
import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.inf.vo.DataTaskDefinition;
import com.dap.etl.admin.inf.xml.JaxbKettleTrans;

@RestController
public class TaskApiController {
	
	@Autowired
	private DataTaskService dataTaskService;
	
	@Autowired
	private JaxbKettleTrans jaxbKettleTrans;
	
	@RequestMapping(value = "/task/buildtask", method = RequestMethod.POST)
	public DataTaskVO buildDataTask(@RequestBody DataTaskDefinition dataTaskDefinition) 
	{
		DataTaskVO result = dataTaskService.buildDataTask(dataTaskDefinition.getTaskId(), dataTaskDefinition.getTaskName(),
				dataTaskDefinition.getOriginId(), dataTaskDefinition.getOrgTable(), dataTaskDefinition.getObjId(),
				dataTaskDefinition.getObjTable());
		
		
		return result;
	}
	
	
	@RequestMapping(value = "/task/taskfile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getDataTaskKettleFile(@RequestParam String taskId)
	{
		DataTaskVO dataTaskVO = dataTaskService.getDataTask(taskId);
		
		String filePath = jaxbKettleTrans.buildKettleTransFile(dataTaskVO);
		
		String filename = dataTaskVO.getTaskName()+".ktr";
		
	
        File f=new File(filePath);
        InputStream in = null;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            filename = new String(filename.getBytes("utf8"),"utf8");
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

}
