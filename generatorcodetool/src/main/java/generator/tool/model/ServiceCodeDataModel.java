package generator.tool.model;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/14
 */
public class ServiceCodeDataModel {
    private String tableName;
    private String packageNameStr;
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

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageNameStr() {
        return packageNameStr;
    }

    public void setPackageNameStr(String packageNameStr) {
        this.packageNameStr = packageNameStr;
    }

    public String getImportDominPackageStr() {
        return importDominPackageStr;
    }

    public void setImportDominPackageStr(String importDominPackageStr) {
        this.importDominPackageStr = importDominPackageStr;
    }

    public String getBeanContent() {
        return beanContent;
    }

    public void setBeanContent(String beanContent) {
        this.beanContent = beanContent;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFindByPageMethodName() {
        return findByPageMethodName;
    }

    public void setFindByPageMethodName(String findByPageMethodName) {
        this.findByPageMethodName = findByPageMethodName;
    }

    public String getFindByPrimarKeyMethodName() {
        return findByPrimarKeyMethodName;
    }

    public void setFindByPrimarKeyMethodName(String findByPrimarKeyMethodName) {
        this.findByPrimarKeyMethodName = findByPrimarKeyMethodName;
    }

    public String getDeleteByPrimarKeyMethodName() {
        return deleteByPrimarKeyMethodName;
    }

    public void setDeleteByPrimarKeyMethodName(String deleteByPrimarKeyMethodName) {
        this.deleteByPrimarKeyMethodName = deleteByPrimarKeyMethodName;
    }

    public String getAddMethodName() {
        return addMethodName;
    }

    public void setAddMethodName(String addMethodName) {
        this.addMethodName = addMethodName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainArgName() {
        return domainArgName;
    }

    public void setDomainArgName(String domainArgName) {
        this.domainArgName = domainArgName;
    }

    public String getUpdateMethodName() {
        return updateMethodName;
    }

    public void setUpdateMethodName(String updateMethodName) {
        this.updateMethodName = updateMethodName;
    }

    public String getImportServicePackageStr() {
        return importServicePackageStr;
    }

    public void setImportServicePackageStr(String importServicePackageStr) {
        this.importServicePackageStr = importServicePackageStr;
    }

    public String getDaoSelecListSqlID() {
        return daoSelecListSqlID;
    }

    public void setDaoSelecListSqlID(String daoSelecListSqlID) {
        this.daoSelecListSqlID = daoSelecListSqlID;
    }

    public String getDaoSelecPrimarySqlID() {
        return daoSelecPrimarySqlID;
    }

    public void setDaoSelecPrimarySqlID(String daoSelecPrimarySqlID) {
        this.daoSelecPrimarySqlID = daoSelecPrimarySqlID;
    }

    public String getDaoDeletePrimarySqlID() {
        return daoDeletePrimarySqlID;
    }

    public void setDaoDeletePrimarySqlID(String daoDeletePrimarySqlID) {
        this.daoDeletePrimarySqlID = daoDeletePrimarySqlID;
    }

    public String getDaoInsertSqlID() {
        return daoInsertSqlID;
    }

    public void setDaoInsertSqlID(String daoInsertSqlID) {
        this.daoInsertSqlID = daoInsertSqlID;
    }

    public String getDaoUpdateSqlID() {
        return daoUpdateSqlID;
    }

    public void setDaoUpdateSqlID(String daoUpdateSqlID) {
        this.daoUpdateSqlID = daoUpdateSqlID;
    }

    public String getTabkePkBeanName() {
        return tabkePkBeanName;
    }

    public void setTabkePkBeanName(String tabkePkBeanName) {
        this.tabkePkBeanName = tabkePkBeanName;
    }

    public String getIpmlPackageStr() {
        return ipmlPackageStr;
    }

    public void setIpmlPackageStr(String ipmlPackageStr) {
        this.ipmlPackageStr = ipmlPackageStr;
    }

    @Override
    public String toString() {
        return "ServiceCodeDataModel{" +
                "tableName='" + tableName + '\'' +
                ", packageNameStr='" + packageNameStr + '\'' +
                ", importDominPackageStr='" + importDominPackageStr + '\'' +
                ", beanContent='" + beanContent + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", findByPageMethodName='" + findByPageMethodName + '\'' +
                ", findByPrimarKeyMethodName='" + findByPrimarKeyMethodName + '\'' +
                ", deleteByPrimarKeyMethodName='" + deleteByPrimarKeyMethodName + '\'' +
                ", addMethodName='" + addMethodName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", domainArgName='" + domainArgName + '\'' +
                ", updateMethodName='" + updateMethodName + '\'' +
                ", importServicePackageStr='" + importServicePackageStr + '\'' +
                ", daoSelecListSqlID='" + daoSelecListSqlID + '\'' +
                ", daoSelecPrimarySqlID='" + daoSelecPrimarySqlID + '\'' +
                ", daoDeletePrimarySqlID='" + daoDeletePrimarySqlID + '\'' +
                ", daoInsertSqlID='" + daoInsertSqlID + '\'' +
                ", daoUpdateSqlID='" + daoUpdateSqlID + '\'' +
                ", tabkePkBeanName='" + tabkePkBeanName + '\'' +
                ", ipmlPackageStr='" + ipmlPackageStr + '\'' +
                '}';
    }
}
