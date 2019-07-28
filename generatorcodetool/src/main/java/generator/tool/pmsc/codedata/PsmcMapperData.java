package generator.tool.pmsc.codedata;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.*;
import generator.tool.pmsc.mode.PsmcMapperCodeDataModel;
import generator.tool.util.ColumnToPropertyUtil;
import generator.tool.util.TableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/10/13
 */
@SuppressWarnings("unchecked")
public class PsmcMapperData implements AbstractCodeData<List<PsmcMapperCodeDataModel>> {
    private  List<PsmcMapperCodeDataModel> mybatisMapperConfigs  = new ArrayList<>();
    /**
     * Default constructor
     */
    public PsmcMapperData() {
    }

    @Override
    public List<PsmcMapperCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        Map<String, Object> projectCodePropertiesModelMap = SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES,Map.class);
        if(tableBeans.size()>0){
            for(TableBean table :tableBeans) {
                String tableName = table.getTableName();
                if(null!=projectCodePropertiesModelMap&&!projectCodePropertiesModelMap.isEmpty()){
                    String beanName = projectCodePropertiesModelMap.get(tableName+CommonConstants.beanName).toString();
                    PsmcMapperCodeDataModel mybatisMapperConfig =
                            new PsmcMapperCodeDataModel(projectCodePropertiesModelMap,table,beanName);
                    mybatisMapperConfigs.add(mybatisMapperConfig);
                    inIntMapperCommon2Cache(table.getTbalePk(), tableName, mybatisMapperConfig);
                }
            }
        }
        return mybatisMapperConfigs;
    }



    /**
     * 将实体类映射公共信息方到缓存里
     * @param tablePk 表格主键
     * @param tableName 表名
     * @param mybatisMapperConfig 映射文件配置信息
     */
    private void inIntMapperCommon2Cache(String tablePk, String tableName,
                                         PsmcMapperCodeDataModel mybatisMapperConfig) {
        // 设置表主键
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.tablePkBenanName,ColumnToPropertyUtil.camelName(tablePk));
        // 设置删除语句ID
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.daoDeletePrimarySqlID,mybatisMapperConfig.getDeleteByPrimaryKeySqlID());
        // 设置更新语句ID
        TableUtil.setProjectCodePropertiesByTableName(tableName+ CommonConstants.daoUpdateSqlID,
                mybatisMapperConfig.getUpdateSqlID());
        // 设置主键查询语句ID
        TableUtil.setProjectCodePropertiesByTableName(tableName+ CommonConstants.daoSelecPrimarySqlID,
                mybatisMapperConfig.getSelectByPrimaryKeySqlID());
        // 设置查询列表语句ID
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.daoSelecListSqlID,mybatisMapperConfig.getSelectListSqlID());
        // 设置插入语句ID
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.daoInsertSqlID,mybatisMapperConfig.getInsertSqlID());
    }
}
