package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.*;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.DomainCodeConfig;
import generator.tool.model.config.MapperFileConfig;
import generator.tool.util.ColumnToPropertyUtil;
import generator.tool.util.TableUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
public class MybatisMapperData extends AbstractCodeData{
    private  List<MybatisMapperConfig> mybatisMapperConfigs  = new ArrayList<>();
    /**
     * Default constructor
     */
    public MybatisMapperData() {
    }

    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        MapperFileConfig mapperFile= codeFileCfg.getCommonConfig().getMapperFile();
        if(tableBeans.size()>0){
            for(TableBean table :tableBeans) {
                MybatisMapperConfig mybatisMapperConfig = new MybatisMapperConfig();
                String tableName = table.getTableName();
                ProjectCodePropertiesModel resModel =  projectCodePropertiesModelMap.get(tableName);
                Map<String,Object>  benMap =   resModel.getModelBeanPorperties();
                if(null!=benMap&&!benMap.isEmpty()){
                    String beanName = benMap.get(CommonConstants.beanName).toString();
                    String fileName = StringUtils.isEmpty(mapperFile.getFileName())?beanName+mapperFile.getFileSuffx():mapperFile.getFileName();
                    String beanNamePackageStr = benMap.get(CommonConstants.beanNamePackageStr).toString();
                    List<BeanProperties> beanProperties  = (List<BeanProperties>)benMap.get(CommonConstants.beanProperties);
                    List<CfgProperty> cfgPropertys = new ArrayList<>();
                    StringBuffer sbf = new StringBuffer();
                    for(BeanProperties bean:beanProperties){
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
                    mybatisMapperConfig.setCfgClassName(beanNamePackageStr+"."+beanName);
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
                    mybatisMapperConfig.setCfgResultType(beanNamePackageStr+"."+beanName);
                    mybatisMapperConfig.setCfgResultMapID(CommonConstants.BASE_RESULT_MAP_IDPRE);
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.tablePkBenanName,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoDeletePrimarySqlID,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoUpdateSqlID,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoSelecPrimarySqlID,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    TableUtil.setProjectCodePropertiesByTableName(table.getTableName(),CommonConstants.mapperPorperties,CommonConstants.daoSelecListSqlID,ColumnToPropertyUtil.camelName(table.getTbalePk()));
                    mybatisMapperConfigs.add(mybatisMapperConfig);
                }
            }
        }
        return mybatisMapperConfigs;
    }
}
