package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.BeanModel;
import generator.tool.model.BeanProperties;
import generator.tool.model.TableBean;
import generator.tool.model.TableColumn;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.DomainCodeConfig;
import generator.tool.model.config.TableColumnNameConfigs;
import generator.tool.model.config.TableConfig;
import generator.tool.util.ColumnToPropertyUtil;
import generator.tool.util.TableUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis实体类数据
 */
public class MybatisDomainData extends AbstractCodeData {
   private  List<BeanModel> beanModels  = new ArrayList<>();
    /**
     * Default constructor
     */
    public MybatisDomainData() {
    }
    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        DomainCodeConfig domainCfg = codeFileCfg.getCommonConfig().getDaominCode();
        String packageNameStr = domainCfg.getPakageName();
        List<TableConfig> tableConfigs = codeFileCfg.getTables();
        if(tableBeans.size()>0){
            for(TableBean table :tableBeans) {
                //如果指定表格名为空则默认生成全部的属性
                BeanModel beanModel = new BeanModel ();
                List<TableColumnNameConfigs> inittableColumns = null;
                String domainCodeFileName =null;
                //找到要指定要生成列的数据
                for(TableConfig taleCof :tableConfigs) {
                    if(taleCof.getTableName().equals(table.getTableName())){
                        inittableColumns = taleCof.getTbaleColumns();
                        domainCodeFileName = taleCof.getDomainCodeFileName();
                        break;
                    }
                }
                //表格属性
                List<TableColumn> tableColumns;
                if(!TableUtil.chenInintColumns(inittableColumns)){
                    tableColumns = table.getTableColumn();
                }else{
                    //生成指定属性列
                    tableColumns = new ArrayList<>();
                    List<TableColumn> temptableColumns = table.getTableColumn();
                    for(TableColumn tableColumn :temptableColumns){
                        for(TableColumnNameConfigs inittableColumn : inittableColumns){
                            if(tableColumn.getColumnName().equals(inittableColumn.getTableColumnName())){
                                tableColumns.add(tableColumn);
                            }
                        }
                    }
                }
                String beanName = StringUtils.isBlank(domainCodeFileName)?ColumnToPropertyUtil.getBeanNameByTableName(table.getTableName()):domainCodeFileName;
                beanModel.setBeanName(beanName);
                beanModel.setPackageNameStr(packageNameStr);
                List<BeanProperties> beanProperties = intJavaProperByTableColumnName(tableColumns);
                beanModel.setColumns(beanProperties);
                beanModel.setBeanContent(table.getTableContent());
                beanModel.setImportStrs(ColumnToPropertyUtil.getImportStrByBeanProperType(beanProperties));
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.modelBeanPorperties,CommonConstants.beanName,beanName);
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.modelBeanPorperties,CommonConstants.beanNamePackageStr,packageNameStr+"."+beanName);
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.modelBeanPorperties,CommonConstants.beanProperties,beanProperties);
                beanModels.add(beanModel);
            }
         }
        return beanModels;
    }
    /**
     * <p>Description:根据列名初始化javabean的实体属性<p>
     * @param tableColumns 表属性列
     * @return 实体类属性列表
     * @author wanglei 2018年1月21日
     */
    private static List<BeanProperties> intJavaProperByTableColumnName(List<TableColumn> tableColumns){
        List<BeanProperties> beanPropertieses = new ArrayList<>();
        if(null!=tableColumns && tableColumns.size()>0){
            for(TableColumn tableColumn: tableColumns){
                BeanProperties beanProperties = new BeanProperties();
                String columnName = ColumnToPropertyUtil.camelName(tableColumn.getColumnName());
                beanProperties.setPropertName(ColumnToPropertyUtil.camelName(columnName));
                beanProperties.setPropertComment(tableColumn.getColumnRmark());
                beanProperties.setPropertType(ColumnToPropertyUtil.getBenaPropertiesTypeByTableColumn(tableColumn.getColumnType()));
                beanProperties.setPropertNameUpCase(StringUtils.capitalize(columnName));
                beanProperties.setJdbcType(tableColumn.getColumnType());
                beanPropertieses.add(beanProperties);
            }
        }
        return beanPropertieses;
    }
}