package generator.tool.codefile;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.factory.CodeFileDataFactory;

import java.util.List;

/**
 * 要生成代码对象工具的超类
 */
public abstract class AbstractCodeFile implements  CodeFileInterface {

    /**
     * Default constructor
     */
    public AbstractCodeFile(){

    }
    public AbstractCodeFile(String codeFilePath,
                            String codeFileSuffx, String codeFileName,
                            String codeTemplateFileName, String packageName, String fileName) {
        this.codeFilePath = codeFilePath;
        this.codeFileSuffx = codeFileSuffx;
        this.codeFileName = codeFileName;
        this.codeTemplateFileName = codeTemplateFileName;
        this.packageName = packageName;
        this.fileName = fileName;
    }

    /**
     * 生成文件路径
     */
    protected String codeFilePath;

    /**
     * 生成文件后缀
     */
    protected String codeFileSuffx;

    /**
     * 生成文件的文件名
     */
    protected String codeFileName;

    /**
     * 代码模板文件路径
     */
    protected String codeTemplateFileName;

    /**
     * 代码文件的具体数据信息
     */
    protected AbstractCodeData abstractCodeDatas;
    /**
     * 包名
     */
    protected String packageName;
    /**
     * 文件名
     */
    protected String fileName;

}