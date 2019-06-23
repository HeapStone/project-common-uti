package generator.tool.model.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.CfgProperty;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.MapperFileConfig;
import generator.tool.util.ColumnToPropertyUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

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
public class MybatisMapperCodeDataModel extends   AbstractCodeDataModel {
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
    public MybatisMapperCodeDataModel() {
    }

    public MybatisMapperCodeDataModel( Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap,
                                       TableBean table, String beanName) {
                SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        String tableName = table.getTableName();
        ProjectCodePropertiesModel resModel =  projectCodePropertiesModelMap.get(tableName);
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        MapperFileConfig mapperFile= codeFileCfg.getCommonConfig().getMapperFile();
        Map<String,Object> benMap =   resModel.getModelBeanPorperties();
        String fileName = StringUtils.isEmpty(mapperFile.getFileName())?beanName:mapperFile.getFileName();
        String beanNamePackageStr = benMap.get(CommonConstants.beanNamePackageStr).toString();
        this.setCfgTableName(table.getTableName());
        this.setCfgClassName(beanNamePackageStr);
        this.setCfgTablePk(table.getTbalePk());
        this.setFileName(fileName);
        this.setCfgPropertys(cfgPropertys);
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
}
