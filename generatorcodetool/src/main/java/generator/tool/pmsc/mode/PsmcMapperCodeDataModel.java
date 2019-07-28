package generator.tool.pmsc.mode;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.model.codedata.DomainPropertiesModel;
import generator.tool.model.config.CfgProperty;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.MapperFileConfig;
import generator.tool.util.ColumnToPropertyUtil;
import lombok.Data;
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
 * @history: Created by wanglei on  2018/8/8
 */
@Data
@SuppressWarnings("unchecked")
public class PsmcMapperCodeDataModel extends AbstractCodeDataModel {
    private String cfgClassName;
    private String cfgResultMapID;
    private String cfgResultType;
    private String cfgTablePk;
    private String beanPkColmn;
    private String baseColumn;
    private String cfgTableName;
    private String selectListSqlID;
    private String selectByPrimaryKeySqlID;
    private String deleteByPrimaryKeySqlID;
    private String insertSqlID;
    private String updateSqlID;
    private List<CfgProperty> cfgPropertys;
    public PsmcMapperCodeDataModel() {
    }

    public PsmcMapperCodeDataModel(Map<String, Object> projectCodePropertiesModelMap,
                                   TableBean table, String beanName) {
        String tableName = table.getTableName();
        // 从缓存获取实体类属性信息
        List<DomainPropertiesModel> beanProperties  = (List<DomainPropertiesModel>)
                projectCodePropertiesModelMap.get(tableName+CommonConstants.beanProperties);
        // 获取基本信息
        String baseColumn = getMapperPropertys(beanProperties);
        // 从缓存获实体类包路径
        String beanNamePackageStr = projectCodePropertiesModelMap.get(tableName+CommonConstants.beanNamePackageStr).toString();
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        MapperFileConfig mapperFile= codeFileCfg.getCommonConfig().getMapperFile();
        String fileName = StringUtils.isEmpty(mapperFile.getFileName())?beanName:mapperFile.getFileName();
        this.setCfgTableName(tableName);
        this.setCfgClassName(beanNamePackageStr);
        this.setCfgTablePk(table.getTbalePk());
        this.setFileName(fileName);
        this.setBaseColumn(baseColumn);
        this.setBeanPkColmn(ColumnToPropertyUtil.camelName(table.getTbalePk()));
        this.setBaseColumn(baseColumn);
        this.setUpdateSqlID(CommonConstants.UPDATE_SQL+beanName);
        this.setInsertSqlID(CommonConstants.INSER_SQL+beanName);
        this.setDeleteByPrimaryKeySqlID(CommonConstants.DELETE_SQL+beanName+CommonConstants.PRIMARYKEY);
        this.setSelectByPrimaryKeySqlID(CommonConstants.SELECT_PRI_SUFIX+beanName+CommonConstants.PRIMARYKEY);
        this.setSelectListSqlID(CommonConstants.SELECT_PRE+beanName+CommonConstants.SELECT_LIST_ED);
        this.setCfgResultType(beanNamePackageStr);
        this.setCfgResultMapID(CommonConstants.BASE_RESULT_MAP_IDPRE);
    }

    /**
     * 获取映射文件的属性信息
     * @param beanProperties
     * @return
     */
    private String getMapperPropertys(List<DomainPropertiesModel> beanProperties) {
        this.cfgPropertys = new ArrayList<>();
        StringBuilder sbf = new StringBuilder();
        for(DomainPropertiesModel bean:beanProperties){
            String columnName = ColumnToPropertyUtil.underscoreName(bean.getPropertNameUpCase());
            CfgProperty cfgProperty = new CfgProperty();
            cfgProperty.setJdbcType(bean.getJdbcType());
            cfgProperty.setColumnName(columnName);
            cfgProperty.setPropertyName(bean.getPropertName());
            sbf.append(columnName);
            sbf.append(",");
            cfgPropertys.add(cfgProperty);
        }
        String baseColumn = sbf.toString();
        baseColumn=baseColumn.substring(0, baseColumn.lastIndexOf(","));
        return baseColumn;
    }
}
