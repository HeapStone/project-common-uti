package generator.tool.codefile;

/**
 * 控制层代码生成工具
 */
public class ControllerCodeFile extends AbstractCodeFile {

    public ControllerCodeFile(String codeFilePath,
                              String codeFileSuffx, String codeFileName,
                              String codeTemplateFileName, String packageName, String fileName) {
        super(codeFilePath, codeFileSuffx, codeFileName,codeTemplateFileName,packageName,fileName);
        this.codeFilePath = codeFilePath;
        this.codeFileSuffx = codeFileSuffx;
        this.codeFileName = codeFileName;
        this.codeTemplateFileName = codeTemplateFileName;
        this.packageName = packageName;
        this.fileName = fileName;
    }
    /**
     * 默认生成代码文件的方法
     */
    public  void generatorCodeFile(){

    }

    @Override
    public void inIntCodeFileData() {

    }

}