package com.wanglei.baseservlet.model;

import java.util.List;

public class ExcelModel{
	private String[] titles;
	private String[] columns;
	private List<?> exportList;
	private String desFileName;
	
	public String getDesFileName() {
		return desFileName;
	}
	public void setDesFileName(String desFileName) {
		this.desFileName = desFileName;
	}
	private long dataLength;
	
	public long getDataLength() {
		return dataLength;
	}
	public void setDataLength(long dataLength) {
		this.dataLength = dataLength;
	}
	public String[] getTitles() {
		return titles;
	}
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public List<?> getExportList() {
		return exportList;
	}
	public void setExportList(List<?> exportList) {
		this.exportList = exportList;
	}
	
	
}
