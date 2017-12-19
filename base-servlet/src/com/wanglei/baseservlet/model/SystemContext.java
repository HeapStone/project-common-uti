package com.wanglei.baseservlet.model;

/**
 * <p>Title:用来传递列表对象的ThreadLocal 数据 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright wanglei(c) 2017</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">WangLei</a>
 * @version 1.0
 * @history:
 * Created by WangLei 2017年1月8日
 */
public class SystemContext {
	
	/**
	 * 分页大小
	 * 
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	
	/**
	 * 分页起始页
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	
	/**
	 * 分页的排序
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 排序方式
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	public static Integer getPageSize() {
		return pageSize.get();
	}

	public static Integer getPageOffset() {
		return pageOffset.get();
	}

	public static String getSort() {
		return sort.get();
	}

	public static String getOrder() {
		return order.get();
	}

	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}

	public static void setPageOffset(Integer _pageOffset) {
		pageOffset.set(_pageOffset);
	}

	public static void setSort(String _sort) {
		sort .set(_sort);
	}

	public static void setOrder(String _order) {
		order .set(_order);
	}
	
	public static void removePageSize(){
		pageSize.remove();
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	public static void removeSort(){
		sort.remove();
	}
	public static void removeOrder(){
		order.remove();
	}
}
