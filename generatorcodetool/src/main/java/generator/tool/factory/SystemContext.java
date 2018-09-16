package generator.tool.factory;

import java.util.HashMap;
import java.util.Map;

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
	 * 系统临时存储
	 * 
	 */
	private static ThreadLocal  CONTEXT_MAP = new ThreadLocal ();
	private static SystemContext systemContext = null;
	/** * 工具类的protected构造方法. */
	private  SystemContext() {
	 }

	/**
	 * 获得线程中保存的属性.
	 *
	 * @param attribute
	 *            属性名称
	 * @return 属性值
	 */
	public static Object get(String attribute) {
		@SuppressWarnings("rawtypes")
		Map map = (Map) CONTEXT_MAP.get();
		return map.get(attribute);
	}

	/**
	 * 获得线程中保存的属性，使用指定类型进行转型.
	 *
	 * @param attribute
	 *            属性名称
	 * @param clazz
	 *            类型
	 * @param <T>
	 *            自动转型
	 * @return 属性值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String attribute, Class<T> clazz) {
		return (T) get(attribute);
	}

	/**
	 * 设置制定属性名的值.
	 *
	 * @param attribute
	 *            属性名称
	 * @param value
	 *            属性值
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void set(String attribute, Object value) {
		Map map = (Map) CONTEXT_MAP.get();

		if (map == null) {
			map = new HashMap();
			CONTEXT_MAP.set(map);
		}
		map.put(attribute, value);
	}

}
