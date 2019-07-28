package generator.tool.pmsc.mode;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.ControllerCodeConfig;
import generator.tool.util.ColumnToPropertyUtil;
import lombok.Data;

import java.util.Map;

/**
 * <p>Title:Controller 的配置数据 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/18
 */
@Data
@SuppressWarnings("unchecked")
public class PsmcCotrollerCodeDataModel extends AbstractCodeDataModel {
    private String importDominPackageStr;
    private String importServicePackageStr;
    private String controllerUrl;
    private String serviceName;
    private String serviceArgName;
    private String beanName;
    private String beanArgName;
    private String addMethodName;
    private String deleteMethodName;
    private String updateMethodName;
    private String findPrimarkeyMethodName;
    private String findPageMethodName;
    public PsmcCotrollerCodeDataModel(){

    }
    public PsmcCotrollerCodeDataModel(String tableName){
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        Map<String, Object> projectCodePropertiesModelMap =
                SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES,Map.class);
        ControllerCodeConfig controllerCodeConfig = codeFileCfg.getCommonConfig().getControllerCode();
        String beanName = projectCodePropertiesModelMap.get(tableName+CommonConstants.beanName).toString();
        this.setPackageNameStr(controllerCodeConfig.getPakageName());
        this.setImportDominPackageStr(projectCodePropertiesModelMap.get(tableName+CommonConstants.beanNamePackageStr).toString());
        this.setImportServicePackageStr(projectCodePropertiesModelMap.get(tableName+CommonConstants.servicePackageName).toString());
        this.setControllerUrl("/website/backstage/"+beanName+"Controller");
        this.setFileName(beanName+"Controller");
        String serviceName = projectCodePropertiesModelMap.get(tableName+CommonConstants.serviceName).toString();
        this.setServiceName(serviceName);
        this.setServiceArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(serviceName));
        this.setBeanName(beanName);
        this.setBeanArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(beanName));
        this.setFindPageMethodName(projectCodePropertiesModelMap.get(tableName+CommonConstants.findPageMethodName).toString());
        this.setFindPrimarkeyMethodName(projectCodePropertiesModelMap.get(tableName+CommonConstants.findPrimarkeyMethodName).toString());
        this.setAddMethodName(projectCodePropertiesModelMap.get(tableName+CommonConstants.addMethodName).toString());
        this.setDeleteMethodName(projectCodePropertiesModelMap.get(tableName+CommonConstants.deleteMethodName).toString());
        this.setUpdateMethodName(projectCodePropertiesModelMap.get(tableName+CommonConstants.updateMethodName).toString());
    }
}
