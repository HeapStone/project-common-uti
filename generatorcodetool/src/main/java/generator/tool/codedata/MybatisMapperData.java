package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.*;
import generator.tool.model.codedata.DomainPropertiesModel;
import generator.tool.model.codedata.MybatisMapperCodeDataModel;
import generator.tool.model.config.CfgProperty;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.MapperFileConfig;
import generator.tool.util.ColumnToPropertyUtil;
import generator.tool.util.TableUtil;
import org.apache.commons.lang.StringUtils;

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
public class MybatisMapperData implements AbstractCodeData <List<MybatisMapperCodeDataModel>>{
    private  List<MybatisMapperCodeDataModel> mybatisMapperConfigs  = new ArrayList<>();
    /**
     * Default constructor
     */
    public MybatisMapperData() {
    }

    @Override
    public List<MybatisMapperCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        MapperFileConfig mapperFile= codeFileCfg.getCommonConfig().getMapperFile();
        if(tableBeans.size()>0){
            for(TableBean table :tableBeans) {
                MybatisMapperCodeDataModel mybatisMapperConfig = new MybatisMapperCodeDataModel();
                String tableName = table.getTableName();
                ProjectCodePropertiesModel resModel =  projectCodePropertiesModelMap.get(tableName);
                Map<String,Object>  benMap =   resModel.getModelBeanPorperties();
                if(null!=benMap&&!benMap.isEmpty()){
                    String beanName = benMap.get(CommonConstants.beanName).toString();
                    String fileName = StringUtils.isEmpty(mapperFile.getFileName())?beanName:mapperFile.getFileName();
                    String beanNamePackageStr = benMap.get(CommonConstants.beanNamePackageStr).toString();
                    List<DomainPropertiesModel> beanProperties  = (List<DomainPropertiesModel>)benMap.get(CommonConstants.beanProperties);
                    List<CfgProperty> cfgPropertys = new ArrayList<>();
                    StringBuffer sbf = new StringBuffer();
                    for(DomainPropertiesModel bean:beanProperties){
                        String columnName = ColumnToPropertyUtil.underscoreName(bean.getPropertNameUpCase());
                        CfgProperty cfgProperty = new CfgProperty();
                        cfgProperty.setJdbcType(bean.getJdbcType());
                        cfgProperty.setColumnName(columnName);
                        cfgProperty.setPropertyName(bean.getPropertName());
                        sbf.append(columnName+ ",");
                        cfgPropertys.add(cfgProperty);
                    }
                    String baseColumn = sbf.toString();
                    baseColumn=baseColumn.substring(0, baseColumn.lastIndexOf(","));
                    mybatisMapperConfig.setCfgTableName(table.getTableName());
                    mybatisMapperConfig.setCfgClassName(beanNamePackageStr);
                    mybatisMapperConfig.setCfgTablePk(table.getTbalePk());
                    mybatisMapperConfig.setFileName(fileName);
                    mybatisMapperConfig.setCfgPropertys(cfgPropertys);
                    mybatisMapperConfig.setBeanPkColmn(ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    mybatisMapperConfig.setBaseColumn(baseColumn);
                    mybatisMapperConfig.setUpdateSqlID(CommonConstants.UPDATE_SQL+beanName);
                    mybatisMapperConfig.setInsertSqlID(CommonConstants.INSER_SQL+beanName);
                    mybatisMapperConfig.setDeleteByPrimaryKeySqlID(CommonConstants.DELETE_SQL+beanName+CommonConstants.PRIMARYKEY);
                    mybatisMapperConfig.setSelectByPrimaryKeySqlID(CommonConstants.SELECT_PRI_SUFIX+beanName+CommonConstants.PRIMARYKEY);
                    mybatisMapperConfig.setSelectListSqlID(CommonConstants.SELECT_PRE+beanName+CommonConstants.SELECT_LIST_ED);
                    mybatisMapperConfig.setCfgResultType(beanNamePackageStr);
                    mybatisMapperConfig.setCfgResultMapID(CommonConstants.BASE_RESULT_MAP_IDPRE);
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.tablePkBenanName,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoDeletePrimarySqlID,mybatisMapperConfig.getDeleteByPrimaryKeySqlID());
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoUpdateSqlID,mybatisMapperConfig.getUpdateSqlID());
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoSelecPrimarySqlID,mybatisMapperConfig.getSelectByPrimaryKeySqlID());
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoSelecListSqlID,mybatisMapperConfig.getSelectListSqlID());
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoInsertSqlID,mybatisMapperConfig.getInsertSqlID());
                    mybatisMapperConfigs.add(mybatisMapperConfig);
                }
            }
        }
        return mybatisMapperConfigs;
    }
}
