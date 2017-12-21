package com.wanglei.baseservlet.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wanglei.baseservlet.dao.BaseDao;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.SystemContext;
import com.wanglei.baseservlet.utils.DbHelper;


public class BaseDaoImpl<T> implements BaseDao<T> {
	private DbHelper dh = DbHelper.getDbHelperInstance();
	/**
	 * 根据模板T获取class
	 */
	private Class<T> clz;
	@SuppressWarnings("unchecked")
	public Class<T> getClz(){
		if(clz==null) {
			synchronized (DbHelper.class) {
				//该同步代码块是为了解决多线程状态下 获取单例对象为null的问题
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
		String endstr=sql.substring(sql.indexOf("from"));
		String c = "select count(*) as total" +endstr;
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
	@SuppressWarnings("unchecked")
	public Pager<T> findPagerBySql(String sql,Pager<T> pager ){
		sql = inintSort(sql);
		pager.setDatas((List<T>) dh.excuteQueryListBySqlWithParams(sql, this.getPagerProgramsByPager(pager), this.getClz()));
		long total = Long.parseLong(this.excuteQuerySql(getCountSql(sql,true),null,false).get(0).get("total").toString());
		pager.setTotal(total);
		return pager;
	}
	/**
	 * <p>Description:根据分页对象获取参数信息<p>
	 * @param pager
	 * @return
	 * @author wanglei 2017年12月19日
	 */
	private List<Object > getPagerProgramsByPager(Pager<T> pager){
		List<Object > prams = new ArrayList<>();
		Map<String,String > pram = pager.getParams();
		if(null!=pram && pram.size()>0){
			Set<String> keys = pram.keySet();
			for(String key: keys){
				prams.add(pram.get(key));
			}
		}
		prams.add(pager.getOffset());
		prams.add(pager.getSize());
		return prams;
	}
	/**
	 * <p>Description:查询list结果<p>
	 * @param sql SQL语句
	 * @param args 多参数
	 * @return 对象的list结果集
	 * @author wanglei 2017年12月16日
	 */
	@SuppressWarnings("unchecked")
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
	public List<Object>queryListBySql(String sql,Class<?> claz){
		List<Object> results = new ArrayList<Object>();
		sql = inintSort(sql);
		results = this.queryListBySqlWithParams(sql, null, claz);
		return results;
	}

	/**
	 * <p>Description:返回一个map类型结果集<p>
	 * @param sql sql 
	 * @param params 参数类型
	 * @param isConvert 是否将map的键转换成驼峰命名
	 * @return
	 * @author wanglei 2017年12月18日
	 */
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
}
