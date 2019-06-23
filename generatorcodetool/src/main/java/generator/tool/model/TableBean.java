package generator.tool.model;
import lombok.Data;

import java.util.List;

/**
 * <p>Title:数据库表实体类 </p>
 * <p>Description:数据库表实体类 </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
@Data
public class TableBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableContent;
    /**
     * 数据库
     */
    private String tableCatalog;
    /**
     * 表属性
     */
    private List<TableColumn> tableColumn;
    /**
     * 表主键名
     */
    private String tbalePk;
}
