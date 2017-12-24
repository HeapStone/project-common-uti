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

/**
 * <p>
 * Title: 获取数据连接工具的类
 * </p>
 * <p>
 * Description: 通过jdbc驱动获取数据库连接，提供获取驱动方法，关闭连接方法，执行sql方法
 * </p>
 * 
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei 2017年10月27日
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
	private static String PASSWORD = null;
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
	private ResultSet resultSet = null;
	private static DbHelper dh = null;
	static {
		// 初始化配置
		inintConfig();
	}

	private DbHelper() {
	}

	public static DbHelper getDbHelperInstance() {
		if (null == dh) {
			synchronized (DbHelper.class) {
				// 该同步代码块是为了解决多线程状态下 获取单例对象为null的问题
				dh = new DbHelper();
			}
		}
		return dh;
	}

	/**
	 * <p>
	 * Description:初始化配置信息
	 * <p>
	 * 
	 * @author wanglei 2017年10月27日
	 */
	public static void inintConfig() {
		try {
			InputStream inps = DbHelper.class
					.getResourceAsStream("/resources/db.properties");
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
	 * <p>
	 * Description:获取数据库连接对象
	 * <p>
	 * 
	 * @return 数据库连接对象
	 * @author wanglei 2017年10月27日
	 */
	public Connection getConnection() {
		// 反射获取driverManager
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException("获取数据库驱动信息异常！", e);
		}

		return connection;
	}

	/**
	 * <p>
	 * Description:执行sql的公共方法
	 * <p>
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param params
	 *            参数
	 * @return 执行sql影响的条数
	 * @author wanglei 2017年10月27日
	 */
	public long excuteSqlByPrepareStatement(String sql, List<?> params) {
		if (null == connection) {
			this.getConnection();
		}
		System.out.println(sql);
		long excuNum = -1;
		try {
			if (connection.isClosed()) {
				System.out.println("-----------重新获取连接-------------");
				this.getConnection();
			}
			pstmt = connection.prepareStatement(sql);
			int index = 1;
			if (null != params && !params.isEmpty()) {
				for (int i = 0, j = params.size(); i < j; i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			excuNum = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("执行预处理sql异常！", e);
		} finally {
			releaseConn();
		}

		return excuNum;
	}

	/**
	 * <p>
	 * Description:执行sql语句
	 * <p>
	 * 
	 * @param sql
	 *            sql 语句
	 * @return
	 * @author wanglei 2017年12月21日
	 */
	public long excuteSqlByStatement(String sql) {
		Statement stmt = null;
		if (null == connection) {
			this.getConnection();
		}
		System.out.println(sql);
		long excuNum = -1;
		try {
			if (connection.isClosed()) {
				System.out.println("-----------重新获取连接---------------");
				this.getConnection();
			}
			stmt = connection.createStatement();
			excuNum = stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw new RuntimeException("执行预处理sql异常！", e);
		} finally {
			releaseConn();
		}

		return excuNum;
	}
	/**
	 * <p>Description:批量执行SQL语句<p>
	 * @param sqls SQL集合
	 * @author wanglei 2017年12月24日
	 */
	public void batchExcuteSqlByStatement(List<String> sqls) {
		Statement stmt = null;
		if (null == connection) {
			this.getConnection();
		}
		try {
			if (connection.isClosed()) {
				System.out.println("-----------重新获取连接-----------------");
				this.getConnection();
			}
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			for(String sql : sqls){
				System.out.println(sql);
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			connection.commit();
		} catch (Exception e) {
			throw new RuntimeException("执行预处理sql异常！", e);
		} finally {
			releaseConn();
		}

	}
	/**
	 * <p>
	 * Description:执行查询语句的方法
	 * <p>
	 * 
	 * @param sql
	 *            查询的sql
	 * @param params
	 *            参数
	 * @return 查询结果
	 * @author wanglei 2017年10月27日
	 */
	public List<Map<String, Object>> excuteQuerySql(String sql, List<?> params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (null == connection) {
			this.getConnection();
		}

		try {
			if (connection.isClosed()) {
				System.out.println("-----------重新获取连接------------");
				this.getConnection();
			}
			// 预处理sql
			System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			if (params != null && !params.isEmpty()) {
				// 赋值参数
				int index = 1;
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			// 执行查询
			resultSet = pstmt.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int colsLen;
			colsLen = metaData.getColumnCount();
			while (resultSet.next()) {
				// 将数据封装到map
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
		} finally {
			releaseConn();
		}
		return list;
	}
	/**
	 * <p>Description:预处理的sql语句批量更新或者添加<p>
	 * @param sql  SQL语句
	 * @param batchprams 批处理参数
	 * @author wanglei 2017年12月24日
	 */
	public void bacthExcutePreSql(String sql,List<List<Object>> batchprams){
		if (null == connection) {
			this.getConnection();
		}
		System.out.println(sql);
		try {
			if (connection.isClosed()) {
				this.getConnection();
			}
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			for(List<Object> params : batchprams){
				int index = 1;
				if (null != params && !params.isEmpty()) {
					for (int i = 0, j = params.size(); i < j; i++) {
						pstmt.setObject(index++, params.get(i));
					}
				}
				pstmt.executeUpdate();
			}
			connection.commit();
		} catch (Exception e) {
			throw new RuntimeException("执行预处理sql异常！", e);
		} finally {
			releaseConn();
		}

	}
	/**
	 * <p>
	 * Description:释放数据库连接资源
	 * <p>
	 * 
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
	 * <p>
	 * Description:将查询的map中的表字段转换成实体类属性
	 * <p>
	 * 
	 * @param argmap
	 *            查询结果集
	 * @return 转后之后map
	 * @author wanglei 2017年12月17日
	 */
	public Map<String, Object> convertTableColumnToProperty(
			Map<String, Object> argmap) {
		Map<String, Object> resmap = new HashMap<>();
		if (null != argmap) {
			Set<String> keys = argmap.keySet();
			for (String key : keys) {
				resmap.put(ColumnToPropertyUtil.camelName(key), argmap.get(key));
			}
		}
		return resmap;
	}

	/**
	 * <p>
	 * Description:根据参数和sql返回一个实体类集合
	 * <p>
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            参数
	 * @param clazz
	 *            类的class
	 * @return 实体类列表
	 * @author wanglei 2017年12月16日
	 */
	public List<Object> excuteQueryListBySqlWithParams(String sql,
			List<?> params, Class<?> clazz) {
		// 查询对象返回map对象
		List<Map<String, Object>> list = this.excuteQuerySql(sql, params);
		return getobjListByMaps(list, clazz);
	}

	/**
	 * <p>
	 * Description:根据参数和sql返回一个实体类集合
	 * <p>
	 * 
	 * @param sql
	 *            sql
	 * @param clazz
	 *            类的class
	 * @return 实体类列表
	 * @author wanglei 2017年12月16日
	 */
	public List<Object> excuteQueryListBySql(String sql, Class<?> clazz) {
		// 查询对象返回map对象
		List<Map<String, Object>> list = this.excuteQuerySql(sql, null);
		return getobjListByMaps(list, clazz);
	}

	/**
	 * <p>
	 * Description:根据sql和参数返回一个实体类
	 * <p>
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            参数
	 * @param clazz
	 *            类的class
	 * @return 实体类
	 * @author wanglei 2017年12月16日
	 */
	public Object excuteQuerySql(String sql, List<?> params, Class<?> clazz) {
		List<Object> results = new ArrayList<Object>();
		results = excuteQueryListBySqlWithParams(sql, params, clazz);
		return results.size() > 0 ? results.get(0) : null;
	}

	/**
	 * <p>
	 * Description:根据预处理sql返回map集合
	 * <p>
	 * 
	 * @param sql
	 *            预处理SQL
	 * @param params
	 *            参数
	 * @param isConvert
	 *            是否将map键准换成实体类属性
	 * @return
	 * @author wanglei 2017年12月24日
	 */
	public List<Map<String, Object>> excuteQuerySql(String sql, List<?> params,
			boolean isConvert) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> noConvertResults = excuteQuerySql(sql, params);
		if (isConvert) {
			for (Map<String, Object> obj : noConvertResults) {
				results.add(this.convertTableColumnToProperty(obj));
			}

		} else {
			results.addAll(noConvertResults);
		}
		return results;
	}

	/**
	 * <p>
	 * Description:将map结果集转换成实体类结果集
	 * <p>
	 * 
	 * @param list
	 *            结果集合
	 * @param clazz
	 *            实体类的class
	 * @return
	 * @author wanglei 2017年12月17日
	 */
	private List<Object> getobjListByMaps(List<Map<String, Object>> list,
			Class<?> clazz) {
		List<Object> results = new ArrayList<Object>();
		if (null != list) {
			// 进行对象封装
			// 将实体属性和值封装到map集合中并返回实体类
			for (Map<String, Object> re : list) {
				Object reobj = ReflectUtils.setBeanProperty(
						this.convertTableColumnToProperty(re), clazz);
				results.add(reobj);
			}
		}
		return results;
	}

	/**
	 * <p>
	 * Description:向数据库添加实体类
	 * <p>
	 * 
	 * @param obj
	 *            实体bean
	 * @author wanglei 2017年12月21日
	 */
	public void add(Object obj) {
		String sql = BuildSqlUtil.getInsertSqlByBean(obj);
		this.excuteSqlByStatement(sql);

	}

	/**
	 * <p>
	 * Description:以实体类更新数据库数据
	 * <p>
	 * 
	 * @param obj
	 *            实体类
	 * @param pk
	 *            主键名称
	 * @author wanglei 2017年12月21日
	 */
	public void update(Object obj, String pk) {
		String sql = BuildSqlUtil.getUpdateSqlByBeanAndPk(obj, pk);
		this.excuteSqlByStatement(sql);
	}

	/**
	 * <p>
	 * Description:根据实体类和主键删除
	 * <p>
	 * 
	 * @param obj
	 *            实体类
	 * @param pk
	 *            主键
	 * @author wanglei 2017年12月23日
	 */
	public void delete(Object obj, String pk) {
		String sql = BuildSqlUtil.getDeleteSqlByBeanAndPk(obj, pk);
		this.excuteSqlByStatement(sql);
	}

	/**
	 * <p>
	 * Description:根据实体类和主键名称返回实体类对象-实体类的主键值不为空
	 * <p>
	 * 
	 * @param obj
	 *            实体类
	 * @param pk
	 *            主键名称
	 * @return
	 * @author wanglei 2017年12月23日
	 */
	public Object load(Object obj, String pk) {
		String sql = BuildSqlUtil.getSelectSqlByBeanAndPk(obj, pk);
		return this.excuteQuerySql(sql, null, obj.getClass());
	}
	/**
	 * <p>Description:批量添加<p>
	 * @param objs 要添加的实体类
	 * @author wanglei 2017年12月24日
	 */
	public void batchAdd(List<?> objs) {
		if(null != objs&& objs.size()>0){
			String sql = BuildSqlUtil.getInsertPreSqlByBean(objs.get(0));
			 List<List<Object>> prams = new ArrayList<>();
					for(Object obj:objs){
						List<Object> pram = new ArrayList<>();
						Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
						Set<String> keys = tablecoul .keySet();
						for(String key :keys){
							pram.add(tablecoul.get(key));
						}
						prams.add(pram);
					}
			this.bacthExcutePreSql(sql, prams);		
		}
	}
	/**
	 * <p>Description:批量删除<p>
	 * @param objs 实体类
	 * @param pk 主键
	 * @author wanglei 2017年12月24日
	 */
	public void batchDelete(List<?> objs, String pk) {
		List<String> sqls = new ArrayList<>();
		if(null != objs&& objs.size()>0){
			for(Object obj : objs){
				String sql = BuildSqlUtil.getDeleteSqlByBeanAndPk(obj, pk);
				sqls.add(sql);
			}
			this.batchExcuteSqlByStatement(sqls);
		}
	}
	/**
	 * <p>Description:批量修改<p>
	 * @param objs 实体类 
	 * @param pk 主键
	 * @author wanglei 2017年12月24日
	 */
	public void batchUpdate(List<?> objs, String pk) {
		List<String> sqls = new ArrayList<>();
		if(null != objs&& objs.size()>0){
			for(Object obj : objs){
				String sql = BuildSqlUtil.getUpdateSqlByBeanAndPk(obj, pk);
				sqls.add(sql);
			}
			this.batchExcuteSqlByStatement(sqls);
		}
	}
}
