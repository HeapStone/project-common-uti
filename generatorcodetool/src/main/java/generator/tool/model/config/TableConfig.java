package generator.tool.model.config;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: 要生成代码文件的表配置信息</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
@Data
public class TableConfig {
    private String tableName;
    private String domainCodeFileName;
    private String controllerCodeFileName;
    private String serviceCodeFileName;
    private String mapperCodeFileName;
    private String daoCodeFileName;
    private String viewCodeFileName;
    List<TableColumnNameConfigs> tbaleColumns;
}
