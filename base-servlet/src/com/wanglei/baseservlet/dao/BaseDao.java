package com.wanglei.baseservlet.dao;

import java.util.List;

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
public void add(String sql , List<Object> args);
/**
 * <p>Description:更新对象<p>
 * @param sql SQL语句 
 * @param args 更新的参数
 * @author wanglei 2017年12月16日
 */
public void update(String sql , List<Object> args);
/**
 * <p>Description:删除对象<p>
 * @param sql SQL语句
 * @param uuid
 * @author wanglei 2017年12月16日
 */
public void delete (String sql ,List<Object> args);


/**
 * <p>Description:根根据主键查询<p>
 * @param sql sql语句
 * @param id 主建值
 * @return 单个对象
 * @author wanglei 2017年12月16日
 */
public T findByUuid(String sql ,String id) ;
/**
 * <p>Description:根据主键返回对象<p>
 * @param sql SQL语句
 * @param id 主键
 * @return 对象
 * @author wanglei 2017年12月16日
 */
public T findByUuid(String sql ,Integer id);

}
