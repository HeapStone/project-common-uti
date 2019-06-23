package generator.tool.model.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:要生成代码的配置类 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/29
 */
@Data
public class CodeFileCfg implements Serializable {
    //公共配置信息
    private CommonConfig commonConfig;
    //生成数据表配置信息
    private List<TableConfig> tables;

}
