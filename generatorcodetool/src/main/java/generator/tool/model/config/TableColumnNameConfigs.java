package generator.tool.model.config;

import lombok.Data;

/**
 * <p>Title:要生成表的列配值信息</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
@Data
public class TableColumnNameConfigs {
    //实体类的列名
    private String domainColumnName;
    //数据表名
    private String tableColumnName;
    //是否主键
    private String isPk;
}
