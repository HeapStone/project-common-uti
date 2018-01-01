package com.wanglei.baseservlet.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wanglei.baseservlet.exception.BusinessException;
import com.wanglei.baseservlet.model.ExcelModel;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	protected static final  Logger logger  = LoggerFactory.getLogger(ExcelUtil.class);
	public static File getExcelFile(ExcelModel exm) {
		if(null == exm) throw new BusinessException("导出类为null 无法导出!");
		String fileName = exm.getDesFileName(); 
		List<?> dataList = exm.getExportList();
		String[] dataColumns = exm.getColumns();
		String[] dataTitles = exm.getTitles();
		long datalength = exm.getDataLength(); 
		String fileUrl = "/temp/"+new Date().getTime()+fileName;
		
		logger.debug("create excel temp file path :"+fileUrl);
		
		WritableWorkbook wwb;
        FileOutputStream fos;
        
		File file = new File(fileUrl);

		try {
				fos = new FileOutputStream(file);
	            wwb = Workbook.createWorkbook(fos);
	            
				Iterator<?> it = dataList.iterator();
				
				int rows = 0;
				
				int k=0;
				
				WritableSheet ws = null;
				
				WritableFont font1= new 
				WritableFont(WritableFont.TIMES,9,WritableFont.BOLD); 

				WritableCellFormat titleCellFormat = new WritableCellFormat(font1);
				//设置文字居中对齐方式;  
				titleCellFormat.setAlignment(Alignment.CENTRE);  
				//设置自动换行;  
				titleCellFormat.setWrap(true);  
				
				WritableCellFormat cellFormat = new WritableCellFormat(font1);
				//设置文字居中对齐方式;  
				cellFormat.setAlignment(Alignment.CENTRE);  
				//设置自动换行;  
				cellFormat.setWrap(true);  
				//jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
				
				
				Date fileWriteStartDate = new Date();
				
				
				
				while(it.hasNext()){
					Object obj = it.next();
					Map<?,?> map = null;
					if(obj instanceof Map){
						 map = (Map<?,?>)obj;
					}else{
						map = JsonHelper.toMap(obj);
					}
					
					long avglength = rows / datalength;
					if( avglength == k ){
					    ws =wwb.createSheet("sheet"+k, k);
					   
					    logger.debug("每一个新的sheet页都要创建文件标题.....");
	                    for(int i=0;i<dataTitles.length;i++) {
	                        Label label = new Label(i,0,dataTitles[i]); 
	                        label.setCellFormat(titleCellFormat);
	                        ws.addCell(label);
	                    }
	                    k++;
	                    rows = 1; //初始化行数  每一个新建的sheet页的行数索引从1开始，因为第一行是标题
					}
					
					// 输出数据
					for (int j = 0; j < dataColumns.length; j++)
					{
					    Label label = new Label(j,rows, getContentByKey(map,dataColumns[j].toString()));  
					    
					    ws.addCell(label);
					}
					rows++;
				}
				if(ws != null)
				{
					wwb.write();
		            wwb.close();
				}
	            
	
				Date fileWriteEndDate = new Date();
				
				long second = (fileWriteStartDate.getTime() - fileWriteEndDate.getTime());
				float millSec = second/1000;
				float min = millSec/60;
				
				logger.debug("文件创建完毕，共花费"+second+"毫秒"+","+millSec+"秒,"+min+"分钟.");
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("excel file wirte error...."+fileUrl);
			throw new BusinessException("excel 生成出错!");
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return file;
	}
	@SuppressWarnings("rawtypes")
	public static String getContentByKey(Map map ,String key){
	    String result="";
	    if(map.containsKey(key)){
	        result = map.get(key)!=null?map.get(key).toString():"";
	    }
	    return result;
	}
}
