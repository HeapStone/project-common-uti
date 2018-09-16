package generator.tool.model.config;

import java.util.List;

/**
 * <p>Title: 要生成代码文件的表配置信息</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
public class TableConfig {
    private String tableName;
    private String domainCodeFileName;
    private String controllerCodeFileName;
    private String serviceCodeFileName;
    private String mapperCodeFileName;
    private String daoCodeFileName;
    private String viewCodeFileName;
    List<TableColumnNameConfigs> tbaleColumns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainCodeFileName() {
        return domainCodeFileName;
    }

    public void setDomainCodeFileName(String domainCodeFileName) {
        this.domainCodeFileName = domainCodeFileName;
    }

    public String getControllerCodeFileName() {
        return controllerCodeFileName;
    }

    public void setControllerCodeFileName(String controllerCodeFileName) {
        this.controllerCodeFileName = controllerCodeFileName;
    }

    public String getServiceCodeFileName() {
        return serviceCodeFileName;
    }

    public void setServiceCodeFileName(String serviceCodeFileName) {
        this.serviceCodeFileName = serviceCodeFileName;
    }

    public String getMapperCodeFileName() {
        return mapperCodeFileName;
    }

    public void setMapperCodeFileName(String mapperCodeFileName) {
        this.mapperCodeFileName = mapperCodeFileName;
    }

    public String getDaoCodeFileName() {
        return daoCodeFileName;
    }

    public void setDaoCodeFileName(String daoCodeFileName) {
        this.daoCodeFileName = daoCodeFileName;
    }

    public String getViewCodeFileName() {
        return viewCodeFileName;
    }

    public void setViewCodeFileName(String viewCodeFileName) {
        this.viewCodeFileName = viewCodeFileName;
    }

    public List<TableColumnNameConfigs> getTbaleColumns() {
        return tbaleColumns;
    }

    public void setTbaleColumns(List<TableColumnNameConfigs> tbaleColumns) {
        this.tbaleColumns = tbaleColumns;
    }

    @Override
    public String toString() {
        return "TableConfig{" +
                "tableName='" + tableName + '\'' +
                ", domainCodeFileName='" + domainCodeFileName + '\'' +
                ", controllerCodeFileName='" + controllerCodeFileName + '\'' +
                ", serviceCodeFileName='" + serviceCodeFileName + '\'' +
                ", mapperCodeFileName='" + mapperCodeFileName + '\'' +
                ", daoCodeFileName='" + daoCodeFileName + '\'' +
                ", viewCodeFileName='" + viewCodeFileName + '\'' +
                ", tbaleColumns=" + tbaleColumns +
                '}'+"\n";
    }
}
