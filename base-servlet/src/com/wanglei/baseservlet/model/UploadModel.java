package com.wanglei.baseservlet.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:文件上传实体类 </p>
 * <p>Description:  </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月31日
 */
public class UploadModel {
	/**
	 * 要上传文件
	 */
	private File file;
    /**
     * 文件上传真实路径- 必须参数不然不知道往哪上传
     */
    private String fileUploadRealPath;
    /**
     * 文件真实名称
     */
    private String fileRealName;
    /**
     * 系统存储文件名-存储名称必须要不然无法存储
     */
    private String fileSystemName;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 文件大小
     */
    public String fileSize;
    /**
     * 封装表单其他参数
     */
    private Map<String,Object> requestParameters;
    
    public UploadModel(){
    	requestParameters = new HashMap<>();
    }
    
    public void setParameter(String name,String value){
    	requestParameters.put(name, value);
    }
    
    public Object getParameter(String name){
    	return requestParameters.get(name);
    }
    
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileUploadRealPath() {
		return fileUploadRealPath;
	}
	public void setFileUploadRealPath(String fileUploadRealPath) {
		this.fileUploadRealPath = fileUploadRealPath;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public String getFileSystemName() {
		return fileSystemName;
	}
	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public Map<String, Object> getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(Map<String, Object> requestParameters) {
		this.requestParameters = requestParameters;
	}
	@Override
	public String toString() {
		return "UploadModel [file=" + file + ", fileUploadRealPath=" + fileUploadRealPath
				+ ", fileRealName=" + fileRealName + ", fileSystemName="
				+ fileSystemName + ", suffix=" + suffix + ", fileSize="
				+ fileSize + "]";
	}
    
    
}
