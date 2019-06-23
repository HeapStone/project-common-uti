package generator.tool.model.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.ProjectCodePropertiesModel;
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
public class CotrollerCodeDataModel extends AbstractCodeDataModel{
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
    public CotrollerCodeDataModel(){

    }
    public CotrollerCodeDataModel( String tableName){
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)
                SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        ControllerCodeConfig controllerCodeConfig = codeFileCfg.getCommonConfig().getControllerCode();
        ProjectCodePropertiesModel projectCodePropertiesModel = projectCodePropertiesModelMap.get(tableName);
        Map<String,Object> modelBeanPorperties = projectCodePropertiesModel.getModelBeanPorperties();
        Map<String,Object> servicePorperties = projectCodePropertiesModel.getServicePorperties();
        String beanName = modelBeanPorperties.get(CommonConstants.beanName).toString();
        this.setPackageNameStr(controllerCodeConfig.getPakageName());
        this.setImportDominPackageStr(modelBeanPorperties.get(CommonConstants.beanNamePackageStr).toString());
        this.setImportServicePackageStr(servicePorperties.get(CommonConstants.servicePackageName).toString());
        this.setControllerUrl("/website/backstage/"+beanName+"Controller");
        this.setFileName(beanName+"Controller");
        String serviceName = servicePorperties.get(CommonConstants.serviceName).toString();
        this.setServiceName(serviceName);
        this.setServiceArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(serviceName));
        this.setBeanName(beanName);
        this.setBeanArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(beanName));
        this.setFindPageMethodName(servicePorperties.get(CommonConstants.findPageMethodName).toString());
        this.setFindPrimarkeyMethodName(servicePorperties.get(CommonConstants.findPrimarkeyMethodName).toString());
        this.setAddMethodName(servicePorperties.get(CommonConstants.addMethodName).toString());
        this.setDeleteMethodName(servicePorperties.get(CommonConstants.deleteMethodName).toString());
        this.setUpdateMethodName(servicePorperties.get(CommonConstants.updateMethodName).toString());
    }
}
