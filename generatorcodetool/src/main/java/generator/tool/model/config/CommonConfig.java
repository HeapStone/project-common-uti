package generator.tool.model.config;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:代码生成公共配置类 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
@Data
public class CommonConfig implements Serializable {
    private String projectPath;
    private String driver;
    private String url;
    private String userName;
    private String password;
    private String templatepath;
    private String freeMarkParams;
    private DomainCodeConfig daominCode;
    private ControllerCodeConfig controllerCode;
    private DaoCodeConfig daoDaoCode;
    private MapperFileConfig mapperFile;
    private ServiceCodeConfig serviceCode;
    private ViewCodeConfig viewCode;
}
