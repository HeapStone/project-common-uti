package generator.tool.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import generator.tool.model.codedata.DomainPropertiesModel;
import org.apache.commons.lang.StringUtils;


public class ColumnToPropertyUtil {
	/**
	 * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HelloWorld->HELLO_WORLD
	 * @param name 转换前的驼峰式命名的字符串
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underscoreName(String name) {
	    StringBuilder result = new StringBuilder();
	    if (name != null && name.length() > 0) {
	        // 将第一个字符处理成大写
	        result.append(name.substring(0, 1).toUpperCase());
	        // 循环处理其余字符
	        for (int i = 1; i < name.length(); i++) {
	            String s = name.substring(i, i + 1);
	            // 在大写字母前添加下划线
	            if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
	                result.append("_");
	            }
	            // 其他字符直接转成大写
	            result.append(s.toUpperCase());
	        }
	    }
	    return result.toString();
	}
	 
	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
	    StringBuilder result = new StringBuilder();
	    // 快速检查
	    if (name == null || name.isEmpty()) {
	        // 没必要转换
	        return "";
	    } else if (!name.contains("_")) {
	        // 不含下划线，仅将首字母小写
	        return name.substring(0, 1).toLowerCase() + name.substring(1);
	    }
	    // 用下划线将原始字符串分割
	    String camels[] = name.split("_");
	    for (String camel :  camels) {
	        // 跳过原始字符串中开头、结尾的下换线或双重下划线
	        if (camel.isEmpty()) {
	            continue;
	        }
	        // 处理真正的驼峰片段
	        if (result.length() == 0) {
	            // 第一个驼峰片段，全部字母都小写
	            result.append(camel.toLowerCase());
	        } else {
	            // 其他的驼峰片段，首字母大写
	            result.append(camel.substring(0, 1).toUpperCase());
	            result.append(camel.substring(1).toLowerCase());
	        }
	    }
	    return result.toString();
	}
   /**
 * <p>Description:根据实体类的名字获取表名<p>
 * @return
 * @author wanglei 2017年12月20日
 */
public static String getTableNameByBeanName(Class<?> clazz){
	if(null == clazz) return null;
	String tableName = underscoreName("tb"+clazz.getSimpleName());
	return StringUtils.isBlank(tableName)?null:tableName;
	   
   }
/**
 * <p>Description:根据表名获取实体类名称<p>
 * @param tablename 表名
 * @return
 * @author wanglei 2017年12月20日
 */
public static String getBeanNameByTableName(String tablename){
	String beanName = camelName(tablename);
	return StringUtils.isBlank(beanName)?null:beanName.substring(0, 1).toUpperCase()+beanName.substring(1, beanName.length());
   }
/**
 * <p>Description:根据实体类属性获取导入的包字符串<p>
 * @param beanProperties 
 * @return  要导入的属性类型包
 * @author wanglei 2018年1月21日
 */
@SuppressWarnings("unchecked")
public static List<String> getImportStrByBeanProperType(List<DomainPropertiesModel> beanProperties){
	List<String> importStr = new ArrayList<String>();
	for(DomainPropertiesModel beanPropertie : beanProperties){
		String type = beanPropertie.getPropertType();
		String typePackageStr = null;
		 if("Timestamp".equalsIgnoreCase(type)){
			 typePackageStr = "java.sql.Timestamp";
		}else if("Date".equalsIgnoreCase(type)){
			typePackageStr = "java.sql.Date";
		}
		if(StringUtils.isNotBlank(typePackageStr)){
			importStr.add(typePackageStr);
		}
	}
	return ColumnToPropertyUtil.removeDuplicate(importStr);
}
/**
 * <p>Description:获取属性的clasName<p>
 * @return
 * @author wanglei 2018年1月21日
 */
public static String getProperTypeClassName (String type){
	String typeClassName = null;
	 if("Timestamp".equalsIgnoreCase(type)){
		 typeClassName = "java.sql.Timestamp";
	}else if("Date".equalsIgnoreCase(type)){
		typeClassName = "java.sql.Date";
	}else if("double".equalsIgnoreCase(type)){
		typeClassName = "java.lang.Double";
	}else if("int".equalsIgnoreCase(type)){
		typeClassName = "java.lang.Integer";
	}else if("String".equalsIgnoreCase(type)){
		typeClassName = "java.lang.String";
	}else if("Integer".equalsIgnoreCase(type)){
		typeClassName = "java.lang.Integer";
	}
	 
	return typeClassName;
	
}
@SuppressWarnings({ "rawtypes", "unchecked" })
private static List removeDuplicate(List<String> arlList)      
{      
    HashSet h = new HashSet(arlList);      
    arlList.clear();      
    arlList.addAll(h);  
   return arlList;
}     
/** 
 * <p>Description:根据数据库类型获取java 类型<p>
 * @param columnType
 * @return
 * @author wanglei 2018年1月21日
 */
public static String getBenaPropertiesTypeByTableColumn(String columnType){
	String javaType = null;
	if("VARCHAR".equalsIgnoreCase(columnType)){
		return "String";
	}else if("TIMESTAMP".equalsIgnoreCase(columnType)){
	  return "Timestamp";
	}else if("Date".equalsIgnoreCase(columnType)){
		  return "Date";
	}else if("DOUBLE".equalsIgnoreCase(columnType)){
		 return "double";
	}else if("int".equalsIgnoreCase(columnType)){
		 return "Integer";
	}
	return javaType ;
 }
	public static String getJdbcTypeByTableColumn(String columnType){
		String javaType = null;
		if("VARCHAR".equalsIgnoreCase(columnType)){
			return "VARCHAR";
		}else if("TIMESTAMP".equalsIgnoreCase(columnType)){
			return "TIMESTAMP";
		}else if("Date".equalsIgnoreCase(columnType)){
			return "DATE";
		}else if("DOUBLE".equalsIgnoreCase(columnType)){
			return "DOUBLE";
		}else if("INT".equalsIgnoreCase(columnType)){
			return "INTEGER";
		}
		return javaType ;
	}
	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}


	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
		if(Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
