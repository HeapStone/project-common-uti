package com.wanglei.baseservlet.dao;

import java.util.List;
import java.util.Map;

import com.wanglei.baseservlet.model.Pager;



/**
 * <p>Title:基础数据交互接口</p>
 * <p>Description: 基于jdbc方式的数据增删改查基本操作</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年10月27日
 */
public interface BaseDao <T> {
/**
 * <p>Description:用于添加的<p>
 * @param sql sql语句
 * @param args 参数
 * @author wanglei 2017年12月16日
 */
public void add(T t);
/**
 * <p>Description:批量添加<p>
 * @param objs 实体类
 * @author wanglei 2017年12月24日
 */
public void batchAdd(List<T> objs);
/**
 * <p>Description:批量更新<p>
 * @param objs 实体类集合
 * @param pk 主键
 * @author wanglei 2017年12月24日
 */
public void batchDelete(List<T> objs,String pk);
/**
 * <p>Description:批量更新<p>
 * @param objs 实体类集合
 * @param pk 主键
 * @author wanglei 2017年12月24日
 */
public void batchUpdate(List<T> objs,String pk);
/**
 * <p>Description:更新对象<p>
 * @param sql SQL语句 
 * @param args 更新的参数
 * @author wanglei 2017年12月16日
 */
public void update(T t,String pk);
/**
 * <p>Description:删除对象<p>
 * @param sql SQL语句
 * @param uuid
 * @author wanglei 2017年12月16日
 */
public void delete (T t ,String pk);
/**
 * <p>Description:根据实体类返回实体类<p>
 * @param t 实体bean
 * @param pk 主键名称
 * @return
 * @author wanglei 2017年12月23日
 */
public T load(T t,String pk);


/**
 * <p>Description:根根据主键查询<p>
 * @param sql 预处理SQL语句
 * @param id 主建值
 * @return 单个对象
 * @author wanglei 2017年12月16日
 */
public T findByUuid(String sql ,String id) ;
/**
 * <p>Description:根据主键返回对象<p>
 * @param sql 预处理SQL语句
 * @param id 主键
 * @return 对象
 * @author wanglei 2017年12月16日
 */
public T findByUuid(String sql ,Integer id);
/**
 * <p>Description:根据sql和参数添加<p>
 * @param sql 预处理SQL语句
 * @param args 参数
 * @author wanglei 2017年12月23日
 */
public void add(String sql ,List<Object> args);
/**
 * <p>Description:根据SQL和添加删除<p>
 * @param 预处理SQL语句
 * @param args
 * @author wanglei 2017年12月23日
 */
public void delete (String sql ,List<Object> args);
/**
 * <p>Description:根据SQL语句更新<p>
 * @param sql 预处理SQL语句
 * @param args 参数
 * @author wanglei 2017年12月23日
 */
public void update (String sql, List<Object> args);
/**
 * <p>Description:分页查询的方法<p>
 * @param pager 分页对象
 * @return
 * @author wanglei 2017年12月16日
 */

public Pager<T> findPager(Pager<T> pager);
/**
 * <p>Description:根据预处理sql和参数返回分页对象<p>
 * @param sql sql语句
 * @param prams 参数集合
 *  @param clazz bean 的class
 * @return 分页对象
 * @author wanglei 2017年12月24日
 */
public Pager<Object> findPagerByPreSqlWithParams(String sql, Pager<Object> pager,Class<?> clazz);

/**
 * <p>Description:查询list结果<p>
 * @param sql 预处理SQL语句
 * @param args 多参数
 * @return 对象的list结果集
 * @author wanglei 2017年12月16日
 */
public List<T> findListBySqlWithParams(String sql,Object[] args);
/**
 * <p>Description:根据单独参数查询<p>
 * @param sql 预处理SQL语句
 * @param arg 参数对象
 * @return
 * @author wanglei 2017年12月16日
 */
public List<T> findListBySqlWithParam(String sql,Object arg);
/**
 * <p>Description:根据sql语句查询没有条件<p>
 * @param sql 预处理SQL语句
 * @return 对象list结果集
 * @author wanglei 2017年12月16日
 */
public List<T> listBysql(String sql);
/**
 * <p>Description:根据多参数返回一个Object的list 结果集<p>
 * @param sql 预处理SQL语句
 * @param args 参数
 * @return  
 * @author wanglei 2017年12月16日
 */
public List<Object> queryListBySqlWithParams(String sql,Object[] args,Class<?> claz);
/**
 * <p>Description:根据一个参数返回结果集<p>
 * @param sql 预处理SQL语句
 * @param arg sql
 * @return
 * @author wanglei 2017年12月16日
 */
public List<Object>queryListBySqlWithParam(String sql,Object arg,Class<?> claz);
/**
 * <p>Description:根据SQL语句返回结果集合<p>
 * @param sql 预处理SQL语句
 * @return
 * @author wanglei 2017年12月16日
 */
public List<Object>queryListBySql(String sql,Class<?> claz);
/**
 * <p>Description:根据预处理语句和参数返回一个map结果集<p>
 * @param  sql 预处理SQL语句
 * @param params 参数
 * @return
 * @author wanglei 2017年12月23日
 */
public List<Map<String,Object>> excuteQuerySql(String sql,List<?> params);
/**
 * <p>Description:返回一个map类型结果集<p>
 * @param sql 预处理sql 
 * @param params 参数类型
 * @param isConvert 是否将map的键转换成驼峰命名
 * @return
 * @author wanglei 2017年12月18日
 */
public List<Map<String,Object>> excuteQuerySql(String sql,List<Object> params,boolean isConvert);
/**
 * <p>Description:预处理形式执行SQL语句<p>
 * @param sql SQL语句
 * @param prams 参数
 * @author wanglei 2017年12月24日
 */
public void excutePreSql(String sql ,List<Object> prams);
/**
 * <p>Description:执行SQL语句<p>
 * @param sql SQL语句
 * @author wanglei 2017年12月24日
 */
public void excuteSql(String sql);
/**
 * <p>Description:批处理执行预处理SQL<p>
 * @param sql 预处理SQL语句
 * @param batchprams 参数
 * @author wanglei 2017年12月24日
 */
public void bacthExcutePreSql(String sql,List<List<Object>> batchprams);
/**
 * <p>Description:批量执行SQL语句<p>
 * @param sqls SQL语句
 * @author wanglei 2017年12月24日
 */
public void bacthExceteSql(List<String> sqls);

}
