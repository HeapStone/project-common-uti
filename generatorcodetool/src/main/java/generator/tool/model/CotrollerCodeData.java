package generator.tool.model;

/**
 * <p>Title:Controller 的配置数据 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/8/18
 */
public class CotrollerCodeData {
    private String packageNameStr;
    private String importDominPackageStr;
    private String importServicePackageStr;
    private String controllerUrl;
    private String controllerName;
    private String serviceName;
    private String serviceArgName;
    private String beanName;
    private String beanArgName;
    private String addMethodName;
    private String deleteMethodName;
    private String updateMethodName;
    private String findPrimarkeyMethodName;
    private String findPageMethodName;

    public String getPackageNameStr() {
        return packageNameStr;
    }

    public void setPackageNameStr(String packageNameStr) {
        this.packageNameStr = packageNameStr;
    }

    public String getImportDominPackageStr() {
        return importDominPackageStr;
    }

    public void setImportDominPackageStr(String importDominPackageStr) {
        this.importDominPackageStr = importDominPackageStr;
    }

    public String getImportServicePackageStr() {
        return importServicePackageStr;
    }

    public void setImportServicePackageStr(String importServicePackageStr) {
        this.importServicePackageStr = importServicePackageStr;
    }

    public String getControllerUrl() {
        return controllerUrl;
    }

    public void setControllerUrl(String controllerUrl) {
        this.controllerUrl = controllerUrl;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceArgName() {
        return serviceArgName;
    }

    public void setServiceArgName(String serviceArgName) {
        this.serviceArgName = serviceArgName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanArgName() {
        return beanArgName;
    }

    public void setBeanArgName(String beanArgName) {
        this.beanArgName = beanArgName;
    }

    public String getAddMethodName() {
        return addMethodName;
    }

    public void setAddMethodName(String addMethodName) {
        this.addMethodName = addMethodName;
    }

    public String getDeleteMethodName() {
        return deleteMethodName;
    }

    public void setDeleteMethodName(String deleteMethodName) {
        this.deleteMethodName = deleteMethodName;
    }

    public String getUpdateMethodName() {
        return updateMethodName;
    }

    public void setUpdateMethodName(String updateMethodName) {
        this.updateMethodName = updateMethodName;
    }

    public String getFindPrimarkeyMethodName() {
        return findPrimarkeyMethodName;
    }

    public void setFindPrimarkeyMethodName(String findPrimarkeyMethodName) {
        this.findPrimarkeyMethodName = findPrimarkeyMethodName;
    }

    public String getFindPageMethodName() {
        return findPageMethodName;
    }

    public void setFindPageMethodName(String findPageMethodName) {
        this.findPageMethodName = findPageMethodName;
    }

    @Override
    public String toString() {
        return "CotrollerCodeData{" +
                "packageNameStr='" + packageNameStr + '\'' +
                ", importDominPackageStr='" + importDominPackageStr + '\'' +
                ", importServicePackageStr='" + importServicePackageStr + '\'' +
                ", controllerUrl='" + controllerUrl + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceArgName='" + serviceArgName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanArgName='" + beanArgName + '\'' +
                ", addMethodName='" + addMethodName + '\'' +
                ", deleteMethodName='" + deleteMethodName + '\'' +
                ", updateMethodName='" + updateMethodName + '\'' +
                ", findPrimarkeyMethodName='" + findPrimarkeyMethodName + '\'' +
                ", findPageMethodName='" + findPageMethodName + '\'' +
                '}';
    }
}
