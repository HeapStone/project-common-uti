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
public class SerivceCodeData implements AbstractCodeData {
    List<ServiceCodeDataModel> serviceCodeDataModels =new ArrayList<>();
    /**
     * Default constructor
     */
    public SerivceCodeData() {
    }
    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModelMap = (Map<String, ProjectCodePropertiesModel>)SystemContext.get(CommonConstants.PROJECT_CODE_PROPERTIES);
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        ServiceCodeConfig serviceCode = codeFileCfg.getCommonConfig().getServiceCode();
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            ProjectCodePropertiesModel projectCodePropertiesModel = projectCodePropertiesModelMap.get(tableName);
            ServiceCodeDataModel serviceCodeDataModel = new ServiceCodeDataModel();
            serviceCodeDataModel.setTableName(tableName);
            Map<String,Object> modelBeanPorperties = projectCodePropertiesModel.getModelBeanPorperties();
            Map<String,Object> mapperPorperties = projectCodePropertiesModel.getMapperPorperties();
            serviceCodeDataModel.setTabkePkBeanName(mapperPorperties.get(CommonConstants.tablePkBenanName).toString());
            String beanName = modelBeanPorperties.get(CommonConstants.beanName).toString();
            serviceCodeDataModel.setServiceName(beanName+"Service");
            serviceCodeDataModel.setFileName(beanName+"Service");
            serviceCodeDataModel.setDomainName(beanName);
            serviceCodeDataModel.setBeanContent(beanName+"service接口");
            serviceCodeDataModel.setAddMethodName("add"+beanName);
            serviceCodeDataModel.setDeleteByPrimarKeyMethodName("deleteBy"+beanName+"primaryKeys");
            serviceCodeDataModel.setUpdateMethodName("update"+beanName);
            serviceCodeDataModel.setFindByPrimarKeyMethodName("findBy"+beanName+"primaryKey");
            serviceCodeDataModel.setFindByPageMethodName("findBy"+beanName+"Page");
            serviceCodeDataModel.setDomainArgName(beanName.substring(0, 1).toLowerCase()+beanName.substring(1, beanName.length()));
            serviceCodeDataModel.setImportServicePackageStr(serviceCode.getPakageName()+"."+beanName);
            serviceCodeDataModel.setPackageNameStr(serviceCode.getPakageName());
            serviceCodeDataModel.setIpmlPackageStr((serviceCode.getPakageName()+".impl"));
            serviceCodeDataModel.setImportDominPackageStr(modelBeanPorperties.get(CommonConstants.beanNamePackageStr).toString());
            serviceCodeDataModel.setDaoInsertSqlID((mapperPorperties.get(CommonConstants.daoInsertSqlID).toString()));
            serviceCodeDataModel.setDaoDeletePrimarySqlID((mapperPorperties.get(CommonConstants.daoDeletePrimarySqlID).toString()));
            serviceCodeDataModel.setDaoUpdateSqlID((mapperPorperties.get(CommonConstants.daoUpdateSqlID).toString()));
            serviceCodeDataModel.setDaoSelecPrimarySqlID((mapperPorperties.get(CommonConstants.daoSelecPrimarySqlID).toString()));
            serviceCodeDataModel.setDaoSelecListSqlID((mapperPorperties.get(CommonConstants.daoSelecListSqlID).toString()));
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