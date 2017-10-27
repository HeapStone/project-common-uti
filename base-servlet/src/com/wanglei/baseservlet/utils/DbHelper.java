package com.wanglei.baseservlet.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: 获取数据连接工具的类</p>
 * <p>Description: 通过jdbc驱动获取数据库连接，提供获取驱动方法，关闭连接方法，执行sql方法</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年10月27日
 */
public class DbHelper {
	/**
	 * 数据库驱动信息
	 */
	private static String DRIVER = null;
	/**
	 * 数据库url
	 */
	private static String URL = null;
	/**
	 * 用户名
	 */
	private static String USERNAME = null;
	/**
	 * 密码
	 */
	private static String PASSWORD =null;
	/**
	 * 数据库连接对象
	 */
	private Connection connection = null;
	/**
	 * sql语句预处理对象
	 */
	private PreparedStatement pstmt = null;
	/**
	 * 结果对象
	 */
	private ResultSet  resultSet  = null;
	private static DbHelper dh = null;
	static{
		//初始化配置
		inintConfig();
	}
	private DbHelper(){}
	public static DbHelper getDbHelperInstance(){
		if(null==dh){
			synchronized (DbHelper.class) {
				//该同步代码块是为了解决多线程状态下 获取单例对象为null的问题
				dh = new DbHelper();
			}
		}
		return dh;
	}
	/**
	 * <p>Description:初始化配置信息<p>
	 * @author wanglei 2017年10月27日
	 */
	public  static void inintConfig(){
		try {
		InputStream inps = DbHelper.class.getResourceAsStream("/resources/db.properties");
		Properties prop = new Properties();
			prop.load(inps);
			DRIVER = prop.getProperty("driver");
			URL = prop.getProperty("url");
			USERNAME = prop.getProperty("name");
			PASSWORD = prop.getProperty("password");
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e); 
		}
	}
	/**
	 * <p>Description:获取数据库连接对象<p>
	 * @return 数据库连接对象
	 * @author wanglei 2017年10月27日
	 */
	public  Connection getConnection(){
		//反射获取driverManager
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException("获取数据库驱动信息异常！", e); 
		}
	
		return connection;
	}
	/**
	 * <p>Description:执行sql的公共方法<p>
	 * @param sql 要执行的sql语句
	 * @param params 参数
	 * @return 执行sql影响的条数
	 * @author wanglei 2017年10月27日
	 */
	public long excuteSqlByPrepareStatement(String sql,List<?> params){
		if(null == connection){
			this.getConnection();
		}
		long excuNum = -1;
		try {
			pstmt = connection.prepareStatement(sql);
			int index = 1;
			if(null!=params&& !params.isEmpty()){
				for(int i = 0,j=params.size();i<j;i++){
					pstmt.setObject(index++, params.get(i));
				}
			}
			excuNum = pstmt.executeUpdate(); 
		} catch (Exception e) {
			throw new RuntimeException("执行预处理sql异常！", e); 
		}finally{
			releaseConn() ;
		}
		
		return excuNum ;
	}
	/**
	 * <p>Description:执行查询语句的方法<p>
	 * @param sql 查询的sql
	 * @param params 参数
	 * @return 查询结果
	 * @author wanglei 2017年10月27日
	 */
	public List<Map<String,Object>> excuteQuerySql(String sql,List<?> params){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        if(null == connection){
			this.getConnection();
		}
		try {
			 //预处理sql
	        pstmt = connection.prepareStatement(sql);  
	        if (params != null && !params.isEmpty()) {  
	        	//赋值参数
	            for (int i = 0; i < params.size(); i++) {  
	                pstmt.setObject(index++, params.get(i));  
	            }  
	        }  
	        //执行查询
	        resultSet = pstmt.executeQuery();  
	        ResultSetMetaData metaData = resultSet.getMetaData();  
	        int cols_len;
			cols_len = metaData.getColumnCount();
			while (resultSet.next()) {  
				//将数据封装到map
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (int i = 0; i < cols_len; i++) {  
	                String cols_name = metaData.getColumnName(i + 1);  
	                Object cols_value = resultSet.getObject(cols_name);  
	                if (cols_value == null) {  
	                    cols_value = "";  
	                }  
	                map.put(cols_name, cols_value);  
	            }  
	            list.add(map);  
	        }  
		} catch (SQLException e) {
			throw new RuntimeException("执行预处理查询sql异常！", e); 
		} finally{
			releaseConn() ;
		} 
        return list; 
	}
	

    /**
     * <p>Description:释放数据库连接资源<p>
     * @author wanglei 2017年10月28日
     */
    public void releaseConn() {  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (pstmt != null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}
