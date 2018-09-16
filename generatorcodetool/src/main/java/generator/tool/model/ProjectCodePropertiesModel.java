package generator.tool.model;

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

    public Map<String, Object> getModelBeanPorperties() {
        return modelBeanPorperties;
    }

    public void setModelBeanPorperties(Map<String, Object> modelBeanPorperties) {
        this.modelBeanPorperties = modelBeanPorperties;
    }

    public Map<String, Object> getControllerPorperties() {
        return controllerPorperties;
    }

    public void setControllerPorperties(Map<String, Object> controllerPorperties) {
        this.controllerPorperties = controllerPorperties;
    }

    public Map<String, Object> getServicePorperties() {
        return servicePorperties;
    }

    public void setServicePorperties(Map<String, Object> servicePorperties) {
        this.servicePorperties = servicePorperties;
    }

    public Map<String, Object> getDaoPorperties() {
        return daoPorperties;
    }

    public void setDaoPorperties(Map<String, Object> daoPorperties) {
        this.daoPorperties = daoPorperties;
    }

    public Map<String, Object> getMapperPorperties() {
        return mapperPorperties;
    }

    public void setMapperPorperties(Map<String, Object> mapperPorperties) {
        this.mapperPorperties = mapperPorperties;
    }

    public Map<String, Object> getViewPorperties() {
        return viewPorperties;
    }

    public void setViewPorperties(Map<String, Object> viewPorperties) {
        this.viewPorperties = viewPorperties;
    }

    @Override
    public String toString() {
        return "ProjectCodeModel{" +
                ", modelBeanPorperties=" + modelBeanPorperties +
                ", controllerPorperties=" + controllerPorperties +
                ", servicePorperties=" + servicePorperties +
                ", daoPorperties=" + daoPorperties +
                ", mapperPorperties=" + mapperPorperties +
                ", viewPorperties=" + viewPorperties +
                '}';
    }
}
