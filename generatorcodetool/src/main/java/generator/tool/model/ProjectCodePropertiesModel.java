package generator.tool.model;

import lombok.Data;

import java.util.Map;

/**
 * <p>Title: 用来描述代码的各个模块的关键信息比 </p>
 * <p>Description:
 *
 * </p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/12
 */
@Data
public class ProjectCodePropertiesModel {
    //实体类相关属性
    private Map<String,Object> modelBeanPorperties;
    //控制类属性
    private Map<String,Object> controllerPorperties;
    //服务类属性
    private Map<String,Object> servicePorperties;
    // dao属性
    private Map<String,Object> daoPorperties;
    //映射类属性
    private Map<String,Object> mapperPorperties;
    //视图页面属性
    private Map<String,Object> viewPorperties;
}
