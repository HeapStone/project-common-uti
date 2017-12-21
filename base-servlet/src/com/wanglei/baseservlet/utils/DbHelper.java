package com.wanglei.baseservlet.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.wanglei.baseservlet.exception.BusinessException;

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
	 * <p>Description:执行sql语句<p>
	 * @param sql sql 语句
	 * @return
	 * @author wanglei 2017年12月21日
	 */
	public long excuteSqlByStatement(String sql){
		Statement stmt = null;
		if(null == connection){
			this.getConnection();
		}
		long excuNum = -1;
		try {
			stmt = connection.createStatement();
			excuNum = stmt.executeUpdate(sql); 
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
	        int colsLen;
	        colsLen = metaData.getColumnCount();
			while (resultSet.next()) {  
				//将数据封装到map
	            Map<String, Object> map = new HashMap<String, Object>();  
	            for (int i = 0; i < colsLen; i++) {  
	                String colsName = metaData.getColumnName(i + 1);  
	                Object colsValue = resultSet.getObject(colsName);  
	                if (colsValue == null) {  
	                	colsValue = "";  
	                }  
	                map.put(colsName, colsValue);  
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
	 * <p>Description:根据参数和sql返回一个实体类集合<p>
	 * @param sql sql
	 * @param params 参数
	 * @param clazz 类的class
	 * @return 实体类列表
	 * @author wanglei 2017年12月16日
	 */
	public List<Object> excuteQueryListBySqlWithParams(String sql,List<?> params,Class<?> clazz){
		//查询对象返回map对象
		List<Map<String, Object>> list = this.excuteQuerySql(sql, params);
		return getobjListByMaps(list,clazz);
	}
	/**
	 * <p>Description:根据参数和sql返回一个实体类集合<p>
	 * @param sql sql
	 * @param clazz 类的class
	 * @return 实体类列表
	 * @author wanglei 2017年12月16日
	 */
	public List<Object> excuteQueryListBySql(String sql,Class<?> clazz){
		//查询对象返回map对象
		List<Map<String, Object>> list = this.excuteQuerySql(sql, null);
		return getobjListByMaps(list,clazz);
	}
	/**
	 * <p>Description:根据sql和参数返回一个实体类<p>
	 * @param sql sql
	 * @param params 参数
	 * @param clazz 类的class
	 * @return 实体类
	 * @author wanglei 2017年12月16日
	 */
	public Object excuteQuerySql(String sql,List<?> params,Class<?> clazz){
		List<Object> results = new ArrayList<Object>();
		results=excuteQueryListBySqlWithParams( sql, params, clazz);
		return results.size()>0?results.get(0):null;
	}
	public List<Map<String,Object>> excuteQuerySql(String sql,List<?> params,boolean isConvert){
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> noConvertResults =excuteQuerySql( sql, params);
		if(isConvert){
			 for(Map<String,Object> obj :noConvertResults){
				 results.add(this.convertTableColumnToProperty(obj));
			 }
			
		}else{
			results.addAll(noConvertResults);
		}
		return results;
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
  /**
 * <p>Description:将查询的map中的表字段转换成实体类属性<p>
 * @param argmap 查询结果集
 * @return 转后之后map
 * @author wanglei 2017年12月17日
 */
public Map<String,Object> convertTableColumnToProperty(Map<String,Object> argmap){
	  Map<String,Object>  resmap = new HashMap<>();
	  if(null!= argmap){
		  Set<String> keys = argmap.keySet();
		  for(String key :keys){
			  resmap.put(ColumnToPropertyUtil.camelName(key), argmap.get(key));
		  }
	  }
	  return resmap;
  }
/**
 * <p>Description:将map结果集转换成实体类结果集<p>
 * @param list 结果集合
 * @param clazz 实体类的class
 * @return
 * @author wanglei 2017年12月17日
 */
private  List<Object> getobjListByMaps(List<Map<String, Object>> list,Class<?> clazz){
	List<Object> results = new ArrayList<Object>();
	if(null!=list){
		//进行对象封装
		//将实体属性和值封装到map集合中并返回实体类
		for(Map<String,Object> re:list){
			Object reobj= ReflectUtils.setBeanProperty(this.convertTableColumnToProperty(re), clazz);
			results.add(reobj);
		}
	}
	return results;
}
/**
 * <p>Description:向数据库添加实体类<p>
 * @param obj 实体bean
 * @author wanglei 2017年12月21日
 */
public void add(Object obj){
	String sql = getInsertSqlByBean(obj);
	System.out.println(sql);
	this.excuteSqlByStatement(sql);
	
}
/**
 * <p>Description:以实体类更新数据库数据<p>
 * @param obj 实体类
 * @param pk 主键名称
 * @author wanglei 2017年12月21日
 */
public void update(Object obj,String pk){
	String sql = this.getUpdateSqlByBeanAndPk(obj, pk);
	System.out.println(sql);
	this.excuteSqlByStatement(sql);
}
public void delete(Object obj,String pk){
	String sql = this.getDebleteSqlByBeanAndPk(obj, pk);
	System.out.println(sql);
	this.excuteSqlByStatement(sql);
}
/**
 * <p>Description:根据实体类获取新增语句<p>
 * @param obj 实体类
 * @return 新增sql
 * @author wanglei 2017年12月21日
 */
public String getInsertSqlByBean(Object obj){
	StringBuffer sqlbf = new StringBuffer("INSERT INTO ");
	sqlbf.append(ColumnToPropertyUtil.getTableNameByBeanName(obj.getClass())+" ( ");
	Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
	if(tablecoul == null) throw new BusinessException("类的属性为空！不能插入!");
	Set<String> keys = tablecoul.keySet();
	int i = 0;
	for(String key:keys){
		i ++;
		sqlbf.append(ColumnToPropertyUtil.underscoreName(key));
		if(i<=keys.size()-1){
			sqlbf.append(",");
		}
	}
	int j = 0;
	sqlbf.append(" ) values( ");
	for(String key:keys){
		j++;
		sqlbf.append("'"+tablecoul.get(key)+"'");
		if(j<=keys.size()-1){
			sqlbf.append(",");
		}
	}
	sqlbf.append(")");
	return sqlbf.toString() ;
}
/**
 * <p>Description:根据实体类和主键名获取更新单表的sql<p>
 * @param obj 实体类
 * @param pk 主键名
 * @return
 * @author wanglei 2017年12月21日
 */
public String getUpdateSqlByBeanAndPk(Object obj,String pk){
	StringBuffer sqlbf = new StringBuffer("UPDATE ");
	sqlbf.append(ColumnToPropertyUtil.getTableNameByBeanName(obj.getClass())+" SET ");
	Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
	if(tablecoul == null) throw new BusinessException("类的属性为空！不能更新!");
	if(StringUtils.isBlank(pk))throw new BusinessException("主键名称不能为空!");
	Set<String> keys = tablecoul.keySet();
	int i = 0;
	for(String key:keys){
		i++;
		if(null!=tablecoul.get(key)){
			sqlbf.append(ColumnToPropertyUtil.underscoreName(key)+"= '"+tablecoul.get(key)+"'");
			if(i<=keys.size()-1){
				sqlbf.append(",");
			}
		}
	}
	Object pkvalue = tablecoul.get(ColumnToPropertyUtil.camelName(pk));
	if(null!=pkvalue){
		sqlbf.append(" WHERE "+pk +"= '"+ pkvalue+"' ");
	}else{
		throw new BusinessException("主键不能为空！不能更新!");
	}
	return sqlbf.toString();
}
/**
 * <p>Description:根据主键名和实体类获取删除sql<p>
 * @param obj 实体类
 * @param pk 主键名
 * @return 删除sql
 * @author wanglei 2017年12月22日
 */
public String getDebleteSqlByBeanAndPk(Object obj ,String pk){
	StringBuffer sqlbf = new StringBuffer("DELETE FROM ");
	sqlbf.append(ColumnToPropertyUtil.getTableNameByBeanName(obj.getClass()));
	Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
	if(tablecoul == null) throw new BusinessException("类的属性为空！不能更新!");
	Object pkvalue = tablecoul.get(ColumnToPropertyUtil.camelName(pk));
	if(StringUtils.isBlank(pk))throw new BusinessException("主键名称不能为空!");
	if(null!=pkvalue){
		sqlbf.append(" WHERE "+pk +"= '"+ pkvalue+"' ");
	}else{
		throw new BusinessException("主键值为空！不能删除!");
	}
	return sqlbf.toString();
}

}


