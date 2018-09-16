package generator.tool.model;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/8
 */
public class MybatisMapperConfig extends AbstractMapperConfig{
    private String fileName;
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
    public String getCfgClassName() {
        return cfgClassName;
    }

    public void setCfgClassName(String cfgClassName) {
        this.cfgClassName = cfgClassName;
    }

    public String getCfgResultMapID() {
        return cfgResultMapID;
    }

    public void setCfgResultMapID(String cfgResultMapID) {
        this.cfgResultMapID = cfgResultMapID;
    }

    public String getCfgResultType() {
        return cfgResultType;
    }

    public void setCfgResultType(String cfgResultType) {
        this.cfgResultType = cfgResultType;
    }

    public String getCfgTablePk() {
        return cfgTablePk;
    }

    public void setCfgTablePk(String cfgTablePk) {
        this.cfgTablePk = cfgTablePk;
    }

    public String getBeanPkColmn() {
        return beanPkColmn;
    }

    public void setBeanPkColmn(String beanPkColmn) {
        this.beanPkColmn = beanPkColmn;
    }

    public String getBaseColumn() {
        return baseColumn;
    }

    public void setBaseColumn(String baseColumn) {
        this.baseColumn = baseColumn;
    }

    public String getCfgTableName() {
        return cfgTableName;
    }

    public void setCfgTableName(String cfgTableName) {
        this.cfgTableName = cfgTableName;
    }

    public String getSelectListSqlID() {
        return selectListSqlID;
    }

    public void setSelectListSqlID(String selectListSqlID) {
        this.selectListSqlID = selectListSqlID;
    }

    public String getSelectByPrimaryKeySqlID() {
        return selectByPrimaryKeySqlID;
    }

    public void setSelectByPrimaryKeySqlID(String selectByPrimaryKeySqlID) {
        this.selectByPrimaryKeySqlID = selectByPrimaryKeySqlID;
    }

    public String getDeleteByPrimaryKeySqlID() {
        return deleteByPrimaryKeySqlID;
    }

    public void setDeleteByPrimaryKeySqlID(String deleteByPrimaryKeySqlID) {
        this.deleteByPrimaryKeySqlID = deleteByPrimaryKeySqlID;
    }

    public String getInsertSqlID() {
        return insertSqlID;
    }

    public void setInsertSqlID(String insertSqlID) {
        this.insertSqlID = insertSqlID;
    }

    public String getUpdateSqlID() {
        return updateSqlID;
    }

    public void setUpdateSqlID(String updateSqlID) {
        this.updateSqlID = updateSqlID;
    }

    public List<CfgProperty> getCfgPropertys() {
        return cfgPropertys;
    }

    public void setCfgPropertys(List<CfgProperty> cfgPropertys) {
        this.cfgPropertys = cfgPropertys;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "MybatisMapperConfig{" +
                "fileName='" + fileName + '\'' +
                ", cfgClassName='" + cfgClassName + '\'' +
                ", cfgResultMapID='" + cfgResultMapID + '\'' +
                ", cfgResultType='" + cfgResultType + '\'' +
                ", cfgTablePk='" + cfgTablePk + '\'' +
                ", beanPkColmn='" + beanPkColmn + '\'' +
                ", baseColumn='" + baseColumn + '\'' +
                ", cfgTableName='" + cfgTableName + '\'' +
                ", selectListSqlID='" + selectListSqlID + '\'' +
                ", selectByPrimaryKeySqlID='" + selectByPrimaryKeySqlID + '\'' +
                ", deleteByPrimaryKeySqlID='" + deleteByPrimaryKeySqlID + '\'' +
                ", insertSqlID='" + insertSqlID + '\'' +
                ", updateSqlID='" + updateSqlID + '\'' +
                ", cfgPropertys=" + cfgPropertys +
                '}';
    }

    public MybatisMapperConfig() {
    }
}
