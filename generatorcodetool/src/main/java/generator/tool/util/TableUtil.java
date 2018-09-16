package generator.tool.util;


import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.TableBean;
import generator.tool.model.TableColumn;
import generator.tool.model.config.TableColumnNameConfigs;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TableUtil {
	/**
	 * 数据库工具类
	 */
	private DbHelper dh = null;
	/**
	 * 数据库表工具类
	 */
	private static TableUtil tableBeanUtil ;
	
	/**
	 * 表属性列表
	 */
	public static List<TableBean> tableBeans ;
	private TableUtil(){
		dh = DbHelper.getDbHelperInstance();
	}
	/**
	 * <p>Description:初始化tableutil<p>
	 * @return
	 * @author wanglei 2018年1月21日
	 */
	public static TableUtil getTableBeanUtilInstance() {
		if (null == tableBeanUtil) {
			synchronized (TableUtil.class) {
				// 该同步代码块是为了解决多线程状态下 获取单例对象为null的问题
				tableBeanUtil = new TableUtil();
				//获取所有的表属性列表
				try {
					tableBeans = tableBeanUtil.getTales();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return tableBeanUtil;
	}
	
	/**
	 * <p>Description:获取表属性<p>
	 * @return
	 * @throws SQLException
	 * @author wanglei 2018年1月21日
	 */
	private List<TableBean> getTales() throws SQLException {
		//数据库表列表
		List<TableBean> tableBeans = new ArrayList<TableBean>();
		//获取表配置
		DatabaseMetaData dbmd = dh.getConnection().getMetaData();
		//数据库
		String catalog = dh.getConnection().getCatalog();
		//获取表对象
		ResultSet rs = dbmd.getTables(catalog, "%","%",new String[]{"TABLE"}); 
		//表对象
		TableBean tbaleBean = null ;
		while(rs.next()) {
			tbaleBean = new TableBean();
			String columnName =null; 
			String columnType =null; 
			String currentTableName= rs.getString("TABLE_NAME");
			String remarkes = rs.getString("REMARKS");
			//赋值表名
			tbaleBean.setTableName(currentTableName);
			tbaleBean.setTableContent(remarkes);
			tbaleBean.setTableCatalog(catalog);
			
			ResultSet colRet = dbmd.getColumns(null,"%",currentTableName ,"%"); 
			//赋值表属性
			//获取主键
			ResultSet pkrs = dbmd.getPrimaryKeys(catalog, null, currentTableName);
			String pkName  = null;
			while(pkrs.next()){ 
				 pkName = pkrs.getString("COLUMN_NAME");
			}
			//设置主键
			tbaleBean.setTbalePk(pkName);
			List<TableColumn> tableColumns = new ArrayList<TableColumn>();
			while(colRet.next()){ 
				TableColumn tableColumn = new TableColumn();
			    columnName = colRet.getString("COLUMN_NAME"); 
			    tableColumn.setColumnName(columnName);
				columnType = colRet.getString("TYPE_NAME"); 
				tableColumn.setColumnType(columnType);
				int datasize = colRet.getInt("COLUMN_SIZE"); 
				tableColumn.setColumnSize(datasize);
				int digits = colRet.getInt("DECIMAL_DIGITS");
				tableColumn.setColumnDecimalDigits(digits);
				int nullable = colRet.getInt("NULLABLE"); 
				tableColumn.setColumnIsnull(nullable);
				String REMARKS  = colRet.getString("REMARKS"); 
				tableColumn.setColumnRmark(REMARKS);
				tableColumns.add(tableColumn);
			}
			tbaleBean.setTableColumn(tableColumns);
			tableBeans.add(tbaleBean);
	   }
		return tableBeans;
	} 
	/**
	 * <p>Description:根据表名获取表属性<p>
	 * @param tableName
	 * @return
	 * @author wanglei 2018年1月21日
	 */
	public TableBean getTableBeanByTableName(String tableName){
		TableBean tableBean = null;
		//遍历获取表的bean对象
		for(TableBean tempTableBean : tableBeans){
			if(tempTableBean.getTableName().equals(tableName)){
				tableBean = tempTableBean;
				break;
			}
		}
		return tableBean ;
	}
	/**
	 * <p>Description:根据配置获取实体类<p>
	 * @param temTableBean
	 * @return
	 * @author wanglei 2018年1月21日
	 */
	@SuppressWarnings("static-access")
	public List<TableBean> getTableBeanByTable(TableBean temTableBean){
		List<TableBean> tableBeans = new ArrayList<TableBean>();
		//遍历获取表的bean对象
		if(temTableBean!=null){
			String tableName =temTableBean.getTableName();
			if(StringUtils.isNotBlank(tableName)){
				//table对象
				TableBean temtableBean = tableBeanUtil.getTableBeanByTableName(tableName);
				tableBeans.add(temtableBean);
			}else{
				tableBeans =tableBeanUtil.tableBeans;
			}
		}else{
			tableBeans =tableBeanUtil.tableBeans;
		}
		return tableBeans ;
	}

	/**
	 * 检查默认生成列初始化是否为空
	 * @param tableColumnNameConfigs
	 * @return
	 */
	public static boolean  chenInintColumns(List<TableColumnNameConfigs> tableColumnNameConfigs){
		if(null==tableColumnNameConfigs||tableColumnNameConfigs.isEmpty())return false;
		for(TableColumnNameConfigs tableColumnNameConfig : tableColumnNameConfigs){
			if(StringUtils.isNotBlank(tableColumnNameConfig.getTableColumnName())&&!"null".equals(tableColumnNameConfig.getTableColumnName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据表名获取公共属性
	 * @param tableName 表名
	 * @return
	 */
	public static ProjectCodePropertiesModel  setProjectCodePropertiesByTableName(String tableName,String codeMapName,String  mapKey,Object proerties){
		//公共属性
		ProjectCodePropertiesModel resModel = null;
		Map<String, ProjectCodePropertiesModel> projectCodePropertiesModel = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
	    //	遍历列表获取到对用表格属性
		Set<String> keys = projectCodePropertiesModel.keySet();
		for(String key :keys){
			if(key.equals(tableName)){
				resModel = projectCodePropertiesModel.get(key);
				break;
			}
		}
        //如果属性为空则需要新增一个
        if(resModel==null){
			resModel = new ProjectCodePropertiesModel();

		}
		Map<String, Object> propertiesMap = (Map<String, Object>)ReflectUtils.getBeanProperties(resModel,codeMapName);
		Map<String, Object> values = new HashMap<>();
		values.put(codeMapName, getProjectPropertiesMap(mapKey,proerties,propertiesMap));
		resModel = (ProjectCodePropertiesModel)ReflectUtils.setBeanProperty(values,resModel);
		//放入缓存
		projectCodePropertiesModel.put(tableName, resModel);
		SystemContext.set(CommonConstants.PROJECT_CODE_PROPERTIES, projectCodePropertiesModel);
		return resModel;
	}

	/**
	 * 根据键值获取属性，如果为空则null则要新增
	 * @param key 键值
	 * @param proerties 属性值
	 * @param destMap 目标集合
	 * @return
	 */
	public static Map<String, Object> getProjectPropertiesMap(String  key,Object proerties, Map<String, Object> destMap){
		//如果不为空则做判断赋值
		if(null!=destMap&&!destMap.isEmpty()){
			//如果属性为空则需要新增属性
			if (null==destMap.get(key)){
				destMap.put(key, proerties);
			}
		}else{
			destMap = new HashMap<>();
			destMap.put(key, proerties);
		}
		return destMap;
	}
}
