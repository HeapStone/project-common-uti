package generator.tool.model.config;

import lombok.Data;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/10/13
 */
@Data
public class CfgProperty {
    private String columnName;
    private String jdbcType;
    private String propertyName;

   }
