package com.wanglei.baseservlet.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.wanglei.baseservlet.exception.BusinessException;




/**
 * <p>Title:反射工具类 </p>
 * <p>Description: 该工具类主利用java的反射机制获的类相关信息</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月16日
 */
public class ReflectUtils {

   /**
 * <p>Description:根据类的Class 获取所有的属性<p>
 * @param clazz Class
 * @return 类的所有属性
 * @author wanglei 2017年12月16日
 */
	public static Field [] getClassFileds(Class<?> clazz){
		   Field [] fields = null;
		   if(null != clazz){
			   fields = clazz.getDeclaredFields();
		   }
		   return fields;
  }
   /**
 * <p>Description:返回类的所有方法<p>
 * @param clazz 类的Class
 * @return 类的方法
 * @author wanglei 2017年12月16日
 */
 public static Method [] getClassMethods(Class<?> clazz){
	   Method [] methods = null;
	   if(null != clazz){
		   methods = clazz.getDeclaredMethods();
	   }
	   return methods;
   }
  /**
 * <p>Description:根据属性名获取geeter的方法名称<p>
 * @param fieldName 属性名称
 * @param clazz Class
 * @return 属性的Getter方法名称
 * @author wanglei 2017年12月16日
 */
public static String getGetterMethodByfieldName(String fieldName,Class<?> clazz){
	  String getterName = null;
	  try {
		PropertyDescriptor pd = new PropertyDescriptor(fieldName,clazz);
		Method method = pd.getReadMethod();
		getterName = method.getName();
	} catch (IntrospectionException e) {
		e.printStackTrace();
	}
	  return getterName;
	 
  }
  /**
 * <p>Description:根据属性名称和Class 获取 setter方面名称 <p>
 * @param fieldName 属性名
 * @param clazz 类的class
 * @return setter方法名称
 * @author wanglei 2017年12月16日
 */
public static String getSetterMethodByfieldName(String fieldName,Class<?> clazz){
	 String setterName = null;
	 try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName,clazz);
			Method method = pd.getWriteMethod();
			setterName = method.getName();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	 return setterName;
 }
/**
 * <p>Description：反射调用实体类的setter方法返回实体类<p>
 * @param values 属性-值 类型的集合
 * @param clazz 类的Class
 * @return 赋值好的实体类
 * @author wanglei 2017年12月16日
 */
@SuppressWarnings("rawtypes")
public static Object setBeanProperty (Map<String,Object> values,Class<?> clazz ){
	Object obj = null;
	try {
		 obj =  null == clazz? null: clazz.newInstance();
		if(null != values){
			Set<String> keys = values.keySet();
			for(String key :keys){
				PropertyDescriptor pd = new PropertyDescriptor(key,clazz);
				//得到get方法
				Method method = pd.getWriteMethod();
				if(null != method ){
					Class[] argsClass = method.getParameterTypes();
					if(null!=argsClass && argsClass.length>0){
						//类型转换
						//调用set方法赋值
						method.invoke(obj, new Object[]{convertProperType(argsClass[0].toString(),values.get(key).toString())});
					}else{
						throw new BusinessException(clazz.getName()+ "属性["+key+"] setter方法参数类型不匹配！");
					}
					
				}else{
					throw new BusinessException(clazz.getName()+ "属性["+key+"] 未定义setter方法!");
				}
			}
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	return obj;
}
/**
 * <p>Description:根据参数类型转换值<p>
 * @param methodType 方法的参数类型
 * @param ob 参数
 * @return 转换后的参数
 * @author wanglei 2017年12月16日
 */
private static Object convertProperType(String methodType,Object ob){
	 if( ob == null )return null;
	//System.out.println(methodType);
	if(StringUtils.isBlank(methodType)) return null;
	methodType = methodType.replace("class", "").trim();
	//System.out.println(methodType);
	if( "java.math.BigDecimal".equalsIgnoreCase(methodType)){
        try {
            return new BigDecimal(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if( "java.lang.String".equalsIgnoreCase(methodType)){
        try {
            return ob.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }else if( "java.lang.Integer".equalsIgnoreCase(methodType)){
        try {
            return Integer.parseInt(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if( "java.util.Date".equalsIgnoreCase(methodType)){
        try {
            return DateUtil.getDate(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if( "long".equalsIgnoreCase(methodType)){
        try {
            return Long.parseLong(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if( "java.sql.Timestamp".equalsIgnoreCase(methodType)){
        try {
            return DateUtil.getTime(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    else if( "double".equalsIgnoreCase(methodType)){
        try {
            return Double.parseDouble(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }else if( "java.sql.Date".equalsIgnoreCase(methodType)){
        try {
            return DateUtil.getStringSqlDate(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return null;
  }
public static Map<String,Object> getBeanProperty(Object obj){
	Map<String,Object> remaps = new HashMap<String, Object>();
	Class<?> clazz = null == obj? null: obj.getClass();
	try {
		if(null!=clazz){
			Field [] fields = getClassFileds(clazz);
			 for(Field field :fields){
				 String fieldName = field.getName();
				 if(!fieldName.equalsIgnoreCase("serialVersionUID")){
					 PropertyDescriptor pd = new PropertyDescriptor(fieldName,clazz);
						Method method = pd.getReadMethod();
						if(null != method){
							remaps.put(fieldName, method.invoke(obj, new Object []{}));
						}else{
							throw new BusinessException(clazz.getName()+ "属性["+fieldName+"] 未定义getter方法!");
						}
				 }
			 }
		}
	 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return remaps;
}
}
