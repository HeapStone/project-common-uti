package com.wanglei.baseservlet.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wanglei.baseservlet.dao.BaseDao;
import com.wanglei.baseservlet.exception.BusinessException;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.SystemContext;
import com.wanglei.baseservlet.utils.BuildSqlUtil;
import com.wanglei.baseservlet.utils.ColumnToPropertyUtil;
import com.wanglei.baseservlet.utils.DbHelper;


public class BaseDaoImpl<T> implements BaseDao<T> {
	private DbHelper dh = DbHelper.getDbHelperInstance();
	/**
	 * 根据模板T获取class
	 */
	private Class<T> clz;
	@SuppressWarnings("unchecked")
	private Class<T> getClz(){
		//解决并发问题
		synchronized (BaseDaoImpl.class) {
		if(clz==null) {
			//获取泛型的Class对象
		clz = ((Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		}
		return clz;
	}
	/**
	 * <p>Description: 初始化排序方法<p>
	 * @param hql hql语句
	 * @return 添加好排序的hql
	 * @author WangLei 2017年12月9日
	 */
	private String inintSort(String sql){
		//设置排序
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(null != sort && !"".equals(sort.trim())){
			order = ColumnToPropertyUtil.underscoreName(order);
			sql+=" order by "+order;
		if( !"desc".equals(sort)) sql+=" asc";
		else sql += " desc";
		}
		
		return sql;
	}
	/**
	 * <p>Description:根据SQL语句获取总的记录数<p>
	 * @param sql sql语句
	 * @param isFetch 是否抓取
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	private String getCountSql(String sql,boolean isFetch){
		String endstr=sql.substring(StringUtils.indexOfIgnoreCase(sql, "from"));
		endstr=endstr.replace(" LIMIT ? , ? ", "");
		String c = "select count(*) as total " +endstr;
		if(isFetch){ //是否抓取
			c.replaceAll("fetch", "");
		}
		return c;
	}
	/**
	 * <p>Description:分页查询的方法<p>
	 * @param sql sql语句
	 * @param pager 分页对象
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pager<T> findPager(Pager<T> pager){
		Map<String ,Object> slqAndprams = BuildSqlUtil.getPagerProgramsAndSqlByPager(pager,this.getClz());
		if(null!=slqAndprams && slqAndprams.size()>0){
			String sql;
			Object  objp= slqAndprams.get("prams");
			Object npObj = slqAndprams.get("noPagePrams");
			List<Object> params = null; 
			List<Object> noPagePrams = null;
			if(null != objp){
				params=(List<Object>)objp;
			}
			if(null != npObj){
				noPagePrams=(List<Object>)npObj;
			}
			if( null==slqAndprams.get("sql")) throw new BusinessException("获取sql语句异常!");
			sql = slqAndprams.get("sql").toString();
			if( StringUtils.isBlank(sql)) throw new BusinessException("获取sql语句异常!");
			sql = inintSort(sql);
			pager.setDatas((List<T>) dh.excuteQueryListBySqlWithParams(sql, params, this.getClz()));
			long total = Long.parseLong(this.excuteQuerySql(getCountSql(sql,true),noPagePrams).get(0).get("total").toString());
			pager.setTotal(total);
		}else{
			throw new BusinessException("获取sql语句异常!");
		}
		return pager;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Object> findPagerByPreSqlWithParams(String sql, Pager<Object> pager,Class<?> clazz) {
		Map<String ,Object> slqAndprams = BuildSqlUtil.getPagerProgramsByPagerAndSql(pager,sql);
		if(null!=slqAndprams && slqAndprams.size()>0){
			Object  objp= slqAndprams.get("prams");
			Object npObj = slqAndprams.get("noPagePrams");
			List<Object> params = null; 
			List<Object> noPagePrams = null;
			if(null != objp){
				params=(List<Object>)objp;
			}
			if(null != npObj){
				noPagePrams=(List<Object>)npObj;
			}
			if( null==slqAndprams.get("sql")) throw new BusinessException("获取sql语句异常!");
			sql = slqAndprams.get("sql").toString();
			if( StringUtils.isBlank(sql)) throw new BusinessException("获取sql语句异常!");
			sql = inintSort(sql);
			pager.setDatas(dh.excuteQueryListBySqlWithParams(sql, params, this.getClz()));
			long total = Long.parseLong(this.excuteQuerySql(getCountSql(sql,true),noPagePrams).get(0).get("total").toString());
			pager.setTotal(total);
		}else{
			throw new BusinessException("获取sql语句异常!");
		}
		return pager;
	}
	/**
	 * <p>Description:查询list结果<p>
	 * @param sql SQL语句
	 * @param args 多参数
	 * @return 对象的list结果集
	 * @author wanglei 2017年12月16日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListBySqlWithParams(String sql,Object[] args){
		List<T> results = new ArrayList<T>();
		sql = inintSort(sql);
		//查询结果
		if(null!=args && args.length>1){
			List<Object> params = new ArrayList<>();
			for(Object obj : args){
				params.add(obj);
			}
			results = (List<T>) dh.excuteQueryListBySqlWithParams(sql, params, this.getClz());
		}else{
			results = (List<T>) dh.excuteQueryListBySql(sql, this.getClz());
		}
		return results;
		
	}
	/**
	 * <p>Description:根据单独参数查询<p>
	 * @param sql sql语句
	 * @param arg 参数对象
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	@Override
	public List<T> findListBySqlWithParam(String sql,Object arg){
		List<T> results = new ArrayList<T>();
		sql = inintSort(sql);
		results = this.findListBySqlWithParams(sql, new Object []{arg});
		return results;
	}
	/**
	 * <p>Description:根据sql语句查询没有条件<p>
	 * @param sql SQL语句
	 * @return 对象list结果集
	 * @author wanglei 2017年12月16日
	 */
	@Override
	public List<T> listBysql(String sql){
		List<T> results = new ArrayList<T>();
		sql = inintSort(sql);
		results =  this.findListBySqlWithParams(sql, null);
		return results;
	}
	/**
	 * <p>Description:根据多参数返回一个Object的list 结果集<p>
	 * @param sql sql
	 * @param args 参数
	 * @return  
	 * @author wanglei 2017年12月16日
	 */
	@Override
	public List<Object> queryListBySqlWithParams(String sql,Object[] args,Class<?> claz){
		List<Object> results = new ArrayList<Object>();
		sql = inintSort(sql);
		//查询结果
				if(null!=args && args.length>1){
					List<Object> params = new ArrayList<>();
					for(Object obj : args){
						params.add(obj);
					}
					results = dh.excuteQueryListBySqlWithParams(sql, params,claz );
				}else{
					results = dh.excuteQueryListBySql(sql, claz);
				}
		return results;
	}
	/**
	 * <p>Description:根据一个参数返回结果集<p>
	 * @param sql SQL语句
	 * @param arg sql
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	@Override
	public List<Object>queryListBySqlWithParam(String sql,Object arg,Class<?> claz){
		List<Object> results = new ArrayList<Object>();
		sql = inintSort(sql);
		results = this.queryListBySqlWithParams(sql, new Object []{arg}, claz);
		return results;
	}
	/**
	 * <p>Description:根据SQL语句返回结果集合<p>
	 * @param sql SQL语句
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	@Override
	public List<Object>queryListBySql(String sql,Class<?> claz){
		List<Object> results = new ArrayList<Object>();
		sql = inintSort(sql);
		results = this.queryListBySqlWithParams(sql, null, claz);
		return results;
	}
	/**
	 * <p>Description:根据预处理语句和参数返回一个map结果集<p>
	 * @param sql SQL语句
	 * @param params 参数
	 * @return
	 * @author wanglei 2017年12月23日
	 */
	@Override
	public List<Map<String,Object>> excuteQuerySql(String sql,List<?> params){
		return dh.excuteQuerySql(sql, params);
	}
	/**
	 * <p>Description:返回一个map类型结果集<p>
	 * @param sql sql 
	 * @param params 参数类型
	 * @param isConvert 是否将map的键转换成驼峰命名
	 * @return
	 * @author wanglei 2017年12月18日
	 */
	@Override
	public List<Map<String,Object>> excuteQuerySql(String sql,List<Object> params,boolean isConvert){
		return dh.excuteQuerySql(sql, params,isConvert);
	}
	@Override
	public void add(T t) {
		dh.add(t);
	}
	@Override
	public void update(T t, String pk) {
     dh.update(t, pk);
	}
	@Override
	public void delete(T t ,String pk) {
		dh.delete(t, pk);		
	}
	@Override
	public T findByUuid(String sql, String id) {
		List<T> results = new ArrayList<T>();
		results =findListBySqlWithParam(sql, id);
		return results.isEmpty()?null:results.get(0);
	}
	@Override
	public T findByUuid(String sql, Integer id) {
		return findByUuid(sql, null!=id ?String.valueOf(id):"");
	}
	@SuppressWarnings("unchecked")
	@Override
	public T load(T t,String pk) {
		return (T)dh.load(t, pk);
	}
	/**
	 * <p>Description:根据sql和参数添加<p>
	 * @param sql SQL语句
	 * @param args 参数
	 * @author wanglei 2017年12月23日
	 */
	@Override
	public void add(String sql ,List<Object> args){
		dh.excuteSqlByPrepareStatement(sql, args);
	}
	/**
	 * <p>Description:根据SQL和添加删除<p>
	 * @param sql 
	 * @param args
	 * @author wanglei 2017年12月23日
	 */
	@Override
	public void delete (String sql ,List<Object> args){
		dh.excuteSqlByPrepareStatement(sql, args);
		
	}
	/**
	 * <p>Description:根据SQL语句更新<p>
	 * @param sql sql语句
	 * @param args 参数
	 * @author wanglei 2017年12月23日
	 */
	@Override
	public void update (String sql, List<Object> args){
		dh.excuteSqlByPrepareStatement(sql, args);
	}
	public void excuteSql(){
		
	}
	@Override
	public void excutePreSql(String sql, List<Object> prams) {
		dh.excuteSqlByPrepareStatement(sql, prams);
		
	}
	@Override
	public void excuteSql(String sql) {
		dh.excuteSqlByStatement(sql);
	}
	@Override
	public void batchAdd(List<T> objs) {
		dh.batchAdd(objs);
		
	}
	@Override
	public void batchDelete(List<T> objs, String pk) {
		dh.batchDelete(objs,pk);
		
	}
	@Override
	public void batchUpdate(List<T> objs, String pk) {
		dh.batchUpdate(objs, pk);
		
	}
	@Override
	public void bacthExcutePreSql(String sql, List<List<Object>> batchprams) {
		dh.bacthExcutePreSql(sql, batchprams);
	}
	@Override
	public void bacthExceteSql(List<String> sqls) {
		dh.batchExcuteSqlByStatement(sqls);
	}
	
}
