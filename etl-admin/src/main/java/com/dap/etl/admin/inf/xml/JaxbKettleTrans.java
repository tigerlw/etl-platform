package com.dap.etl.admin.inf.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.task.TaskDataSourceVO;
import com.dap.etl.admin.inf.xml.model.Field;
import com.dap.etl.admin.inf.xml.model.Transformation;


@Component
public class JaxbKettleTrans {
	
	
	private final Logger logger = LogManager.getLogger(JaxbKettleTrans.class);
	
	public String buildKettleTransFile(DataTaskVO dataTaskVO)
	{
		String templateFile = "testdb_trans.ktr";
		String target = "mysql_to_mysql.ktr";
		
		String sourcePath = JaxbKettleTrans.class.getResource("/").getPath()  + templateFile;
		String targetPath = JaxbKettleTrans.class.getResource("/").getPath() + target;
		
		File sourceFile = new File(sourcePath);
		File targetFile = new File(targetPath);
		
		if(targetFile.exists())
		{
			targetFile.delete();
		}
		
		JAXBContext jc = null;
		
		try {

			jc = JAXBContext.newInstance(Transformation.class);
			Unmarshaller uma = jc.createUnmarshaller();
			Transformation trans = (Transformation) uma.unmarshal(sourceFile);

			trans.getInfo().get(0).setName(dataTaskVO.getTaskName());

			Iterator<Transformation.Step> steps = trans.getStep().iterator();

			while (steps.hasNext()) {
				Transformation.Step step = steps.next();
				if ("TableInput".equals(step.getType())) {
					String sql = buildSql(dataTaskVO.getOriginDataSource());
					step.setSql(sql);
					continue;
				}

				if ("TableOutput".equals(step.getType())) {
					step.setTable(dataTaskVO.getObjDataSource().getTableName());
					
					List<ColumnSchemaVO> columns = dataTaskVO.getObjDataSource().getColumns();
					
					List<Field> fields = step.getFields().get(0).getField();

					fields.clear();
					
					for(ColumnSchemaVO colum : columns)
					{
						Field column = new Field();
						column.setColumnName(colum.getColumnName());
						column.setStreamName(colum.getColumnName());
						fields.add(column);
					}

					

					continue;
				}

				steps.remove();

			}

			Transformation.Order order = trans.getOrder().get(0);

			Iterator<Transformation.Order.Hop> hops = order.getHop().iterator();

			while (hops.hasNext()) {
				Transformation.Order.Hop hop = hops.next();

				if (!"表输入".equals(hop.getFrom())) {
					hops.remove();
				} else {
					hop.setTo("表输出");
				}

			}

			// 根据Person类生成上下文对象
			jc = JAXBContext.newInstance(Transformation.class);
			// 从上下文中获取Marshaller对象，用作将bean编组(转换)为xml
			Marshaller ma = jc.createMarshaller();
			// 以下是为生成xml做的一些配置
			// 格式化输出，即按标签自动换行，否则就是一行输出
			ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// 设置编码（默认编码就是utf-8）
			ma.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			// 是否省略xml头信息，默认不省略（false）
			ma.setProperty(Marshaller.JAXB_FRAGMENT, false);

			// 编组
			ma.marshal(trans, targetFile);

			System.out.println("copy finish");

		}catch(Exception e)
		{
			logger.error("buildKettleTransFile error:" + e);
		}
		
		
		
		return targetPath;
	}
	
	
	/**
	 * 根据数据源创建sql
	 * @param taskDataSourceVO
	 * @return
	 */
	public String buildSql(TaskDataSourceVO taskDataSourceVO)
	{
		String sql = null;
		
		StringBuilder temp = new StringBuilder();
		temp.append("SELECT ");
		List<ColumnSchemaVO> columns  = taskDataSourceVO.getColumns();
		
		for (ColumnSchemaVO column : columns) 
		{
			temp.append(column.getColumnName());
			temp.append(",");
		}
		
		temp.deleteCharAt(temp.length()-1);
		
		temp.append(" from ");
		temp.append(taskDataSourceVO.getTableName());
		
		sql = temp.toString();
		
		return sql;
	}

}
