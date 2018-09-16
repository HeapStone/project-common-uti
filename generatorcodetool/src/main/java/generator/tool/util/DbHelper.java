package generator.tool.util;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

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
			CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
			CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
			DRIVER = tempCommonCfg.getDriver();
			URL =  tempCommonCfg.getUrl()+"?useUnicode=true&characterEncoding=UTF8&useSSL=false&autoReconnect=true&amp;autoReconnectForPools=true";
			USERNAME =  tempCommonCfg.getUserName();
			PASSWORD =  tempCommonCfg.getPassword();
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
		Properties props =new Properties();
		try {
			Class.forName(DRIVER);
			props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            props.setProperty("remarks", "true"); //设置可以获取remarks信息 
            props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
		    connection = DriverManager.getConnection(URL,props);
		} catch (Exception e) {
			throw new RuntimeException("获取数据库驱动信息异常！", e);
		}

		return connection;
	}

	
}
