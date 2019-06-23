package generator.tool.model.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.model.ProjectCodePropertiesModel;
import lombok.Data;

import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/14
 */
@Data
public class ServiceCodeDataModel  extends AbstractCodeDataModel{
    private String tableName;
    private String importDominPackageStr;
    private String beanContent;
    private String serviceName;
    private String findByPageMethodName;
    private String findByPrimarKeyMethodName;
    private String deleteByPrimarKeyMethodName;
    private String addMethodName;
    private String domainName;
    private String domainArgName;
    private String updateMethodName;
    private String importServicePackageStr;
    private String daoSelecListSqlID;
    private String daoSelecPrimarySqlID;
    private String daoDeletePrimarySqlID;
    private String daoInsertSqlID;
    private String daoUpdateSqlID;
    private String tabkePkBeanName;
    private String ipmlPackageStr;
    public String getTableName() {
        return tableName;
    }
    public ServiceCodeDataModel(){

    }
    public ServiceCodeDataModel(ProjectCodePropertiesModel projectCodePropertiesModel,String pakageName,String tableName){
        Map<String,Object> modelBeanPorperties = projectCodePropertiesModel.getModelBeanPorperties();
        Map<String,Object> mapperPorperties = projectCodePropertiesModel.getMapperPorperties();
        this.setTabkePkBeanName(mapperPorperties.get(CommonConstants.tablePkBenanName).toString());
        String beanName = modelBeanPorperties.get(CommonConstants.beanName).toString();
        this.setServiceName(beanName+"Service");
        this.setFileName(beanName+"Service");
        this.setDomainName(beanName);
        this.setBeanContent(beanName+"service接口");
        this.setAddMethodName("add"+beanName);
        this.setDeleteByPrimarKeyMethodName("deleteBy"+beanName+"primaryKeys");
        this.setUpdateMethodName("update"+beanName);
        this.setFindByPrimarKeyMethodName("findBy"+beanName+"primaryKey");
        this.setFindByPageMethodName("findBy"+beanName+"Page");
        this.setDomainArgName(beanName.substring(0, 1).toLowerCase()+beanName.substring(1, beanName.length()));
        this.setImportServicePackageStr(pakageName+"."+beanName);
        this.setPackageNameStr(pakageName);
        this.setIpmlPackageStr(pakageName+".impl");
        this.setImportDominPackageStr(modelBeanPorperties.get(CommonConstants.beanNamePackageStr).toString());
        this.setDaoInsertSqlID((mapperPorperties.get(CommonConstants.daoInsertSqlID).toString()));
        this.setDaoDeletePrimarySqlID((mapperPorperties.get(CommonConstants.daoDeletePrimarySqlID).toString()));
        this.setDaoUpdateSqlID((mapperPorperties.get(CommonConstants.daoUpdateSqlID).toString()));
        this.setDaoSelecPrimarySqlID((mapperPorperties.get(CommonConstants.daoSelecPrimarySqlID).toString()));
        this.setDaoSelecListSqlID((mapperPorperties.get(CommonConstants.daoSelecListSqlID).toString()));
        this.setTableName(tableName);
    }
}
