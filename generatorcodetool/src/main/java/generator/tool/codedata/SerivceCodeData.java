package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.codedata.ServiceCodeDataModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.ServiceCodeConfig;
import generator.tool.util.TableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service实体数据
 */
public class SerivceCodeData implements AbstractCodeData<List<ServiceCodeDataModel>> {
    List<ServiceCodeDataModel> serviceCodeDataModels =new ArrayList<>();
    /**
     * Default constructor
     */
    public SerivceCodeData() {
    }
    @Override
    public List<ServiceCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        ServiceCodeConfig serviceCode = codeFileCfg.getCommonConfig().getServiceCode();
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            ProjectCodePropertiesModel projectCodePropertiesModel = projectCodePropertiesModelMap.get(tableName);
            ServiceCodeDataModel serviceCodeDataModel = new ServiceCodeDataModel(
                    projectCodePropertiesModel,serviceCode.getPakageName(),tableName);
            serviceCodeDataModels.add(serviceCodeDataModel);
            //设置Controller中用到的公共属性
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.serviceName,serviceCodeDataModel.getServiceName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.addMethodName,serviceCodeDataModel.getAddMethodName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.deleteMethodName,serviceCodeDataModel.getDeleteByPrimarKeyMethodName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.updateMethodName,serviceCodeDataModel.getUpdateMethodName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.findPrimarkeyMethodName,serviceCodeDataModel.getFindByPrimarKeyMethodName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.findPageMethodName,serviceCodeDataModel.getFindByPageMethodName());
            TableUtil.setProjectCodePropertiesByTableName(tableBean.getTableName(),CommonConstants.servicePorperties,
                    CommonConstants.servicePackageName,serviceCodeDataModel.getPackageNameStr());

        }
        return serviceCodeDataModels;
    }
}