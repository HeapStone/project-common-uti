package generator.tool.model.config;

import java.io.Serializable;

/**
 * <p>Title:代码生成公共配置类 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
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

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public DomainCodeConfig getDaominCode() {
        return daominCode;
    }

    public void setDaominCode(DomainCodeConfig daominCode) {
        this.daominCode = daominCode;
    }

    public ControllerCodeConfig getControllerCode() {
        return controllerCode;
    }

    public void setControllerCode(ControllerCodeConfig controllerCode) {
        this.controllerCode = controllerCode;
    }

    public DaoCodeConfig getDaoDaoCode() {
        return daoDaoCode;
    }

    public void setDaoDaoCode(DaoCodeConfig daoDaoCode) {
        this.daoDaoCode = daoDaoCode;
    }

    public MapperFileConfig getMapperFile() {
        return mapperFile;
    }

    public void setMapperFile(MapperFileConfig mapperFile) {
        this.mapperFile = mapperFile;
    }

    public ServiceCodeConfig getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(ServiceCodeConfig serviceCode) {
        this.serviceCode = serviceCode;
    }

    public ViewCodeConfig getViewCode() {
        return viewCode;
    }

    public void setViewCode(ViewCodeConfig viewCode) {
        this.viewCode = viewCode;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTemplatepath() {
        return templatepath;
    }

    public void setTemplatepath(String templatepath) {
        this.templatepath = templatepath;
    }

    public String getFreeMarkParams() {
        return freeMarkParams;
    }

    public void setFreeMarkParams(String freeMarkParams) {
        this.freeMarkParams = freeMarkParams;
    }

    @Override
    public String toString() {
        return "CommonConfig{" +
                "projectPath='" + projectPath + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", templatepath='" + templatepath + '\'' +
                ", freeMarkParams='" + freeMarkParams + '\'' +
                ", daominCode=" + daominCode +
                ", controllerCode=" + controllerCode +
                ", daoDaoCode=" + daoDaoCode +
                ", mapperFile=" + mapperFile +
                ", serviceCode=" + serviceCode +
                ", viewCode=" + viewCode +
                '}';
    }
}
