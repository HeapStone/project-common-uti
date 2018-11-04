package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.CotrollerCodeDataModel;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.ControllerCodeConfig;
import generator.tool.util.ColumnToPropertyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SpringMvc控制层数据
 */
public class SpringMvcControllerData implements AbstractCodeData {
    List<CotrollerCodeDataModel> cotrollerCodeDatas =new ArrayList<>();
    /**
     * Default constructor
     */
    public SpringMvcControllerData() {
    }
    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        ControllerCodeConfig controllerCodeConfig = codeFileCfg.getCommonConfig().getControllerCode();
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            ProjectCodePropertiesModel projectCodePropertiesModel = projectCodePropertiesModelMap.get(tableName);
            Map<String,Object> modelBeanPorperties = projectCodePropertiesModel.getModelBeanPorperties();
            Map<String,Object> servicePorperties = projectCodePropertiesModel.getServicePorperties();
            String beanName = modelBeanPorperties.get(CommonConstants.beanName).toString();
            CotrollerCodeDataModel cotrollerCodeData = new CotrollerCodeDataModel();
            cotrollerCodeData.setPackageNameStr(controllerCodeConfig.getPakageName());
            cotrollerCodeData.setImportDominPackageStr(modelBeanPorperties.get(CommonConstants.beanNamePackageStr).toString());
            cotrollerCodeData.setImportServicePackageStr(servicePorperties.get(CommonConstants.servicePackageName).toString());
            cotrollerCodeData.setControllerUrl("/website/backstage/"+beanName+"Controller");
            cotrollerCodeData.setFileName(beanName+"Controller");
            String serviceName = servicePorperties.get(CommonConstants.serviceName).toString();
            cotrollerCodeData.setServiceName(serviceName);
            cotrollerCodeData.setServiceArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(serviceName));
            cotrollerCodeData.setBeanName(beanName);
            cotrollerCodeData.setBeanArgName(ColumnToPropertyUtil.toLowerCaseFirstOne(beanName));
            cotrollerCodeData.setFindPageMethodName(servicePorperties.get(CommonConstants.findPageMethodName).toString());
            cotrollerCodeData.setFindPrimarkeyMethodName(servicePorperties.get(CommonConstants.findPrimarkeyMethodName).toString());
            cotrollerCodeData.setAddMethodName(servicePorperties.get(CommonConstants.addMethodName).toString());
            cotrollerCodeData.setDeleteMethodName(servicePorperties.get(CommonConstants.deleteMethodName).toString());
            cotrollerCodeData.setUpdateMethodName(servicePorperties.get(CommonConstants.updateMethodName).toString());
            cotrollerCodeDatas.add(cotrollerCodeData);
        }
        return cotrollerCodeDatas;
    }
}