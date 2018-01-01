package com.wanglei.baseservlet.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileView {
	
	/**
	 *要下载的文件 
	 */
	private File file;
	/**
	 * 目标文件名
	 */
	private String destfileName;
	public FileView (File _file,String destfileName){
		this.file = _file; 
		this.destfileName = destfileName;
	}
	
	/**
	 * <p>Description:下载文件<p>
	 * @param arg1
 	 * @param response
	 * @throws Exception
	 * @author wanglei 2017年12月31日
	 */
	public void render(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
        // 避免下载时文件名乱码
		destfileName = URLEncoder.encode(destfileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + destfileName);
        response.setContentType(getContentType());
        response.addHeader("Content-Length", "" + file.length());   
        //注释原因:如果是大文件会由于int的大小损失精度
        //response.setContentLength((int)dfile.length());
        // 得到输入流
        FileInputStream in = new FileInputStream(file);
        BufferedInputStream bufIn = new BufferedInputStream(in);
        ServletOutputStream out = response.getOutputStream();
        // 下面是一个普通的流的复制 。。。忽略 .这样可以防止内存问题
        byte[] bs = new byte[1024];
        while ((bufIn.read(bs)) != -1) {
            out.write(bs);
        }
        bufIn.close();
        in.close();
        // 最后是流的关闭。
        out.flush();
        out.close();
    }
	/**
	 * <p>Description:获取文件的类型<p>
	 * @return 返回字符型的文件头类型
	 * @author wanglei 2017年12月31日
	 */
	public String getContentType() {
		String ContentType;
	    String str = destfileName.substring(destfileName.indexOf("."),destfileName.length());
	    if(".asf".equals(str)){
	    	 ContentType = "video/x-ms-asf ";
	    }else if(".avi".equals(str)){
	    	ContentType = "video/avi ";
	    }else if(".avi".equals(str)){
	    	ContentType = "video/avi ";
	    }else if(".doc".equals(str)){
	    	ContentType = "application/msword ";
	    }else if(".zip".equals(str)){
	    	ContentType = "application/zip ";
	    }else if(".xls".equals(str)){
	    	ContentType = "application/vnd.ms-excel ";
	    }else if(".gif".equals(str)){
	    	ContentType = "image/gif ";
	    }else if(".jpg".equals(str)){
	    	ContentType = "image/jpeg ";
	    }else if(".xls".equals(str)){
	    	ContentType = "application/vnd.ms-excel ";
	    }else if(".xls".equals(str)){
	    	ContentType = "application/vnd.ms-excel ";
	    }else if(".xls".equals(str)){
	    	ContentType = "application/vnd.ms-excel ";
	    }else if(".xlsx".equals(str)){
	    	ContentType = "application/vnd.ms-excel ";
	    }
	    else
	    	ContentType = "application/octet-stream ";
	    
	    return ContentType;
	}
}
