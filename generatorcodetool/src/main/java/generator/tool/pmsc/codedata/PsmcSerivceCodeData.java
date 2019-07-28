package generator.tool.pmsc.codedata;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.pmsc.mode.PsmcServiceCodeDataModel;
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
@SuppressWarnings("unchecked")
public class PsmcSerivceCodeData implements AbstractCodeData<List<PsmcServiceCodeDataModel>> {
    List<PsmcServiceCodeDataModel> serviceCodeDataModels =new ArrayList<>();
    /**
     * Default constructor
     */
    public PsmcSerivceCodeData() {
    }
    @Override
    public List<PsmcServiceCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        Map<String, Object> projectCodePropertiesModelMap = SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES,Map.class);
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        ServiceCodeConfig serviceCode = codeFileCfg.getCommonConfig().getServiceCode();
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            PsmcServiceCodeDataModel serviceCodeDataModel = new PsmcServiceCodeDataModel(
                    projectCodePropertiesModelMap,serviceCode.getPakageName(),tableName);
            serviceCodeDataModels.add(serviceCodeDataModel);
            setServiceCommons2Cache(tableName, serviceCodeDataModel);


        }
        return serviceCodeDataModels;
    }

    /**
     * 将service的公共属性放到缓存里面
     * @param tableName 表名
     * @param serviceCodeDataModel service属性信息
     */
    private void setServiceCommons2Cache(String tableName, PsmcServiceCodeDataModel serviceCodeDataModel) {
        //设置Controller中用到的公共属性
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.serviceName,serviceCodeDataModel.getServiceName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.addMethodName,serviceCodeDataModel.getAddMethodName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.deleteMethodName,serviceCodeDataModel.getDeleteByPrimarKeyMethodName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.updateMethodName,serviceCodeDataModel.getUpdateMethodName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.findPrimarkeyMethodName,serviceCodeDataModel.getFindByPrimarKeyMethodName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.findPageMethodName,serviceCodeDataModel.getFindByPageMethodName());
        TableUtil.setProjectCodePropertiesByTableName(tableName+
                CommonConstants.servicePackageName,serviceCodeDataModel.getPackageNameStr());
    }
}