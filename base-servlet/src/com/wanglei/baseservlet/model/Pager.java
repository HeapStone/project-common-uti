package com.wanglei.baseservlet.model;

import java.util.List;
import java.util.Map;
/**
 * <p>Title:公共的分页信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright wanglei(c) 2017</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">WangLei</a>
 * @version 1.0
 * @history:
 * Created by WangLei 2017年1月8日
 */
public class Pager<T> {
	/**
	 *页的大小
	 */
	private int size;
	
	/**
	 * 分页起始页
	 */
	private int offset;
	/**
	 * 总的记录数
	 */
	private long total;
	
	/**
	 * 分页的数据
	 */
	private List<T> datas;
	/**
	 * 查询参数-如果有语句中有like 请 在参数中加上% 如params.put("xxx","%XXXX%");
	 */
	private Map<String,String> params;
	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public int getSize() {
		return size;
	}

	public int getOffset() {
		return offset;
	}

	public long getTotal() {
		return total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Pager() {
		super();
	}

	@Override
	public String toString() {
		return "Pager [size=" + size + ", offset=" + offset + ", total="
				+ total + ", datas=" + datas + ", params=" + params + "]";
	}

}
