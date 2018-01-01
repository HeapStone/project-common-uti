package com.wanglei.baseservlet.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.wanglei.baseservlet.exception.BusinessException;
import com.wanglei.baseservlet.model.ExcelModel;
import com.wanglei.baseservlet.model.FileView;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.UploadModel;
import com.wanglei.baseservlet.model.View;
import com.wanglei.baseservlet.utils.ExcelUtil;
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
    private HttpServletRequest _request;
    private HttpServletResponse _response;
    private String Encoding = "UTF-8";
    /**
     * <p>Description:封装request 请求<p>
     * @return
     * @author wanglei 2018年1月1日
     */
    protected HttpServletRequest request(){
    	return this._request;
    }
    
    /**
     * <p>Description:封装response<p>
     * @return
     * @author wanglei 2018年1月1日
     */
    protected HttpServletResponse response(){
    	return this._response;
    }
	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this._request = request;
		this._response = response;
		//设置编码
		request.setCharacterEncoding(Encoding);
		response.setCharacterEncoding(Encoding); 
		this._request.setCharacterEncoding(Encoding);
		this._response.setCharacterEncoding(Encoding);
		String _method = request.getParameter("method");
		//System.out.println(_method);
		//判断是不是为空
		if(StringUtils.isBlank(_method)){
			throw new BusinessException("您没有传递必须的method参数，无法确定业务操作！");
		}
		Class<?> clazz = this.getClass();
		Method method = null;
		try {
			  method = clazz.getMethod(_method);
		} catch (Exception e) {
			throw new BusinessException("您要调用的"+_method+"未定义");
		}
		Object returnBiz = null;
		//如果要访问的方法存在则进行业务处理
		try {
			 returnBiz = method.invoke(this);
			//根据返回类判断接下来的操作
			if(returnBiz instanceof String){
				String returnBizStr = returnBiz.toString();
				this.renderStringUrl(returnBizStr, request, response);
			}
			if(returnBiz instanceof View){
				this.renderView((View)returnBiz, request, response);
			}
			if(returnBiz instanceof Pager<?>){
				response.setCharacterEncoding(Encoding);
            	response.setContentType("text/json;charset="+Encoding);
				PrintWriter out = response.getWriter();
				String pagers = JsonHelper.toJSON((Pager<?>)returnBiz).toString();
            	out.write(pagers);
			}
			if(returnBiz instanceof FileView){
				FileView fv = (FileView)returnBiz;
				fv.render(request, response);
			}
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new BusinessException(e);
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
	protected void renderStringUrl(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		    String bzStr = this.checkUrl(url,request,response);
		    String [] bzs = bzStr.split(";");
		    String bz = bzs[0];
		    String endstr = bzs[1];
            if(bz.equalsIgnoreCase("r")){
                 response.sendRedirect(endstr);
            }else if(bz.equalsIgnoreCase("json")){
            	response.setCharacterEncoding(Encoding);
            	response.setContentType("text/json;charset="+Encoding);
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
	protected  void renderView(View view,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			response.setCharacterEncoding(Encoding);
        	response.setContentType("text/json;charset="+Encoding);
			PrintWriter out = response.getWriter();
        	out.write(JsonHelper.mapToJSON(params).toString());
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
	/**
	 * <p>Description:文件下载方法<p>
	 * @param file 要下载的文件
	 * @param destFileName 文件名
	 * @param request 
	 * @param response
	 * @throws Exception
	 * @author wanglei 2017年12月31日
	 */
	protected void renderFileView (File file,String destFileName,HttpServletRequest request, HttpServletResponse response) throws Exception{
		FileView fv = new FileView(file,destFileName);
		fv.render(request, response);
	}
	/**
	 * 生成excel文件给客户端下载
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	protected void responseExcelFile(ExcelModel excelModel,HttpServletRequest request,HttpServletResponse response) throws Exception{
		File file =ExcelUtil.getExcelFile(excelModel);
		this.renderFileView(file,excelModel.getDesFileName(),request,response);
	}
	
	/**
	 * <p>Description:文件上传公共方法<p>
	 * @param upm 上传文件的实体类
	 * @param request
	 * @param response
	 * @return
	 * @author wanglei 2018年1月1日
	 */
	protected  UploadModel uploadFlie(UploadModel upm ,HttpServletRequest request,HttpServletResponse response){
		 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
       String savePath = upm.getFileUploadRealPath();
       File file = new File(savePath);
       //判断上传文件的保存目录是否存在
       if (!file.exists() && !file.isDirectory()) {
           System.out.println(savePath+"目录不存在，需要创建");
           //创建目录
           file.mkdir();
       }
       try{
           //使用Apache文件上传组件处理文件上传步骤：
           //1、创建一个DiskFileItemFactory工厂
           DiskFileItemFactory factory = new DiskFileItemFactory();
           //2、创建一个文件上传解析器
           ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
           upload.setHeaderEncoding(Encoding); 
           //3、判断提交上来的数据是否是上传表单的数据
           if(!ServletFileUpload.isMultipartContent(request)){
               //按照传统方式获取数据
               return null;
           }
           //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
           List<FileItem> list = upload.parseRequest(request);
           for(FileItem item : list){
               //如果fileitem中封装的是普通输入项的数据
               if(item.isFormField()){
                   String name = item.getFieldName();
                   //解决普通输入项的数据的中文乱码问题
                   String value = item.getString(Encoding);
                   //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                   //将非文件表单数据放进request里
                   upm.setParameter(name, value);
                   System.out.println(name + "=" + value);
               }else{//如果fileitem中封装的是上传文件
                   //得到上传的文件名称，
                   String filename = item.getName();
                   System.out.println(filename);
                   if(filename==null || filename.trim().equals("")){
                       continue;
                   }
                   //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                   //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                   filename = filename.substring(filename.lastIndexOf("\\")+1);
                   String etc = filename.substring(filename.lastIndexOf(".")+1,+filename.length());
                   //获取item中的上传文件的输入流
                   InputStream in = item.getInputStream();
                   //文件的真实存储名称
                   String fielRealName = upm.getFileSystemName()+"."+etc;
                   //创建一个文件输出流
                   FileOutputStream out = new FileOutputStream(savePath + "\\" +fielRealName);
                   //创建一个缓冲区
                   byte buffer[] = new byte[1024];
                   //判断输入流中的数据是否已经读完的标识
                   int len = 0;
                   //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                   while((len=in.read(buffer))>0){
                       //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                       out.write(buffer, 0, len);
                   }
                   //关闭输入流
                   in.close();
                   //关闭输出流
                   out.close();
                   //封装文件实体类
                   upm.setFile(file);
                   upm.setFileRealName(filename);
                   upm.setFileSize(String.valueOf(file.length()));
                   upm.setSuffix(etc);
                   //删除处理文件上传时生成的临时文件
                   item.delete();
                  
               }
           }
       }catch (Exception e) {
           e.printStackTrace();
           
       }
       return upm;
	}
	 /**返回一个图片
     * @param response
     * @param ins 图片二进制流
     * @throws Exception
     */
    protected void responseImage(HttpServletResponse response,File file) throws Exception{  
        if(null == file){
        	return ;
        }
    	response.setContentType("image/png");
        OutputStream stream = response.getOutputStream(); 
        FileInputStream fin = new FileInputStream(file);
        byte[] buf=new byte[1024];  
        int len=0;  
        while((len=fin.read(buf))!=-1){   //将byte数据读到最多buf长度的buf数组中   
        	stream.write(buf,0,len); 
        }  
        stream.flush();  
        stream.close();  
        fin.close();
    }  
    /**
	 * 生成json串给前端
	 * @param jo
	 * @param response
	 * @throws IOException
	 */
    protected void responseJson(JSONObject jo,HttpServletResponse response) throws IOException{
        response.setHeader("Content-type", "text/json;charset="+Encoding);
        response.setCharacterEncoding(Encoding); 
        PrintWriter pw =  response.getWriter();
        pw.append(jo.toString());
        pw.close();
    }
    /**
	 * 生成json串给前端
	 * @param jo
	 * @param response
	 * @throws IOException
	 */
    protected void responseJson(JSONArray jo,HttpServletResponse response) throws IOException{
        response.setHeader("Content-type", "text/json;charset="+Encoding);
        response.setCharacterEncoding(Encoding); 
        PrintWriter pw =  response.getWriter();
        pw.append(jo.toString());
        pw.close();
    }
    /**
     * 生成字符串给前端
     * @param htmlText
     * @param response
     * @throws IOException
     */
    protected void responseHtmltext(String htmlText,HttpServletResponse response) throws IOException{
        response.setHeader("Content-type", "text/html;charset="+Encoding);
        response.setCharacterEncoding(Encoding); 
        PrintWriter pw =  response.getWriter();
        pw.append(htmlText);
        pw.close();
    }
    
    /**
     * forward跳转
     * @param path 目标路径
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(String path,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset="+Encoding);
        response.setCharacterEncoding(Encoding); 
        request.getRequestDispatcher(path).forward(request, response);
    }
    
    /**
     * 重定向
     * @param path 目标地址
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void redirect(String path,HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset="+Encoding);
        response.setCharacterEncoding(Encoding);  
        response.sendRedirect(path);
    }
	@Override
    public void init(ServletConfig config) throws ServletException {
		//初始化编码
		System.out.println("------------初始化编码");
		String _encodeing = config.getServletContext().getInitParameter("Encoding");
		if(StringUtils.isNotBlank(_encodeing)){
			Encoding = _encodeing ;
		}
	    super.init(config);
    }
    
}
