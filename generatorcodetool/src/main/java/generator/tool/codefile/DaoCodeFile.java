package generator.tool.codefile;

/**
 * Dao层代码工具
 */
public class DaoCodeFile extends AbstractCodeFile {

    /**
     * Default constructor
     */
    public DaoCodeFile(String codeFilePath,
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
    @Override
    public void generatorCodeFile() {

    }

    @Override
    public void inIntCodeFileData() {

    }

}