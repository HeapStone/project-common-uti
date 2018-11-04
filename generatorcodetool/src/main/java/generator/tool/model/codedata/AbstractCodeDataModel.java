package generator.tool.model.codedata;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/11/4
 */
public class AbstractCodeDataModel {
    private String fileName;
    private String packageNameStr;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPackageNameStr() {
        return packageNameStr;
    }

    public void setPackageNameStr(String packageNameStr) {
        this.packageNameStr = packageNameStr;
    }
}
