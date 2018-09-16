package generator.tool.model.config;

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
public class CodeFileCfg implements Serializable {
    //公共配置信息
    private CommonConfig commonConfig;
    //生成数据表配置信息
    private List<TableConfig> tables;

    public CommonConfig getCommonConfig() {
        return commonConfig;
    }

    public void setCommonConfig(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    public List<TableConfig> getTables() {
        return tables;
    }

    public void setTables(List<TableConfig> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "CodeFileCfg{" +
                "commonConfig=" + commonConfig +
                ",\n tables=" + tables +
                '}'+"\n";
    }
}
