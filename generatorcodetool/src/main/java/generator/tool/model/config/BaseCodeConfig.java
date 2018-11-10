package generator.tool.model.config;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/30
 */
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


    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }

    public String getFileTemplateName() {
        return fileTemplateName;
    }

    public void setFileTemplateName(String fileTemplateName) {
        this.fileTemplateName = fileTemplateName;
    }

    public String getFileSuffx() {
        return fileSuffx;
    }

    public void setFileSuffx(String fileSuffx) {
        this.fileSuffx = fileSuffx;
    }

    public Boolean getIsGenerator() {
        return isGenerator;
    }

    public void setIsGenerator(Boolean isGenerator) {
        this.isGenerator = isGenerator;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getGenerator() {
        return isGenerator;
    }

    public void setGenerator(Boolean generator) {
        isGenerator = generator;
    }

    public String getCodeDataClassName() {
        return codeDataClassName;
    }

    public void setCodeDataClassName(String codeDataClassName) {
        this.codeDataClassName = codeDataClassName;
    }

    @Override
    public String toString() {
        return "BaseCodeConfig{" +
                ", pakageName='" + pakageName + '\'' +
                ", fileTemplateName='" + fileTemplateName + '\'' +
                ", fileSuffx='" + fileSuffx + '\'' +
                ", isGenerator=" + isGenerator +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", codeDataClassName='" + codeDataClassName + '\'' +
                '}';
    }
}
