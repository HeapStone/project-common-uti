package generator.tool.codefile;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/10/13
 */
public class ViewCodeFile  extends AbstractCodeFile{
    private String fileImplTemplateName;
    /**
     * Default constructor
     */
    public ViewCodeFile(String codeFilePath,
                            String codeFileSuffx, String codeFileName,
                            String codeTemplateFileName, String packageName,
                        String fileName) {
        super(codeFilePath, codeFileSuffx, codeFileName,codeTemplateFileName,packageName,fileName);
        this.codeFilePath = codeFilePath;
        this.codeFileSuffx = codeFileSuffx;
        this.codeFileName = codeFileName;
        this.codeTemplateFileName = codeTemplateFileName;
        this.packageName = packageName;
        this.fileName = fileName;
        inIntCodeFileData();
    }
    @Override
    public void generatorCodeFile() {

    }

    @Override
    public void inIntCodeFileData() {

    }
}
