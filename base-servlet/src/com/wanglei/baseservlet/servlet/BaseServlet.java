package com.wanglei.baseservlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.wanglei.baseservlet.exception.BusinessException;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.View;
import com.wanglei.baseservlet.utils.JsonHelper;

/**
 * <p>Title: 通过这个baseServlet统一处理请求</p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月24日
 */

public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 2543080326681088942L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _method = request.getParameter("method");
		System.out.println(_method);
		//判断是不是为空
		if(StringUtils.isBlank(_method)){
			throw new BusinessException("您没有传递必须的method参数，无法确定业务操作！");
		}
		Class<?> clazz = this.getClass();
		Method method = null;
		try {
			  method = clazz.getMethod(_method, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new BusinessException("您要调用的"+_method+"(HttpServletRequest,HttpServletResponse)未定义");
		}
		Object returnBiz = null;
		//如果要访问的方法存在则进行业务处理
		try {
			 returnBiz = method.invoke(this,request,response);
			//根据返回类判断接下来的操作
			if(returnBiz instanceof String){
				String returnBizStr = returnBiz.toString();
				this.renderString(returnBizStr, request, response);
			}
			if(returnBiz instanceof View){
				this.renderView((View)returnBiz, request, response);
			}
			if(returnBiz instanceof Pager<?>){
				PrintWriter out = response.getWriter();
				String pagers = JsonHelper.toJSON((Pager<?>)returnBiz).toString();
            	out.write(pagers);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * <p>Description:处理string类型返回值
     *获取请求处理方法后返回的字符串，它表示转发或者重定向的路径 
     *如果返回值中不包含“:”，表示使用默认方式：转发
     *如果返回值中包含“：”，将字符串通过“：”截取为两部分，前一部分表示标识，f代表转发，r代表重定向
     *如果包含json:字样则会返回json字符串
	 *如果返回的字符串是null或者""，我们什么也不干<p>
	 * @param url 路径
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @author wanglei 2017年12月27日
	 */
	private void renderString(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		    String bzStr = this.checkUrl(url,request,response);
		    String [] bzs = bzStr.split(";");
		    String bz = bzs[0];
		    String endstr = bzs[1];
            if(bz.equalsIgnoreCase("r")){
                 response.sendRedirect(endstr);
            }else if(bz.equalsIgnoreCase("json")){
            	PrintWriter out = response.getWriter();
            	out.write(endstr);
            }else if(bz.equalsIgnoreCase("f")){
                request.getRequestDispatcher(endstr).forward(request, response);
            }else{
                throw new BusinessException("您的指定："+bz+"暂时没有开通此业务");
            }
	}
	/**
	 * <p>Description:处理页面返回类<p>
	 * @param view 页面对象
	 * @param request 请求
	 * @param response 
	 * @throws ServletException
	 * @throws IOException
	 * @author wanglei 2017年12月27日
	 */
	private  void renderView(View view,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取路由
		String url = view.getUrl();
		String bzStr = this.checkUrl(url,request,response);
		String [] bzs = bzStr.split(";");
		String bz = bzs[0];
		String endstr = bzs[1];
		//获取参数
	    Map<String,Object>params = view.getparams();
	    StringBuffer paramssbf = new StringBuffer("");
		if(null!=params&&params.size()>0){
			Set<String> keys = params.keySet();
			int index = 0; 
			paramssbf.append("?");
			for(String key :keys){
				request.setAttribute(key, params.get(key));
				index ++;
				paramssbf.append(key+"="+params.get(key));
				if(index<keys.size()-1){
					paramssbf.append("&");
				}
			}
		}
		if(bz.equalsIgnoreCase("json")){
			PrintWriter out = response.getWriter();
        	out.write(JsonHelper.toJSON(params).toString());
		}else if(bz.equalsIgnoreCase("r")){
            response.sendRedirect(endstr+paramssbf.toString());
		 }else if(bz.equalsIgnoreCase("f")){
             request.getRequestDispatcher(endstr).forward(request, response);
         }else{
             throw new BusinessException("您的指定："+bz+"暂时没有开通此业务");
         }
	}
	/**
	 * <p>Description: 预处理url<p>
	 * @param url 路径
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @author wanglei 2017年12月27日
	 */
	private String checkUrl(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String bzUrl = null;
		if(!url.contains(":")){
            request.getRequestDispatcher(url).forward(request, response);
        }else{
            int index=url.indexOf(":");
            String bz=url.substring(0, index);
            String endstr=url.substring(index+1);
            bzUrl = bz+";"+endstr;
        }
		return bzUrl;
	}
	@Override
    public void init(ServletConfig config) throws ServletException {
	    // TODO Auto-generated method stub
		
	    super.init(config);
    }
    
}
