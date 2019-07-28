package generator.tool.pmsc.codedata;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.DomainCodeDataModel;
import generator.tool.model.TableBean;
import generator.tool.model.TableColumn;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.TableColumnNameConfigs;
import generator.tool.model.config.TableConfig;
import generator.tool.util.ColumnToPropertyUtil;
import generator.tool.util.TableUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mybatis实体类数据
 */
public class PsmcDomainData implements AbstractCodeData<List<DomainCodeDataModel>> {
    private List<DomainCodeDataModel> beanModels = new ArrayList<>();

    /**
     * Default constructor
     */
    public PsmcDomainData() {
    }

    @Override
    public List<DomainCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        List<TableConfig> tableConfigs = codeFileCfg.getTables();
        if (tableBeans.size() > 0) {
            for (TableBean table : tableBeans) {
                // 查找表配置信息
                Optional<TableConfig> tabCfgOpt = tableConfigs.stream().filter(tcf->tcf.getTableName().equals(table.getTableName())).findFirst();
                TableConfig tableConfig = tabCfgOpt.orElse(null);
                if(null == tableConfig){
                    continue;
                }
                // 获取配置的实体类名
                String domainCodeFileName = tableConfig.getDomainCodeFileName();
                // 获取表的列信息
                List<TableColumn> tableColumns = inIntTableCoulms(tableConfig,table);
                String beanName = StringUtils.isBlank(domainCodeFileName) ? ColumnToPropertyUtil.
                        getBeanNameByTableName(table.getTableName()) : domainCodeFileName;
                // 初始化实体类代码信息
                DomainCodeDataModel beanModel = new DomainCodeDataModel(codeFileCfg, table, tableColumns, beanName);
                beanModels.add(beanModel);
                setModelCommon2Cache(table, beanName, beanModel);
            }
        }
        return beanModels;
    }

    /**
     * 将实体公共属性添加到缓存
     * @param table 表格实体
     * @param beanName 实体名称
     * @param beanModel 实体类代码信息
     */
    private void setModelCommon2Cache(TableBean table, String beanName, DomainCodeDataModel beanModel) {
        // 设置实体类名称
        TableUtil.setProjectCodePropertiesByTableName(table.getTableName()+CommonConstants.beanName, beanName);
        // 设置实体类类包名称 包+实体类
        TableUtil.setProjectCodePropertiesByTableName(table.getTableName()+
                CommonConstants.beanNamePackageStr, beanModel.getPackageNameStr() + "." + beanName);
        // 设置实体类属性信息
        TableUtil.setProjectCodePropertiesByTableName(table.getTableName()+CommonConstants.beanProperties, beanModel.getColumns());
    }

    /**
     * 初始化要生成表格的列
     * @param tableConfig 表格配置信息
     * @param table 数据库表信息
     * @return 要生成的列对应实体类的属性值
     */
    private List<TableColumn> inIntTableCoulms(TableConfig tableConfig,TableBean table) {
        //如果指定表格名为空则默认生成全部的属性
        List<TableColumnNameConfigs> inittableColumns = tableConfig.getTbaleColumns();
        //表格属性
        List<TableColumn> tableColumns;
        if (!TableUtil.chenInintColumns(inittableColumns)) {
            tableColumns = table.getTableColumn();
        } else {
            //生成指定属性列
            tableColumns = new ArrayList<>();
            List<TableColumn> temptableColumns = table.getTableColumn();
            for (TableColumn tableColumn : temptableColumns) {
                for (TableColumnNameConfigs inittableColumn : inittableColumns) {
                    if (tableColumn.getColumnName().equals(inittableColumn.getTableColumnName())) {
                        tableColumns.add(tableColumn);
                    }
                }
            }
        }
        return tableColumns;
    }
}