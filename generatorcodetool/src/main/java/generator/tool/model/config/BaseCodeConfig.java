package generator.tool.model.config;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
@Data
public class BaseCodeConfig implements Serializable {
    //包名
    private String pakageName;
    //模板名称
    private String fileTemplateName;
    //文件后缀
    private String fileSuffx;
    //是否生成
    private Boolean isGenerator;
    //文件名
    private String fileName;
    //文件路径
    private String filePath;
    //获取数据类的类名
    private String codeDataClassName;

    public BaseCodeConfig() {
    }
}
