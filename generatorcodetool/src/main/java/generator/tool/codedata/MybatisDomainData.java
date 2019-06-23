package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.DomainCodeDataModel;
import generator.tool.model.codedata.DomainPropertiesModel;
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
public class MybatisDomainData implements AbstractCodeData<List<DomainCodeDataModel>> {
   private  List<DomainCodeDataModel> beanModels  = new ArrayList<>();
    /**
     * Default constructor
     */
    public MybatisDomainData() {
    }
    @Override
    public List<DomainCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        List<TableConfig> tableConfigs = codeFileCfg.getTables();
        if(tableBeans.size()>0){
            for(TableBean table :tableBeans) {
                //如果指定表格名为空则默认生成全部的属性
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
                String beanName = StringUtils.isBlank(domainCodeFileName)?ColumnToPropertyUtil.
                        getBeanNameByTableName(table.getTableName()):domainCodeFileName;
                DomainCodeDataModel beanModel = new DomainCodeDataModel(codeFileCfg,table,tableColumns,beanName);
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),
                        CommonConstants.modelBeanPorperties,CommonConstants.beanName,beanName);
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),
                        CommonConstants.modelBeanPorperties,
                        CommonConstants.beanNamePackageStr,beanModel.getPackageNameStr()+"."+beanName);
                TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),
                        CommonConstants.modelBeanPorperties,CommonConstants.beanProperties,beanModel.getColumns());
                beanModels.add(beanModel);
            }
         }
        return beanModels;e
    }

}