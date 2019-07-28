package generator.tool.util;


import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.TableBean;
import generator.tool.model.TableColumn;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.TableColumnNameConfigs;
import generator.tool.model.config.TableConfig;
import org.apache.commons.lang.StringUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static generator.tool.factory.SystemContext.set;

@SuppressWarnings("unchecked")
public class TableUtil {
    /**
     * 数据库工具类
     */
    private DbHelper dh = null;
    /**
     * 数据库表工具类
     */
    private static TableUtil tableBeanUtil;

    /**
     * 表属性列表
     */
    public static List<TableBean> tableBeans;

    private TableUtil() {
        dh = DbHelper.getDbHelperInstance();
    }

    /**
     * <p>Description:初始化tableutil<p>
     *
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
                    List<TableConfig> tableConfigs = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class).getTables();
                    // 如果没有配置表则获取数据库全部表
                    tableBeans = tableBeanUtil.getTales(tableConfigs);
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
     *
     * @return
     * @throws SQLException
     * @author wanglei 2018年1月21日
     */
    private List<TableBean> getTales(List<TableConfig> tableConfigs) throws SQLException {
        List<String> tableNames = tableConfigs.parallelStream().map(TableConfig::getTableName).collect(Collectors.toList());
        //数据库表列表
         tableBeans = new ArrayList<TableBean>();
        //获取表配置
        DatabaseMetaData dbmd = dh.getConnection().getMetaData();
        //数据库
        String catalog = dh.getConnection().getCatalog();
        //获取表对象
        ResultSet rs = null;
         if(null== tableNames ||tableNames.isEmpty()){
             rs =  dbmd.getTables(catalog, "%", "%", new String[]{"TABLE"});
             getTableBean(dbmd,catalog,rs);
        }else{
             for (String tableName : tableNames) {
                 rs =  dbmd.getTables(catalog, "%", tableName, new String[]{"TABLE"});
                 getTableBean(dbmd,catalog,rs);
             }
         }
        return tableBeans;
    }

    /**
     * 获取table信息
     * @param dbmd
     * @param catalog
     * @param rs
     * @throws SQLException
     */
    private void getTableBean(DatabaseMetaData dbmd, String catalog, ResultSet rs) throws SQLException {
        TableBean tbaleBean;
        while (rs.next()) {
            tbaleBean = new TableBean();
            String columnName = null;
            String columnType = null;
            String currentTableName = rs.getString("TABLE_NAME");
            String remarkes = rs.getString("REMARKS");
            //赋值表名
            tbaleBean.setTableName(currentTableName);
            tbaleBean.setTableContent(remarkes);
            tbaleBean.setTableCatalog(catalog);
            ResultSet colRet = dbmd.getColumns(null, "%", currentTableName, "%");
            //赋值表属性
            //获取主键
            ResultSet pkrs = dbmd.getPrimaryKeys(catalog, null, currentTableName);
            String pkName = null;
            while (pkrs.next()) {
                pkName = pkrs.getString("COLUMN_NAME");
            }
            //设置主键
            tbaleBean.setTbalePk(pkName);
            List<TableColumn> tableColumns = new ArrayList<TableColumn>();
            while (colRet.next()) {
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
                String REMARKS = colRet.getString("REMARKS");
                tableColumn.setColumnRmark(REMARKS);
                tableColumns.add(tableColumn);
            }
            tbaleBean.setTableColumn(tableColumns);
            tableBeans.add(tbaleBean);
        }
    }

    /**
     * <p>Description:根据表名获取表属性<p>
     *
     * @param tableName
     * @return
     * @author wanglei 2018年1月21日
     */
    public TableBean getTableBeanByTableName(String tableName) {
        TableBean tableBean = null;
        //遍历获取表的bean对象
        for (TableBean tempTableBean : tableBeans) {
            if (tempTableBean.getTableName().equals(tableName)) {
                tableBean = tempTableBean;
                break;
            }
        }
        return tableBean;
    }

    /**
     * <p>Description:根据配置获取实体类<p>
     *
     * @param temTableBean
     * @return
     * @author wanglei 2018年1月21日
     */
    @SuppressWarnings("static-access")
    public List<TableBean> getTableBeanByTable(TableBean temTableBean) {
        List<TableBean> tableBeans = new ArrayList<TableBean>();
        //遍历获取表的bean对象
        if (temTableBean != null) {
            String tableName = temTableBean.getTableName();
            if (StringUtils.isNotBlank(tableName)) {
                //table对象
                TableBean temtableBean = tableBeanUtil.getTableBeanByTableName(tableName);
                tableBeans.add(temtableBean);
            } else {
                tableBeans = tableBeanUtil.tableBeans;
            }
        } else {
            tableBeans = tableBeanUtil.tableBeans;
        }
        return tableBeans;
    }

    /**
     * 检查默认生成列初始化是否为空
     *
     * @param tableColumnNameConfigs
     * @return
     */
    public static boolean chenInintColumns(List<TableColumnNameConfigs> tableColumnNameConfigs) {
        if (null == tableColumnNameConfigs || tableColumnNameConfigs.isEmpty()) return false;
        for (TableColumnNameConfigs tableColumnNameConfig : tableColumnNameConfigs) {
            if (StringUtils.isNotBlank(tableColumnNameConfig.getTableColumnName()) && !"null".equals(tableColumnNameConfig.getTableColumnName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据表名获取公共属性
     *
     * @param proerties 表名
     * @return
     */
    public static void setProjectCodePropertiesByTableName(String key, Object proerties) {
        //公共属性
        Map<String, Object> projectCodePropertiesModel = SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES, Map.class);
        if (null == projectCodePropertiesModel) {
            projectCodePropertiesModel = new HashMap<>();
        }
        projectCodePropertiesModel.put(key, proerties);
        set(CommonConstants.PROJECT_CODE_PROPERTIES, projectCodePropertiesModel);
    }
}
