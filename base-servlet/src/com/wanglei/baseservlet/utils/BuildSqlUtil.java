package com.wanglei.baseservlet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.wanglei.baseservlet.exception.BusinessException;
import com.wanglei.baseservlet.model.Pager;

public class BuildSqlUtil {
	/**
	 * <p>Description:根据实体类获取新增语句<p>
	 * @param obj 实体类
	 * @return 新增sql
	 * @author wanglei 2017年12月21日
	 */
	public static String getInsertSqlByBean(Object obj){
		StringBuffer sqlbf = new StringBuffer(BuildSqlUtil.getBeginInertSqlByBean(obj));
		Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
		if(tablecoul == null) throw new BusinessException("类的属性为空！不能插入!");
		Set<String> keys = tablecoul.keySet();
		int j = 0;
		sqlbf.append("  values( ");
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
	 * <p>Description:根据实体bean获取预处理的sql<p>
	 * @param obj 实体类
	 * @return SQL语句
	 * @author wanglei 2017年12月24日
	 */
	@SuppressWarnings("unused")
	public static String getInsertPreSqlByBean(Object obj){
		StringBuffer sqlbf = new StringBuffer(BuildSqlUtil.getBeginInertSqlByBean(obj));
		Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
		if(tablecoul == null) throw new BusinessException("类的属性为空！不能插入!");
		Set<String> keys = tablecoul.keySet();
		int j = 0;
		sqlbf.append("  values( ");
		for(String key:keys){
			j++;
			sqlbf.append(" ? ");
			if(j<=keys.size()-1){
				sqlbf.append(",");
			}
		}
		sqlbf.append(")");
		return sqlbf.toString() ;
	}
	/**
	 * <p>Description:<p>
	 * @param obj
	 * @return
	 * @author wanglei 2017年12月24日
	 */
	public static String getBeginInertSqlByBean(Object obj){
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
		sqlbf.append(" )");
		return sqlbf.toString();
	}
	/**
	 * <p>Description:根据实体类和主键名获取更新单表的sql<p>
	 * @param obj 实体类
	 * @param pk 主键名
	 * @return
	 * @author wanglei 2017年12月21日
	 */
	public static String getUpdateSqlByBeanAndPk(Object obj,String pk){
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
	 * <p>Description:根据主键和对象获取更新的预处理SQL<p>
	 * @param obj bean
	 * @param pk 主键
	 * @return 返回值
	 * @author wanglei 2017年12月24日
	 */
	public static String getUpdatePreSqlByBeanAndPk(Object obj,String pk){
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
				sqlbf.append(ColumnToPropertyUtil.underscoreName(key)+"= ? ");
				if(i<=keys.size()-1){
					sqlbf.append(",");
				}
			}
		}
		Object pkvalue = tablecoul.get(ColumnToPropertyUtil.camelName(pk));
		if(null!=pkvalue){
			sqlbf.append(" WHERE "+pk +"= ? ");
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
	public static String getDeleteSqlByBeanAndPk(Object obj ,String pk){
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
	/**
	 * <p>Description:根据实体类获取查询语句<p>
	 * @param obj 实体类
	 * @param pk 主键
	 * @return
	 * @author wanglei 2017年12月23日
	 */
	public static String getSelectSqlByBeanAndPk(Object obj ,String pk){
		StringBuffer sqlbf = new StringBuffer("SELECT * FROM ");
		sqlbf.append(ColumnToPropertyUtil.getTableNameByBeanName(obj.getClass()));
		Map<String,Object> tablecoul =  ReflectUtils.getBeanProperty(obj);
		if(tablecoul == null) throw new BusinessException("类的属性为空！不能查询!");
		Object pkvalue = tablecoul.get(ColumnToPropertyUtil.camelName(pk));
		if(StringUtils.isBlank(pk))throw new BusinessException("主键名称不能为空!");
		if(null!=pkvalue){
			sqlbf.append(" WHERE "+pk +"= '"+ pkvalue+"' ");
		}else{
			sqlbf.append(" WHERE 1 !=1");
		}
		return sqlbf.toString();
	}
	/**
	 * <p>Description：根据实体类获取查询语句<p>
	 * @param clazz 实体类class
	 * @return
	 * @author wanglei 2017年12月23日
	 */
	public static String getSelectSqlByBean(Class<?> clazz){
		StringBuffer sqlbf = new StringBuffer("SELECT  * FROM ");
		sqlbf.append(ColumnToPropertyUtil.getTableNameByBeanName(clazz));
		sqlbf.append(" WHERE 1 = 1 ");
		return sqlbf.toString() ;
	}
	/**
	 * <p>Description:根据分页对象获取参数信息<p>
	 * @param pager
	 * @return
	 * @author wanglei 2017年12月19日
	 */
	public static Map<String ,Object> getPagerProgramsAndSqlByPager(Pager<?> pager,Class<?> clazz){
		Map<String ,Object> pramsAndSql = new HashMap<String ,Object>();
		List<Object > prams = new ArrayList<>();
		List<Object > noPagePrams = new ArrayList<>();
		StringBuffer sbf = new StringBuffer(BuildSqlUtil.getSelectSqlByBean(clazz));
		Map<String,String > pram = pager.getParams();
		if(null!=pram && pram.size()>0){
			Set<String> keys = pram.keySet();
			int i = 0;
			for(String key: keys){
				i++;
				sbf.append(" AND "+ColumnToPropertyUtil.underscoreName(key)+" = ");
				prams.add(pram.get(key));
				noPagePrams.add(pram.get(key));
				sbf.append("?");
					if(i<=keys.size()-1){
						sbf.append(",");
					}
			}
		}
		pramsAndSql.put("noPagePrams",noPagePrams);
		if(pager.getOffset()!=0 && pager.getSize()!=0){
			prams.add(pager.getOffset()-1);
			prams.add(pager.getSize());
			sbf.append(" LIMIT ? , ? ");
		}
		pramsAndSql.put("prams",prams);
		//System.out.println(prams);
		//System.out.println(sbf.toString());
		pramsAndSql.put("sql", sbf.toString());
		return pramsAndSql;
	}
	/**
	 * <p>Description:根据分页对象和sql 获得分页查询的参数和sql<p>
	 * @param pager 分页对象
	 * @param sql 预处理sql
	 * @return
	 * @author wanglei 2017年12月24日
	 */
	public static Map<String ,Object> getPagerProgramsByPagerAndSql(Pager<?> pager,String sql){
		Map<String ,Object> pramsAndSql = new HashMap<String ,Object>();
		List<Object > prams = new ArrayList<>();
		List<Object > noPagePrams = new ArrayList<>();
		Map<String,String > pram = pager.getParams();
		if(null!=pram && pram.size()>0){
			Set<String> keys = pram.keySet();
			for(String key: keys){
				prams.add(pram.get(key));
				noPagePrams.add(pram.get(key));
			}
		}
		pramsAndSql.put("noPagePrams",noPagePrams);
		if(pager.getOffset()!=0 && pager.getSize()!=0){
			prams.add(pager.getOffset()-1);
			prams.add(pager.getSize());
			 sql+=" LIMIT ? , ? ";
		}
		pramsAndSql.put("prams",prams);
		//System.out.println(prams);
		//System.out.println(sbf.toString());
		pramsAndSql.put("sql", sql);
		return pramsAndSql;
	}
}
