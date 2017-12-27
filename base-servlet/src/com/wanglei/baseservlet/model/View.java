package com.wanglei.baseservlet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {

/**
 * 返回路径
 *获取请求处理方法后返回的字符串，它表示转发或者重定向的路径 
 *如果返回值中不包含“:”，表示使用默认方式：转发
 *如果返回值中包含“：”，将字符串通过“：”截取为两部分，前一部分表示标识，f代表转发，r代表重定向
 *如果包含json:字样则会返回json字符串
 *如果返回的字符串是null或者""，我们什么也不干
 ***/
public String url = null;

/**
 * 参数map对象
 */
public Map<String,Object> param = null;
/**
 * 返回参数
 */

/**构造函数初始化类
 * @param url
 */
public View (String url){
	this.url = url;
	param = new HashMap<String,Object>();
}

/**
 * <p>Description:绑定返回参数<p>
 * @param key 参数键
 * @param obj 参数值
 * @author wanglei 2017年12月27日
 */
public void bind(String key,Object obj){
	param.put(url, obj);
}

/**
 * <p>Description:返回一个参数集合<p>
 * @return 参数列表
 * @author wanglei 2017年12月27日
 */
public Map<String,Object> getparams(){
	return this.param;
}

/**
 * <p>Description:返回一个路径<p>
 * @return
 * @author wanglei 2017年12月27日
 */
public String getUrl() {
	return this.url;
}

}
 