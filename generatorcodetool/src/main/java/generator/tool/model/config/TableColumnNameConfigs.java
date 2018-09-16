package generator.tool.model.config;

/**
 * <p>Title:要生成表的列配值信息</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
public class TableColumnNameConfigs {
    //实体类的列名
    private String domainColumnName;
    //数据表名
    private String tableColumnName;
    //是否主键
    private String isPk;

    public String getDomainColumnName() {
        return domainColumnName;
    }

    public void setDomainColumnName(String domainColumnName) {
        this.domainColumnName = domainColumnName;
    }

    public String getTableColumnName() {
        return tableColumnName;
    }

    public void setTableColumnName(String tableColumnName) {
        this.tableColumnName = tableColumnName;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    @Override
    public String toString() {
        return "TableColumnNameConfigs{" +
                "domainColumnName='" + domainColumnName + '\'' +
                ", tableColumnName='" + tableColumnName + '\'' +
                ", isPk='" + isPk + '\'' +
                '}';
    }
}
